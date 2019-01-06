package stacks;

import java.util.HashMap;

public class Test {
	private HashMap<String, Integer> mapping = new HashMap<>();

	public Test() {
		mapping.put("+", 1);
		mapping.put("-", 1);
		mapping.put("(", 1);
		mapping.put(")", 3);
	}

	public static void main(String[] args) {
		System.out.println(new Test().mapping.get("+"));
	}
}
