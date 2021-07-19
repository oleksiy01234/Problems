package DataStructures;

import DataStructures.Node.Node;
import Util.Util;

class Stack {
	Node top;
	int size;

	boolean isEmpty() {
		return top == null;
	}

	void push(int n) {
		Node newTop = new Node(n);
		if (top != null) {
			newTop.min = Util.min(n, top.min);
			newTop.next = top;
		} else {
			newTop.min = n;
		}
		top = newTop;
		size++;
	}

	int peek() {
		if (top == null) {
			System.out.println("Stack is empty. Returning -1");
			return -1;
		}
		return top.val;
	}

	int pop() {
		if (top == null) {
			System.out.println("Stack is empty. Returning -1");
			return -1;
		}

		int oldData = top.val;
		top = top.next;
		size--;
		return oldData;
	}

	int getMin() {
		if (top == null) {
			System.out.println("Stack is empty. Returning -1");
			return -1;
		}
		return top.min;
	}

}