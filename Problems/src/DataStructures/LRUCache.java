package DataStructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	Map<Integer, Node> map = new HashMap<>();
	Node head = null;
	Node tail = null;
	int max;

	public LRUCache(int capacity) {
		max = capacity;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			remove(n);
			prepend(n);
			return n.val;
		}

		return -1;
	}

	public void put(int key, int val) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val = val;
			remove(node);
			prepend(node);
		} else {
			if (map.size() == max) {
				map.remove(tail.key);
				remove(tail);
			}

			Node n = new Node(key, val);
			map.put(key, n);
			prepend(n);
		}
	}

	public void prepend(Node n) {
		printQ("before prepend ");

		if (head == null) {
			head = n;
			tail = n;
		} else {
			n.next = head;
			n.prev = null;
			head.prev = n;
			head = n;
		}

		printQ("after prepend ");
	}

	public void remove(Node n) {
		if (n == tail) {
			tail = n.prev;
		}
		if (n == head) {
			head = n.next;
		}

		if (n.prev != null) {
			n.prev.next = n.next;
		}

		if (n.next != null) {
			n.next.prev = n.prev;
		}

		n.next = null;
		n.prev = null;
	}

	public void printQ(String message) {
		System.out.print(message);
		printFromHead();
		printFromTail();
		System.out.println();
	}

	public void printFromHead() {
		Node n = head;
		System.out.print(". Forward: ");

		while (n != null) {
			System.out.print(n.key + "->");
			n = n.next;
		}
	}

	public void printFromTail() {
		Node n = tail;
		System.out.print(". Backward: ");
		StringBuilder sb = new StringBuilder();

		while (n != null) {
			sb.insert(0, "<-" + n.key);
			n = n.prev;
		}

		System.out.print(sb.toString());
	}

	public void printHeadTail() {
		System.out.println("Head: " + (head == null ? "null" : head.val));
		System.out.println("Tail: " + (tail == null ? "null" : tail.val));
	}
}