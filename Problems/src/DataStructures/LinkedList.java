package DataStructures;

import java.util.HashSet;
import java.util.Set;

import Util.Util;

class LinkedList {
	Node head;

	LinkedList() {
	}

	LinkedList(int data) {
		append(data);
	}

	LinkedList append(int data) {
		return append(new Node(data));
	}

	LinkedList append(Node n) {
		if (head == null) {
			head = n;
			return this;
		}

		Node end = head;

		while (end.next != null) {
			end = end.next;
		}

		end.next = n;
		return this;
	}

	void prepend(int data) {
		Node newHead = new Node(data);
		newHead.next = head;
		head = newHead;
	}

	Node isCircular() {
		if (head == null) {
			return null;
		}

		Node fast = head.next;
		Node slow = head;

		while (fast != null && fast.next != null) {
			if (fast == slow) {
				return fast;
			}

			fast = fast.next.next;
			slow = slow.next;
		}

		return null;
	}

	void print() {
		Node c = head;
		Node circle = isCircular();
		boolean palindrome = isPalindrome(head);

		while (c != null && c != circle) {
			System.out.print(c.val + "->");
			c = c.next;
		}

		System.out.println(circle != null ? "Circular" : "");
		System.out.println(palindrome ? "Palindrome" : "");
	}

	void makeLoopTo(int data) {
		Node c = head;
		Node loopLoc = null;

		while (c.next != null) {
			c = c.next;
			if (c.val == data) {
				loopLoc = c;
			}
		}

		c.next = loopLoc;
	}

	boolean isPalindrome(Node head) {
		Node reversed = reverseAndClone(head);
		return isEqual(head, reversed);
	}

	boolean isPalindromeIterative(Node head) {
		Node fast = head;
		Node slow = head;

		Stack stack = new Stack();

		while (fast != null && fast.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}

		// Has odd number of elements, so skip the middle element
		if (fast != null) {
			slow = slow.next;
		}

		while (slow != null) {
			int top = stack.pop();

			if (top != slow.val) {
				return false;
			}

			slow = slow.next;
		}
		return true;
	}

	Node reverseAndClone(Node node) {
		Node head = null;
		while (node != null) {
			Node n = new Node(node.val); // Clone
			n.next = head;
			head = n;
			node = node.next;
		}
		return head;
	}

	boolean isEqual(Node one, Node two) {
		while (one != null && two != null) {
			if (one.val != two.val) {
				return false;
			}
			one = one.next;
			two = two.next;
		}
		return one == null && two == null;
	}

	Node reverse() {
		if (head != null) {
			Node current = head.next;
			head.next = null;

			while (current != null) {
				Node next = current.next;
				current.next = head;
				head = current;
				current = next;
			}
		}
		return head;
	}

	void removeDuplicates(boolean usingSet) {
		if (head == null || head.next == null) {
			return;
		}

		Set<Integer> numbers = new HashSet<>();

		Node n = head;
		numbers.add(n.val);

		while (n.next != null) {
			if (numbers.contains(n.next.val)) {
				n.next = n.next.next;
			} else {
				numbers.add(n.next.val);
				n = n.next;
			}

		}
	}

	Node findKthToLast(int k) {
		Node n = head;
		for (int i = 0; i <= k; i++) {
			if (n == null) {
				return null;
			}
			n = n.next;
		}

		Node kNode = head;

		while (n != null) {
			n = n.next;
			kNode = kNode.next;
		}

		return kNode;
	}

	void remove(int data) {
		if (head == null) {
			return;
		}

		if (head.val == data) {
			head = head.next;
			return;
		}

		Node n = head;
		while (n.next != null) {
			if (n.next.val == data) {
				n.next = n.next.next;
				return;
			}
			n = n.next;
		}
	}

	void remove(Node node) {
		if (head == null) {
			return;
		}

		Node n = head;
		while (n.next != null) {
			if (n.next == node) {
				n.next = n.next.next;
				return;
			}
			n = n.next;
		}
	}

	static Node removeDuplicates(Node head) {
		if (head == null) {
			return head;
		}

		Node n = head;
		while (n.next != null) {
			if (n.next.val == n.val) {
				n.next = n.next.next;
			} else {
				n = n.next;
			}
		}

		return head;
	}

	void removeNodeWithoutHead(Node node) {
		if (node == null || node.next == null) {
			return;
		}

		node.val = node.next.val;
		node.next = node.next.next;
	}

	Node getRandomNode() {
		int number = Util.constructInt(getLength()) + 1;

		Node r = head;
		for (int i = 0; i < number; i++) {
			r = r.next;
		}

		return r;
	}

	int getLength() {
		int count = 0;

		Node r = head;
		while (r.next != null) {
			count++;
			r = r.next;
		}

		return count;
	}

	// put all values less than x before values >= x
	void partition(int x) {
		System.out.println("Partition: " + x);
		Node n = head;
		boolean partitionFound = head.val >= x;

		while (n.next != null) {
			if (n.next.val < x) {
				if (partitionFound) {
					// move n.next to the beginning
					Node temp = n.next.next;
					n.next.next = head;
					head = n.next;
					n.next = temp;
				} else {
					n = n.next;
				}
			} else {
				partitionFound = true;
				n = n.next;
			}
		}
	}
}