package stacks;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private java.util.Stack<Integer> number = new java.util.Stack<>();
    private java.util.Stack<String> operators = new java.util.Stack<>();
    private HashMap<String, Integer> mapping = new HashMap<>();

    public Solution() {
        mapping.put("+", 1);
        mapping.put("-", 1);
        mapping.put("(", 2);
        mapping.put(")", 3);
    }

    public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String handle(String s) {
        // 处理空格
        StringBuilder sb = new StringBuilder(s.trim());
        for (int i = 0; i < sb.length() - 1; i++) {
            if ((sb.charAt(i) >= '0' && sb.charAt(i) <= '9' && sb.charAt(i + 1) != ' '
                    && (sb.charAt(i + 1) < '0' || sb.charAt(i + 1) > '9'))
                    || ((sb.charAt(i) < '0' || sb.charAt(i) > '9') && sb.charAt(i) != ' '
                    && ((sb.charAt(i + 1) >= '0' && sb.charAt(i + 1) <= '9')
                    || ((sb.charAt(i + 1) < '0' || sb.charAt(i + 1) > '9')
                    && sb.charAt(i + 1) != ' ')))) {
                sb = sb.insert(++i, ' ');
            } else if (sb.charAt(i) == ' ' && sb.charAt(i + 1) == ' ') {
                sb.deleteCharAt(i);
            }
        }
        s = sb.toString();
        // 将（1）处理为 1
        Pattern p = Pattern.compile("\\(\\s\\d+\\s\\)");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String old = m.group();
            String newStr = old.substring(old.indexOf("(") + 2, old.indexOf(")") - 1);
            s = s.replace(old, newStr);
        }
        return s;
    }

    public void cal(String oper, int num1, int num2) {
        if (oper.equals("+")) {
            number.push(num2 + num1);
        } else if (oper.equals("-")) {
            number.push(num2 - num1);
        } else {
            System.out.println(oper + " \t" + num1 + " \t" + num2);
            throw new Error("calculate error!!!");
        }
    }

    public int calculate(String s) {
        s = handle(s);
        String[] arr = s.trim().split(" ");

        for (int i = 0; i < arr.length; i++) {
            if (isNumericZidai(arr[i])) {
                number.push(Integer.valueOf(arr[i]));
            } else {
                if (operators.isEmpty()) {
                    operators.push(arr[i]);
                } else {
                    String top = operators.peek();
                    String oper = top;
                    // 当前运算符优先级大于栈顶元素直接push
                    if (mapping.get(arr[i]) > mapping.get(top)) {
                        operators.push(arr[i]);
                    } else {
                        // 栈顶元素或者当前运算符是“(”，直接push
                        if (top.equals("(") || arr[i].equals("(")) {
                            operators.push(arr[i]);
                            continue;
                        }
                        // 当前运算符优先级小于栈顶元素 且 栈顶元素或者当前运算符是“(” 进行运算流程
                        // pop出栈顶元素
                        oper = operators.pop();
                        // oper为右括号
                        if (oper.equals(")")) {
                            // 修改运算符同时pop出左括号
                            if (!operators.isEmpty()) {
                                oper = operators.pop();
                            }
                            if (!operators.isEmpty() && operators.peek().equals("(")) {
                                operators.pop();
                            }
                        }
                        operators.push(arr[i]);
                        if (number.size() >= 2) {
                            int num1 = number.pop();
                            int num2 = number.pop();
                            cal(oper, num1, num2);
                        }
                    }
                    // 递归处理（2+15-4）-4这种情况 为17-4
                    while (operators.size() > 0 && operators.peek().equals(")") && number.size() >= 2) {
                        operators.pop();
                        if (!operators.isEmpty()) {
                            oper = operators.pop();
                        }
                        if (!operators.isEmpty() && operators.peek().equals("(")) {
                            operators.pop();
                        }
                        int num1 = number.pop();
                        int num2 = number.pop();
                        cal(oper, num1, num2);
                    }
                }
            }
        }

        while (!operators.isEmpty()) {
            String oper = operators.pop();
            // 右括号处理
            if (oper.equals(")")) {
                if (!operators.isEmpty()) {
                    oper = operators.pop();
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }
            } else if (oper.equals("(")) {
                continue;
            }
            if (number.size() >= 2) {
                int num1 = number.pop();
                int num2 = number.pop();
                cal(oper, num1, num2);
            }
        }
        if (number.size() != 1 || !operators.isEmpty()) {
            throw new Error("运算错误！");
        }
        return number.peek();
    }

    public int calculate_8(String s) {
        char[] arrs = s.toCharArray();
        int res = 0, sign = 1, n = arrs.length;
        java.util.Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (arrs[i] >= '0') {
                int num = 0;
                while (i < n && arrs[i] >= '0') {
                    num = num * 10 + arrs[i++] - '0';
                }
                res += sign * num;
                i--;
            } else if (arrs[i] == '+') {
                sign = 1;
            } else if (arrs[i] == '-') {
                sign = -1;
            } else if (arrs[i] == '(') {
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            } else if (arrs[i] == ')') {
                res *= st.pop();
                res += st.pop();
            }
        }
        return res;
    }


    public int calPoints(String[] opts) {
        Stack<Integer> numbers = new Stack<>();
        for (int i = 0; i < opts.length; i++) {
            // 表示本轮获得的得分是前两轮有效 回合得分的总和。
            if (opts[i].equals("+")) {
                int num1 = numbers.pop();
                int num2 = numbers.peek();
                numbers.push(num1);
                numbers.push(num1 + num2);
            }
            // 表示本轮获得的得分是前一轮有效 回合得分的两倍。
            else if (opts[i].equals("D")) {
                int num = numbers.peek();
                numbers.push(num * 2);
            }
            // （一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
            else if (opts[i].equals("C")) {
                numbers.pop();
            } else {
                numbers.push(Integer.valueOf(opts[i]));
            }
        }
        int sum = 0;
        while (!numbers.isEmpty()) {
            sum += numbers.pop();
        }
        return sum;
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().calPoints(new String[]{"-21", "-66", "39", "+", "+"}));
//        System.out.println(new Solution().calPoints(new String[]{"-9", "-40", "-35", "D", "73"}));
        System.out.println(new Solution().calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));

//        System.out.println(new Solution().calculate_8("(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))"));
//        System.out.println(new Solution().calculate_8("(9-(0-(8-(2))))"));
//        System.out.println(new Solution()
//                .calculate_8("1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10"));

//        System.out.println(new Solution().calculate("(3-(5-(8+4)"));
        // System.out.println(new
        // Calculator().calculate("(3-(5-8-(2+(9-(0-(8-2)))-4)-4))"));

    }
}
