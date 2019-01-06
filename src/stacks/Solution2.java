//package stack;
//
//import java.util.HashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Solution {
//    private java.util.Stack<Integer> number = new java.util.Stack<>();
//    private java.util.Stack<String> operators = new java.util.Stack<>();
//    private HashMap<String, Integer> mapping = new HashMap<>();
//    private int start = 0;
//
//    public Solution() {
//        mapping.put("+", 1);
//        mapping.put("-", 1);
//        mapping.put("(", 2);
//        mapping.put(")", 3);
//    }
//
//    public static boolean isNumericZidai(String str) {
//        for (int i = 0; i < str.length(); i++) {
//            if (!Character.isDigit(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public int calculate(String[] arr, java.util.Stack<String> operators) {
//        for (; start < arr.length; start++) {
//            if (isNumericZidai(arr[start])) {
//                number.push(Integer.valueOf(arr[start]));
//            } else {
//                if (operators.isEmpty()) {
//                    operators.push(arr[start]);
//                } else {
//                    // 操作符大于栈顶元素直接push
//                    if (arr[start].equals("(")) {
//                        start++;
//                        calculate(arr, new java.util.Stack<String>());
//                    } else {
//                        String c = operators.peek();
//                        if (mapping.get(arr[start]) > mapping.get(c)) {
//                            operators.push(arr[start]);
//                        } else {
//                            String oper = operators.pop();
//                            // 右括号处理
//                            if (oper.equals(")")) {
//                                if (!operators.isEmpty()) {
//                                    oper = operators.pop();
//                                }
//                            }
//                            int num1 = number.pop();
//                            int num2 = number.pop();
//                            caling(oper, num1, num2, number);
//                            operators.push(arr[start]);
//                        }
//                    }
//                }
//            }
//        }
//        while (!operators.isEmpty()) {
//            String oper = operators.pop();
//            // 右括号处理
//            if (oper.equals(")")) {
//                if (!operators.isEmpty()) {
//                    oper = operators.pop();
//                }
//            }
//            int num1 = number.pop();
//            int num2 = number.pop();
//            caling(oper, num1, num2, number);
//        }
////        if (number.size() != 1 || !operators.isEmpty()) {
////            throw new Error("运算错误！");
////        }
//        return number.peek();
//    }
//
//    public void caling(String oper, int num1, int num2, java.util.Stack<Integer> number) {
//        if (oper.equals("+")) {
//            number.push(num2 + num1);
//        } else if (oper.equals("-")) {
//            number.push(num2 - num1);
//        }
//    }
//
//    public int calculate(String s) {
//        // 处理空格
//        StringBuilder sb = new StringBuilder(s.trim());
//        for (int i = 0; i < sb.length() - 1; i++) {
//            if ((sb.charAt(i) >= '0' && sb.charAt(i) <= '9' && sb.charAt(i + 1) != ' '
//                    && (sb.charAt(i + 1) < '0' || sb.charAt(i + 1) > '9'))
//                    || ((sb.charAt(i) < '0' || sb.charAt(i) > '9') && sb.charAt(i) != ' '
//                    && ((sb.charAt(i + 1) >= '0' && sb.charAt(i + 1) <= '9')
//                    || ((sb.charAt(i + 1) < '0' || sb.charAt(i + 1) > '9')
//                    && sb.charAt(i + 1) != ' ')))) {
//                sb = sb.insert(++i, ' ');
//            } else if (sb.charAt(i) == ' ' && sb.charAt(i + 1) == ' ') {
//                sb.deleteCharAt(i);
//            }
//        }
//        String[] arr = sb.toString().trim().split(" ");
//        return calculate(arr, operators);
//    }
//
//    public static void main(String[] args) {
////        System.out.println(new Solution().calculate("(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))"));
////        System.out.println(new Solution()
////                .calculate("1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10"));
////
//        System.out.println(new Solution().calculate("(3-(5-(8+4)"));
//        System.out.println(new Solution().calculate("1-(2+2)"));
////        System.out.println(new
////                Solution().calculate("(3-(5-8-(2+(9-(0-(8-2)))-4)-4))"));
//
//    }
//}
