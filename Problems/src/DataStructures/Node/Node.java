package DataStructures.Node;

public class Node {
	public int val;
	public int key;
	public Node right;
	public Node left;
	public Node prev;
	public Node next;
	public Node parent;
	public int min;
	public int max;
	public Node[] children;
	public static int count = 0;

	public Node(int d) {
		val = d;
	}

	public Node(int key, int val) {
		this.key = key;
		this.val = val;
	}

	public Node() {
		count++;
		System.out.println(count);
		val = 0;
	}

	public void addChildren(int num) {
		children = new Node[num];
		for (int i = 0; i < num; i++) {
			children[i] = new Node();
		}
	}
}