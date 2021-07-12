import java.util.Comparator;
import java.util.PriorityQueue;

import DataStructures.LRUCache;
import DataStructures.Node.Node;
import Util.Util;

class LinkedListTest {

	static void test() {
		LRUCache c = new LRUCache(2);
		int x = Util.getUserInput().nextInt();

		while (x != -1) {
			if (x == 0) {
				System.out.println("New Cache Capacity: ");
				c = new LRUCache(Util.getUserInput().nextInt());
			} else if (x == 1) {
				System.out.println("Put key/value: ");
				c.put(Util.getUserInput().nextInt(), Util.getUserInput().nextInt());
			} else if (x == 2) {
				System.out.println("Get: ");
				System.out.println(c.get(Util.getUserInput().nextInt()));
			} else if (x == 4) {
				c.printQ("");
				c.printHeadTail();
			}
			x = Util.getUserInput().nextInt();
		}
	}

	static Node findLoopBeginning(Node head) {
		Node slow = head;
		Node fast = head;

		/* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {// Collision
				break;
			}
		}

		/* Error check - no meeting point, and therefore no loop */
		if (fast == null || fast.next == null) {
			return null;
		}

		/*
		 * Move slow to Head. Keep fast at Meeting Point. Each are k steps from the Loop
		 * Start. If they move at the same pace, they must meet at Loop Start.
		 */
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		/* Both now point to the start of the loop. */
		return fast;
	}

	static int findKthToLastRec(Node head, int k) {
		if (head == null) {
			return 0;
		}

		int index = findKthToLastRec(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.val);
		}

		return index;
	}

	Node mergeTwoListsIter(Node l1, Node l2) {
		// node ahead of head
		Node preHead = new Node(0);
		Node merged = preHead;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				merged.next = l1;
				l1 = l1.next;
			} else {
				merged.next = l2;
				l2 = l2.next;
			}
			merged = merged.next;
		}

		merged.next = l1 != null ? l1 : l2;
		return preHead.next;
	}

	Node mergeTwoLists(Node l1, Node l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	public Node mergeKLists(Node[] lists) {
		PriorityQueue<Node> q = new PriorityQueue<Node>(lists.length, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.val - o2.val;
			}
		});

		for (Node n : lists) {
			q.add(n);
		}

		Node res = new Node();
		Node n = res;

		do {
			n.next = q.peek();
			Node next = q.poll().next;
			if (next != null) {
				q.add(next);
			}
			n = n.next;
		} while (!q.isEmpty());

		return res.next;
	}

	public Node insert(Node head, int insertVal) {
		Node n = new Node(insertVal);

		if (head == null) {
			n.next = n;
			head = n;
		} else if (head.next == head) {
			head.next = n;
			n.next = head;
		} else { // 2+ elements
			Node prev = head;
			Node next = head.next;

			while (true) {
				boolean cycleComplete = next == head;
				boolean middleCase = prev.val <= n.val && next.val >= n.val;
				boolean endCase = next.val < prev.val && (n.val <= next.val || n.val >= prev.val);

				if (cycleComplete || middleCase || endCase) {
					prev.next = n;
					n.next = next;
					break;
				}

				prev = next;
				next = next.next;
			}
		}

		return head;
	}

	public Node deleteDuplicates(Node n) {
		Node preHead = new Node(0);
		Node r = preHead;

		while (n != null) {
			if (hasNextDuplicate(n)) {
				while (hasNextDuplicate(n)) {
					n.next = n.next.next;
				}
			} else {
				r.next = n;
				r = r.next;
			}

			n = n.next;
		}

		r.next = null;
		return preHead.next;
	}

	private boolean hasNextDuplicate(Node n) {
		return n.next != null && n.val == n.next.val;
	}
}