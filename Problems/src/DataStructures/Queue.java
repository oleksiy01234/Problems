package DataStructures;

class Queue {
	Node head;
	Node tail;

	boolean isEmpty() {
		return head == null;
	}

	int peek() {
		return head.val;
	}

	void add(int val) {
		Node node = new Node(val);
		if (tail != null) {
			tail.next = node;
		}
		tail = node;

		if (head == null) {
			head = tail;
		}
	}

	int remove() {
		if (head == null) {
			return -1;
		}

		int removed = head.val;
		head = head.next;
		if (head == null) {
			tail = null;
		}

		return removed;
	}
}