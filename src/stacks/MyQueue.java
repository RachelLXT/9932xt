package stacks;

/***
 * 用栈实现队列
 * 
 * @author admin
 *
 */
public class MyQueue {
	private java.util.Stack<Integer> stack2;
	private java.util.Stack<Integer> stack1;
	private int start;

	/** Initialize your data structure here. */
	public MyQueue() {
		this.stack1 = new java.util.Stack<>();
		this.stack2 = new java.util.Stack<>();
		this.start = 0;
	}

	/** Push element x to the back of queue. */
	public void push(int x) {
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		stack2.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		return stack1.pop();
	}

	/** Get the front element. */
	public int peek() {
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		return stack1.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return stack2.size() > 0;
	}
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj =
 * new MyQueue(); obj.push(x); int param_2 = obj.pop(); int param_3 =
 * obj.peek(); boolean param_4 = obj.empty();
 */