import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class StackQueueTest {

	static void test() {
		int[][] m = new int[][] { { 2147483647, -1, 0, 2147483647 }, { 2147483647, 2147483647, 2147483647, -1 },
				{ 2147483647, -1, 2147483647, -1 }, { 0, -1, 2147483647, 2147483647 } };

		wallsAndGates(m);
	}

	void sort(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<>();

		while (!stack.isEmpty()) {
			int tempData = stack.pop();
			while (!temp.isEmpty() && temp.peek() > tempData) {
				stack.push(temp.pop());
			}
			temp.push(tempData);
		}

		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
	}

	static void checkMatchingBrackets(String input) {
		Stack<Character> stack = new Stack<>();

		for (int j = 0; j < input.length(); j++) {

			char ch = input.charAt(j);

			if ("{[(".indexOf(ch) != -1) {
				stack.push(ch);
			} else if ("}])".indexOf(ch) != -1) {

				if (!stack.isEmpty()) {
					char chx = (char) stack.pop();
					if ((ch == '}' && chx != '{') || (ch == ']' && chx != '[') || (ch == ')' && chx != '(')) {
						System.out.println("Error: " + ch + " at " + j);
					}

				} else {
					System.out.println("Error: " + ch + " at " + j);
				}
			}
		}

		if (!stack.isEmpty()) {
			System.out.println("Error: missing right delimiter");
		}
	}

	static void towerOfHanoi() {
		Tower src = new Tower('A');
		Tower tmp = new Tower('B');
		Tower dst = new Tower('C');

		int numberOfDisks = 9;
		for (int disk = numberOfDisks - 1; disk >= 0; disk--) {
			src.push(disk);
		}

		System.out.println(src + "\n" + tmp + "\n" + dst);
		moveDisks(numberOfDisks, src, tmp, dst);
		System.out.println(src + "\n" + tmp + "\n" + dst);
	}

	private static class Tower {
		Stack<Integer> towerStack = new Stack<>();
		char name;

		Tower(char name) {
			this.name = name;
		}

		int peek() {
			return towerStack.peek();
		}

		int pop() {
			return towerStack.pop();
		}

		void push(int item) {
			towerStack.push(item);
		}

		public String toString() {
			return name + ": " + towerStack.toString();
		}
	}

	static void moveDisks(int numberOfDisks, Tower src, Tower tmp, Tower dst) {
		if (numberOfDisks <= 0) {
			return;
		}

		moveDisks(numberOfDisks - 1, src, dst, tmp);
		dst.push(src.pop());
		System.out.println(src.peek() + " from " + src.name + " to " + dst.name);
		moveDisks(numberOfDisks - 1, tmp, src, dst);
	}

	class MaxStack {
		Stack<Integer> stack;
		Stack<Integer> maxStack;

		public MaxStack() {
			stack = new Stack<>();
			maxStack = new Stack<>();
		}

		public void push(int x) {
			int max = maxStack.isEmpty() ? x : maxStack.peek();
			maxStack.push(Math.max(max, x));
			stack.push(x);
		}

		public int pop() {
			maxStack.pop();
			return stack.pop();
		}

		public int top() {
			return stack.peek();
		}

		public int peekMax() {
			return maxStack.peek();
		}

		public int popMax() {
			int max = peekMax();
			Stack<Integer> buffer = new Stack<>();
			while (top() != max) {
				buffer.push(pop());
			}
			pop();

			while (!buffer.isEmpty()) {
				push(buffer.pop());
			}
			return max;
		}
	}

	static void wallsAndGates(int[][] rooms) {
		if (rooms.length == 0) {
			return;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int row = 0; row < rooms.length; row++) {
			for (int col = 0; col < rooms[row].length; col++) {
				if (rooms[row][col] == 0) {
					q.add(row);
					q.add(col);
				}
			}
		}

		while (!q.isEmpty()) {
			int row = q.poll();
			int col = q.poll();

			addCell(rooms, row + 1, col, q, rooms[row][col] + 1);
			addCell(rooms, row - 1, col, q, rooms[row][col] + 1);
			addCell(rooms, row, col + 1, q, rooms[row][col] + 1);
			addCell(rooms, row, col - 1, q, rooms[row][col] + 1);
		}
	}

	static void addCell(int[][] m, int row, int col, Queue<Integer> q, int cost) {
		if (row < 0 || row >= m.length || col < 0 || col >= m[row].length || m[row][col] != Integer.MAX_VALUE) {
			return;
		}

		m[row][col] = cost;
		q.add(row);
		q.add(col);
	}
}