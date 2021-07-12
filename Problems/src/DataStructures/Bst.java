package DataStructures;

import DataStructures.Node.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// left <= current < right
public class Bst {
	public Node root;

	public void insert(int val) {
		Node newNode = new Node(val);

		if (root == null) {
			root = newNode;
			return;
		}

		Node n = root;
		boolean success = false;

		while (!success) {
			if (val < n.val) {

				if (n.left == null) {
					n.left = newNode;
					success = true;
				} else {
					n = n.left;
				}

			} else {

				if (n.right == null) {
					n.right = newNode;
					success = true;
				} else {
					n = n.right;
				}

			}
		}
	}

	Node insertRec(Node root, int val) {
		if (root == null) {
			root = new Node(val);
			return root;
		}

		if (val < root.val) {
			root.left = insertRec(root.left, val);
		} else if (val > root.val) {
			root.right = insertRec(root.right, val);
		}

		return root;
	}

	Node remove(int value) {
		Node parent = null, current = root;
		boolean hasLeft = false;

		if (root == null) {
			return root;
		}

		while (current != null) {
			if (current.val == value) {
				break;
			}

			parent = current;
			if (value < current.val) {
				hasLeft = true;
				current = current.left;
			} else {
				hasLeft = false;
				current = current.right;
			}
		}

		if (parent == null) {
			return remove(current);
		}

		if (hasLeft) {
			parent.left = remove(current);
		} else {
			parent.right = remove(current);
		}

		return root;
	}

	private Node remove(Node node) {

		if (node != null) {
			if (node.left == null && node.right == null) {
				return null;
			}

			if (node.left != null && node.right != null) {
				Node inOrderSuccessor = removeInOrderSuccessorDuplicate(node);
				node.val = inOrderSuccessor.val;
			} else if (node.left != null) {
				node = node.left;
			} else {
				node = node.right;
			}
		}

		return node;
	}

	private Node removeInOrderSuccessorDuplicate(Node node) {
		Node parent = node;
		node = node.right;
		boolean rightChild = node.left == null;

		while (node.left != null) {
			parent = node;
			node = node.left;
		}

		if (rightChild) {
			parent.right = node.right;
		} else {
			parent.left = node.right;
		}

		node.right = null;
		return node;
	}

	boolean find(int val) {
		while (root != null) {
			if (root.val == val) {
				return true;
			}

			if (val < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return false;
	}

	boolean findRec(Node root, int val) {
		if (root == null) {
			return false;
		}

		if (root.val == val) {
			return true;
		}

		if (val < root.val) {
			return findRec(root.left, val);
		} else if (val > root.val) {
			return findRec(root.right, val);
		}

		return false;
	}

	void traversalBfs() {
		Queue<Node> nextToVisit = new LinkedList<>();
		nextToVisit.add(root);

		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			System.out.print(node.val + " ");

			if (node.left != null) {
				nextToVisit.add(node.left);
			}

			if (node.right != null) {
				nextToVisit.add(node.right);
			}
		}
	}

	// Returns -1 if tree is null. Otherwise, returns lowest level starting from 0.
	int getHeightRec() {
		return getHeightRec(root, 0);
	}

	private int getHeightRec(Node n, int h) {
		if (n != null) {
			return Math.max(getHeightRec(n.left, h + 1), getHeightRec(n.right, h + 1));
		}

		return h - 1;
	}

	int getHeight() {
		int heightOfTree = -1;

		if (root != null) {
			Queue<Node> queue = new LinkedList<>();
			Node currentNode = root;
			queue.add(currentNode);

			while (!queue.isEmpty()) {
				heightOfTree++;

				for (int i = 0; i < queue.size(); i++) {
					currentNode = queue.remove();

					if (currentNode.left != null) {
						queue.add(currentNode.left);
					}
					if (currentNode.right != null) {
						queue.add(currentNode.right);
					}
				}
			}
		}

		return heightOfTree;
	}

	int getHeightInv() {
		return getHeightInv(root);
	}

	int getHeightInv(Node node) {
		if (node == null) {
			return -1;
		}

		return Math.max(getHeightInv(node.left), getHeightInv(node.right)) + 1;
	}

	// balanced = heights of two subtrees of any node never differ by more than 1
	boolean isBalancedRec() {
		return getBalance(root) != -1;
	}

	private int getBalance(Node n) {
		if (n == null) {
			return 0;
		}

		int leftH = getBalance(n.left);
		if (leftH == -1) {
			return -1;
		}

		int rightH = getBalance(n.right);
		if (rightH == -1) {
			return -1;
		}

		if (Math.abs(leftH - rightH) > 1) {
			return -1;
		}

		return Math.max(leftH, rightH) + 1;
	}

	private boolean isValid(Node n, Integer min, Integer max) {
		if (n == null) {
			return true;
		}

		// check that we're between min and max
		if (n.val < min || n.val > max) {
			return false;
		}

		// check that each child is either null or between min and max
		return isValid(n.left, min, n.val) && isValid(n.right, n.val + 1, max);
	}

	boolean isValid() {
		return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	Node getInorderSucc(Node node) {
		if (node == null) {
			return null;
		}

		if (node.right != null) {
			return findLeftMostChild(node.right);
		} else {
			while (node.parent != null && node.parent.left != node) {
				node = node.parent;
			}
			return node.parent;
		}
	}

	Node findLeftMostChild(Node n) {
		if (n == null) {
			return null;
		}

		while (n.left != null) {
			n = n.left;
		}
		return n;
	}

	Node findRightMostChild(Node n) {
		if (n == null) {
			return null;
		}

		while (n.right != null) {
			n = n.right;
		}
		return n;
	}

	Node flip(Node root) {
		Node curr = root;
		Node next = null;
		Node temp = null;
		Node prev = null;

		while (curr != null) {
			next = curr.left;

			curr.left = temp;
			temp = curr.right;
			curr.right = prev;

			prev = curr;
			curr = next;
		}
		return prev;
	}

	private static int count = 0;
	private static int result = 0;

	public int kthSmallest(Node root, int k) {
		count = k;
		kthSmallest(root);
		return result;
	}

	private void kthSmallest(Node root) {
		if (root != null) {
			kthSmallest(root.left);
			count--;
			if (count == 0) {
				result = root.val;
				return;
			}
			kthSmallest(root.right);
		}
	}

	int kthSmallestIter(Node root, int k) {
		Stack<Node> st = new Stack<>();

		while (root != null) {
			st.push(root);
			root = root.left;
		}

		while (k > 0) {
			Node n = st.pop();
			k--;

			if (k == 0) {
				return n.val;
			}

			Node right = n.right;
			while (right != null) {
				st.push(right);
				right = right.left;
			}
		}

		return -1;
	}

	public int kthSmallestDfs(Node root, int k) {
		int count = countNodes(root.left);
		if (k <= count) {
			return kthSmallest(root.left, k);
		} else if (k > count + 1) {
			return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
		}

		return root.val;
	}

	public int countNodes(Node n) {
		if (n == null) {
			return 0;
		}

		return 1 + countNodes(n.left) + countNodes(n.right);
	}

	public int countLeafNodes(Node n) {
		if (n == null) {
			return 0;
		}

		if (n.left == null && n.right == null) {
			return 1;
		}

		return countLeafNodes(n.left) + countLeafNodes(n.right);
	}
}