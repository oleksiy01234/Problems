
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import DataStructures.Node;
import Util.Util;

class DpGreedy {

	static long getWays(long n, long[] c, boolean memoized) {
		return memoized ? getWaysMemoized(c, n, 0, new HashMap<String, Long>()) : getWays(c, n, 0);
	}

	private static long getWays(long[] change, long total, int index) {
		if (total == 0) {
			return 1;
		}

		if (index >= change.length) {
			return 0;
		}

		long amountWithCoin = 0;
		long ways = 0;
		while (amountWithCoin <= total) {
			long remaining = total - amountWithCoin;
			ways += getWays(change, remaining, index + 1);
			amountWithCoin += change[index];
		}

		return ways;
	}

	private static long getWaysMemoized(long[] change, long total, int index, HashMap<String, Long> memo) {
		if (total == 0) {
			return 1;
		}

		if (index >= change.length) {
			return 0;
		}

		String key = total + "_" + index;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		long amountWithCoin = 0;
		long ways = 0;
		while (amountWithCoin <= total) {
			long remaining = total - amountWithCoin;
			ways += getWaysMemoized(change, remaining, index + 1, memo);
			amountWithCoin += change[index];
		}
		memo.put(key, ways);
		return ways;
	}

	static int numDecodings(String s, int k) {
		int sLength = s.length();
		if (sLength == 0) {
			return 0;
		}

		int[] memo = new int[sLength + 1];
		memo[sLength] = 1;
		memo[sLength - 1] = s.charAt(sLength - 1) == '0' ? 0 : 1;

		for (int i = sLength - 2; i >= 0; i--) {
			if (s.charAt(i) != '0') { // if 0, skip and go left
				memo[i] = memo[i + 1];
				// see if we have a 2-digit letter
				int twoDigitChar = Integer.parseInt(s.substring(i, i + 2));
				if (twoDigitChar <= 26) {
					memo[i] += memo[i + 2];
				}
			}
		}

		return memo[0];
	}

	static int countNodes(Node n) {
		if (n == null) {
			return 0;
		}

		int count = 1;

		if (n.children != null) {
			for (Node child : n.children) {
				count += countNodes(child);
			}
		}

		return count;
	}

	// O(3^n) solution
	// how many ways can we arrange 1, 2, 3 so they add up to n?
	static int countWays(int n) {
		if (n <= 2) {
			return n;
		} else if (n == 3) {
			return n + 1;
		} else {
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
		}
	}

	static int countWaysMemo(int n) {
		int[] map = new int[n + 1];
		return countWaysMemo(n, map);
	}

	static int countWaysMemo(int n, int[] a) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		}

		if (a[n] == 0) { // check if entry is empty
			a[n] = countWaysMemo(n - 1, a) + countWaysMemo(n - 2, a) + countWaysMemo(n - 3, a);
		}
		return a[n];
	}

	static int countWaysIter(int n) {
		if (n < 0) {
			return 0;
		} else if (n <= 1) {
			return 1;
		}

		int[] a = new int[n + 1];
		a[0] = 1;
		a[1] = 1;
		a[2] = 2;
		for (int i = 3; i <= n; i++) {
			a[i] = a[i - 1] + a[i - 2] + a[i - 3];
		}
		return a[n];
	}

	static int countWaysBest(int n) {
		if (n < 0) {
			return 0;
		} else if (n <= 1) {
			return 1;
		}

		int a = 1;
		int b = 1;
		int c = 2;

		for (int i = 3; i <= n; i++) {
			int count = a + b + c;
			a = b;
			b = c;
			c = count;
		}
		return c;
	}

	static int getNumberOfSubsets(int[] a) {
		int count = 1;

		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				count++;
			}
		}

		return count;
	}

	static int factorialIter(int n) {
		int res = 1;

		for (int i = 2; i <= n; i++) {
			res *= i;
		}

		return res;
	}

	static int factorialRec(int n) {
		if (n == 1) {
			return 1;
		}

		return n * factorialRec(n - 1);
	}

	static int factorialRec(int accumulator, int n) {
		if (n == 1) {
			return accumulator;
		}

		return factorialRec(n * accumulator, n - 1);
	}

	// greatest common demonimator
	static int gcdIter(int n1, int n2) {
		while (n2 != 0) {
			int temp = n2;
			n2 = n1 % n2;
			n1 = temp;
		}

		return n1;
	}

	static int gcdRec(int n1, int n2) {
		if (n2 == 0) {
			return n1;
		}

		return gcdRec(n2, n1 % n2);
	}

	static void hanoi(int discCount, char src, char tmp, char dst) {
		if (discCount == 0) {
			return;
		}

		hanoi(discCount - 1, src, dst, tmp);
		System.out.println(discCount + " from " + src + " to " + dst);
		hanoi(discCount - 1, tmp, src, dst);
	}

	static int findKth(int[] a, int k, boolean largest) {
		return select(a, 0, a.length - 1, k - 1, largest);
	}

	private static int select(int[] a, int lo, int hi, int k, boolean largest) {
		int pivot = partition(a, lo, hi, largest);

		if (pivot > k) { // kth smallest is on left
			return select(a, lo, pivot - 1, k, largest);
		} else if (pivot < k) {
			return select(a, pivot + 1, hi, k, largest);
		}

		// k == pivot: exactly k-1 items before it
		return a[k];
	}

	private static int partition(int[] a, int lo, int hi, boolean largest) {
		if (hi + lo <= 0) {

		}
		int pivot = (hi + lo) / 2;
		Util.swap(a, lo, pivot);

		for (int i = lo; i < hi; i++) {
			boolean swap = (largest && a[i] > a[hi]) || (!largest && a[i] < a[hi]);
			if (swap) {
				Util.swap(a, i, lo);
				lo++;
			}
		}

		Util.swap(a, lo, hi);
		return lo;
	}

	static void departmentNumbers() {
		int count = 0;

		for (int f = 1; f <= 7; f++) {
			for (int p = 2; p <= 7; p += 2) {
				for (int s = 1; s <= 7; s++) {
					if (f == p || p == s || f == s || f + p + s != 12) {
						continue;
					}

					System.out.println(++count + ": [" + f + "-" + p + "-" + s + "]");
				}
			}
		}
	}

	public int minDeletions(String word1, String word2) {
		int[][] m = new int[word1.length() + 1][word2.length() + 1];

		for (int i = 1; i < m.length; i++) {
			for (int k = 1; k < m[i].length; k++) {
				if (word1.charAt(i - 1) == word2.charAt(k - 1)) {
					m[i][k] = 1 + m[i - 1][k - 1];
				} else {
					m[i][k] = Math.max(m[i - 1][k], m[i][k - 1]);
				}
			}
		}

		return word1.length() + word2.length() - (m[m.length - 1][m[0].length - 1] * 2);
	}

	static int getNumberOfIslands(int[][] m) {
		int count = 0;

		for (int i = 0; i < m.length; i++) {
			for (int k = 0; k < m[i].length; k++) {
				if (m[i][k] == 1) {
					dfs(m, i, k);
					count++;
				}
			}
		}

		return count;
	}

	private static void dfs(int[][] m, int i, int k) {
		if (i < 0 || k < 0 || i > m.length - 1 || k > m[i].length - 1 || m[i][k] == 0) {
			return;
		}

		m[i][k] = 0;
		dfs(m, i - 1, k);
		dfs(m, i + 1, k);
		dfs(m, i, k - 1);
		dfs(m, i, k + 1);
	}

	public static boolean wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<>();
		for (String word : wordDict) {
			set.add(word);
		}

		boolean[][] m = new boolean[s.length()][s.length()];
		for (int length = 0; length < s.length(); length++) { //
			for (int i = 0; i < s.length() - length; i++) {
				int j = i + length;

				if (set.contains(s.substring(i, j + 1))) {
					m[i][j] = true;
				} else {
					m[i][j] = isMadeOfWords(s, i, j, m);
				}
			}
		}

		return m[0][m.length - 1];
	}

	private static boolean isMadeOfWords(String s, int i, int j, boolean[][] m) {
		for (int k = i; k < j; k++) {
			if (m[i][k] && m[k + 1][j]) {
				return true;
			}
		}

		return false;
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, ArrayList<String>> map = new HashMap<>();

		for (String s : strs) {
			String sorted = Util.sort(s);
			ArrayList<String> list = map.getOrDefault(sorted, new ArrayList<>());
			list.add(s);
			map.put(sorted, list);
		}

		return new ArrayList<>(map.values());
	}

	// max length of subsequence that contains an equal number of 0s and 1s
	public int findMaxLengthWithEqual0sAnd1s(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		int zeroes = 0;

		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				zeroes++;
			} else {
				zeroes--;
			}

			if (zeroes == 0) {
				max = i + 1;
			}

			if (map.containsKey(zeroes)) {
				max = Math.max(max, i - map.get(zeroes));
			} else {
				map.put(zeroes, i);
			}
		}
		return max;
	}

	public int maxNonAdjacentSubsetSumAkaHouseRobber(int[] a) {
		int incl = a.length > 0 ? a[0] : 0;
		int excl = 0;

		for (int i = 1; i < a.length; i++) {
			int temp = incl;
			incl = Math.max(incl, excl + a[i]);
			excl = temp;
		}

		return incl;
	}

	public int superEggDrop(int floors, int eggs) {
		int[][] grid = new int[floors + 1][eggs + 1];
		int floor = 0;

		while (grid[floor][eggs] < floors) {
			floor++;
			for (int egg = 1; egg <= eggs; egg++) {
				grid[floor][egg] = grid[floor - 1][egg - 1] + grid[floor - 1][egg] + 1;
			}
		}
		return floor;
	}

	public static int islandPerimeter(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				if (grid[i][k] == 1) {
					return dfs(grid, i, k, 0);
				}
			}
		}

		return 0;
	}

	private static int dfs(int[][] a, int row, int col, int perimeter) {
		if (row < 0 || col < 0 || row >= a.length || col >= a[row].length || a[row][col] == 0) {
			return perimeter + 1;
		}

		a[row][col] = 0;

		return dfs(a, row - 1, col, perimeter) + dfs(a, row + 1, col, perimeter) + dfs(a, row, col - 1, perimeter)
				+ dfs(a, row, col + 1, perimeter);
	}

	public int largestRectangleArea(int[] a) {
		int max = 0;
		Stack<Integer> heights = new Stack<>();
		Stack<Integer> indices = new Stack<>();

		for (int i = 0; i < a.length; i++) {
			if (heights.isEmpty() || a[i] > heights.peek()) {
				heights.push(a[i]);
				indices.push(i);
			}

			else if (a[i] < heights.peek()) {
				int startIndex = 0; // need to find start of 

				while (!heights.isEmpty() && heights.peek() > a[i]) {
					startIndex = indices.pop();
					int tempArea = heights.pop() * (i - startIndex);
					max = Math.max(max, tempArea);
				}

				heights.push(a[i]);
				indices.push(startIndex);
			}
		}

		while (!heights.isEmpty()) {
			int tempArea = heights.pop() * (a.length - indices.pop());
			max = Math.max(max, tempArea);
		}

		return max;
	}
}