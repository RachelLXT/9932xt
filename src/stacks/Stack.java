package stacks;

import java.util.HashMap;

public class Stack<T extends Comparable<T>> {
	private Object[] items;
	private int count; // 栈中元素个数
	private int n; // 栈的大小
	private static HashMap<Character, Character> mapping = new HashMap<>();
	static {
		mapping.put('}', '{');
		mapping.put(')', '(');
		mapping.put(']', '[');
	}

	public Stack(int n) {
		this.n = n;
		this.items = new Object[n];
	}

	public boolean push(char s) {
		if (count >= n) {
			return false;
		}
		items[count++] = s;
		return true;
	}

	public Object pop() {
		if (count == 0) {
			return 0;
		}
		return items[--count];
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

	public static void main(String[] args) {
		System.out.println(isValid("(){}{}[]"));
		System.out.println(isValid("]"));
	}

	public static boolean isValid(String s) {
		java.util.Stack<Character> stack = new java.util.Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!mapping.containsKey(c)) {
				stack.push(c);
			} else {
				char cur = stack.isEmpty() ? '#' : stack.pop();
				if (mapping.get(c) != cur) {
					return false;
				}
			}
		}
		return stack.size() == 0;
	}

	public static boolean match(char a, char b) {
		return a == '{' && b == '}' || a == '(' && b == ')' || a == '[' && b == ']';
	}
}
