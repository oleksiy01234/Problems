import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import DataStructures.Bst;
import DataStructures.Heap;
import DataStructures.Node;
import Util.Util;

class TreeTest {
	static Bst treeify(int[] array, Bst bst, int lo, int hi) {
		if (hi < lo) {
			return null;
		}

		int mid = (hi + lo) / 2;
		bst.insert(array[mid]);

		treeify(array, bst, lo, mid - 1);
		treeify(array, bst, mid + 1, hi);

		return bst;
	}

	static boolean containsTree(Node t1, Node t2) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		getOrderString(t1, sb1);
		getOrderString(t2, sb2);

		return sb1.indexOf(sb2.toString()) != -1;
	}

	private static void getOrderString(Node node, StringBuilder sb) {
		if (node == null) {
			sb.append("X");
			return;
		}

		sb.append(node.val + " ");
		getOrderString(node.left, sb);
		getOrderString(node.right, sb);
	}

	private static void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists, int lvl) {
		if (root == null) {
			return;
		}

		if (lvl == lists.size()) {
			lists.add(new LinkedList<>());
		}

		lists.get(lvl).add(root);

		createLevelLinkedList(root.left, lists, lvl + 1);
		createLevelLinkedList(root.right, lists, lvl + 1);
	}

	static ArrayList<LinkedList<Node>> createLevelLL(Node root) {
		ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
		createLevelLinkedList(root, lists, 0);
		return lists;
	}

	static ArrayList<LinkedList<Node>> createLevelLLBFS(Node root) {
		ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> currentLvl = new LinkedList<Node>();

		if (root != null) {
			currentLvl.add(root);
		}

		while (!currentLvl.isEmpty()) {
			result.add(currentLvl);
			LinkedList<Node> parents = currentLvl;
			currentLvl = new LinkedList<Node>();

			for (Node parent : parents) {
				if (parent.left != null) {
					currentLvl.add(parent.left);
				}
				if (parent.right != null) {
					currentLvl.add(parent.right);
				}
			}
		}

		return result;
	}

	static Double[] getMedians(int[] array) {
		Heap lowers = new Heap(false);
		Heap highers = new Heap(true);

		Double[] medians = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			addNumber(array[i], lowers, highers);
			rebalance(lowers, highers);
			medians[i] = getMedian(lowers, highers);
		}

		Util.print(medians);
		return medians;
	}

	static boolean isUnivalTree(Node root) {
		if (root == null) {
			return true;
		}

		if (!univalOrNull(root.left, root.val) || !univalOrNull(root.right, root.val)) {
			return false;
		}

		return isUnivalTree(root.left) && isUnivalTree(root.right);
	}

	private static boolean univalOrNull(Node n, int data) {
		return n == null || n.val == data;
	}

	static boolean isUnivalTreeDfs(Node root) {
		ArrayList<Integer> vals = new ArrayList<>();
		dfs(root, vals);

		for (int v : vals) {
			if (v != vals.get(0)) {
				return false;
			}
		}
		return true;
	}

	private static void dfs(Node node, ArrayList<Integer> list) {
		if (node != null) {
			list.add(node.val);
			dfs(node.left, list);
			dfs(node.right, list);
		}
	}

	private static void addNumber(int number, Heap lowers, Heap highers) {
		if (lowers.isEmpty() || number < lowers.peek()) {
			lowers.add(number);
		} else {
			highers.add(number);
		}
	}

	private static void rebalance(Heap lowers, Heap highers) {
		int sizeDiff = highers.getSize() - lowers.getSize();

		if (Math.abs(sizeDiff) > 1) {
			if (sizeDiff > 0) {
				lowers.add(highers.poll());
			} else {
				highers.add(lowers.poll());
			}
		}
	}

	static double getMedian(Heap lowers, Heap highers) {
		int sizeDiff = highers.getSize() - lowers.getSize();

		if (sizeDiff == 0) {
			return ((double) highers.peek() + lowers.peek()) / 2;
		} else {
			return sizeDiff > 0 ? highers.peek() : lowers.peek();
		}
	}

	static int findLargestSmallerKey(int num, Node n) {
		int largestSmaller = -1;

		while (n != null) {
			if (n.val >= num) {
				n = n.left;
			} else {
				largestSmaller = n.val;
				n = n.right;
			}
		}

		return largestSmaller;
	}

	static int kthSmallest(Node root, int k, int current) {
		if (current == k) {
			return current;
		}

		if (root != null) {
			current = kthSmallest(root.left, k, current);
			current++;
			if (current == k) {
				System.out.println(k + "th node is " + root.val);
			} else {
				current = kthSmallest(root.right, k, current);
			}
		}
		return current;
	}

	// shortest path problem using BFS
	static int getCheapestCost(Node rootNode) {
		Queue<Node> q = new LinkedList<>();
		q.add(rootNode);

		int shortestPath = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.children == null || n.children.length == 0) {
				if (n.val < shortestPath) {
					shortestPath = n.val;
				}
			} else {
				for (Node child : n.children) {
					child.val += n.val;
					if (child.val < shortestPath) {
						q.add(child);
					}
				}
			}
		}
		return shortestPath;
	}

	static int getCheapestCostDfs(Node rootNode) {
		if (rootNode.children == null || rootNode.children.length == 0) {
			return rootNode.val;
		}

		int min = Integer.MAX_VALUE;
		for (Node child : rootNode.children) {
			min = Math.min(min, getCheapestCost(child));
		}
		return min + rootNode.val;
	}

	public static Node lowestCommonAncestorNode(Node root, Node p, Node q) {
		if (!covers(root, p) || !covers(root, q)) { // Error check - one node is not in tree
			return null;
		}
		return ancestorHelper(root, p, q);
	}

	public static Node ancestorHelper(Node root, Node p, Node q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		boolean pIsOnLeft = covers(root.left, p);
		boolean qIsOnLeft = covers(root.left, q);
		if (pIsOnLeft != qIsOnLeft) { // Nodes are on different side
			return root;
		}
		Node childSide = pIsOnLeft ? root.left : root.right;
		return ancestorHelper(childSide, p, q);
	}

	public static boolean covers(Node root, Node p) {
		if (root == null) {
			return false;
		} else if (root == p) {
			return true;
		}
		return covers(root.left, p) || covers(root.right, p);
	}

	// stack implementation
	public static Node lca(Node root, int j, int k) {
		Stack<Node> pathToJ = pathToX(root, j);
		Stack<Node> pathToK = pathToX(root, k);
		if (pathToJ == null || pathToK == null) {
			return null;
		}

		Node lcaToReturn = null;

		while (!pathToJ.isEmpty() && !pathToK.isEmpty()) {
			Node jPop = pathToJ.pop();
			Node kPop = pathToK.pop();
			if (jPop != kPop) {
				break;
			}
			lcaToReturn = jPop;
		}
		return lcaToReturn;
	}

	public static Stack<Node> pathToX(Node root, int x) {
		if (root == null) {
			return null;
		}

		if (root.val == x) {
			Stack<Node> path = new Stack<>();
			path.push(root);
			return path;
		}

		Stack<Node> leftPath = pathToX(root.left, x);
		if (leftPath != null) {
			leftPath.push(root);
			return leftPath;
		}

		Stack<Node> rightPath = pathToX(root.right, x);
		if (rightPath != null) {
			rightPath.push(root);
			return rightPath;
		}

		return null;
	}

	int maxLength = Integer.MIN_VALUE;

	public int longestUnivaluePath(Node root) {
		if (root == null) {
			return 0;
		}

		longestUnivaluePathHelper(root, root.val);
		return maxLength;
	}

	private int longestUnivaluePathHelper(Node root, int prevVal) {
		if (root == null) {
			return 0;
		}

		int left = longestUnivaluePathHelper(root.left, root.val);
		int right = longestUnivaluePathHelper(root.right, root.val);

		maxLength = Math.max(maxLength, left + right);

		if (root.val == prevVal) {
			return Math.max(left, right) + 1;
		}

		return 0;
	}

	boolean counUnivalsRec(Node node) {
		if (node == null) {
			return true;
		}

		// If any of the subtrees is not unival, then this cannot be unival
		if (!counUnivalsRec(node.left) || !counUnivalsRec(node.right)) {
			return false;
		}

		if ((node.left != null && node.val != node.left.val) || (node.right != null && node.val != node.right.val)) {
			return false;
		}

		maxLength++;
		return true;
	}

	int countUnivals(Node node) {
		counUnivalsRec(node);
		return maxLength;
	}
}