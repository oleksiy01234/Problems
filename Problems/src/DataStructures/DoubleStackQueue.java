package DataStructures;

import java.util.Stack;

class DoubleStackQueue<T> {
	Stack<Integer> stackNewestOnTop = new Stack<Integer>();
	Stack<Integer> stackOldestOnTop = new Stack<Integer>();

	void enqueue(Integer n) {
		stackNewestOnTop.push(n);
	}

	Integer dequeue() {
		shiftStacks();
		return stackOldestOnTop.pop();
	}

	Integer peek() {
		shiftStacks();
		return stackOldestOnTop.peek();
	}

	void shiftStacks() {
		if (stackOldestOnTop.isEmpty()) {
			while (!stackNewestOnTop.isEmpty()) {
				stackOldestOnTop.push(stackNewestOnTop.pop());
			}
		}
	}

	boolean isEmpty() {
		shiftStacks();
		return stackOldestOnTop.isEmpty();
	}
}