import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

import Util.Util;
import DataStructures.Point;

class StringArrayTest {

	static void test() {
	}

	static char getChar(int n) {
		return Character.forDigit(n + 9, Character.MAX_RADIX);
	}

	static void minesweeper() {
		// NOTE: The following input values will be used for testing your solution.
		int[][] field1 = { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, -1, 1, 0 } };

		Util.print(click(field1, 3, 5, 2, 2));
		// [[0, 0, 0, 0, 0],
		// [0, 1, 1, 1, 0],
		// [0, 1, -1, 1, 0]]

		Util.print(click(field1, 3, 5, 1, 4));
		// [[-2, -2, -2, -2, -2],
		// [-2, 1, 1, 1, -2],
		// [-2, 1, -1, 1, -2]]

		int[][] field2 = { { -1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, -1 } };

		Util.print(click(field2, 4, 4, 0, 1));
		// [[-1, 1, 0, 0],
		// [1, 1, 0, 0],
		// [0, 0, 1, 1],
		// [0, 0, 1, -1]]

		Util.print(click(field2, 4, 4, 1, 3));
		// [[-1, 1, -2, -2],
		// [1, 1, -2, -2],
		// [-2, -2, 1, 1],
		// [-2, -2, 1, -1]]
	}

	// bfs
	public static int[][] click(int[][] m, int nr, int nc, int r, int c) {
		if (m[r][c] != 0) {
			return m;
		}

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));

		while (!q.isEmpty()) {
			Point p = q.poll();
			m[p.row][p.col] = -2;

			for (int row = p.row - 1; row <= p.row + 1; row++) {
				for (int col = p.col - 1; col <= p.col + 1; col++) {
					if (row >= 0 && row < m.length && col >= 0 && col < m[row].length && m[row][col] == 0) {
						q.add(new Point(row, col));
					}
				}
			}

		}

		return m;
	}

	public static int[][] clickDfs(int[][] m, int nr, int nc, int r, int c) {
		if (m[r][c] == 0) {
			clear(m, r, c);
		}

		return m;
	}

	static void clear(int[][] m, int r, int c) {
		for (int row = r - 1; row <= r + 1; row++) {
			for (int col = c - 1; col <= c + 1; col++) {
				if (row >= 0 && row < m.length && col >= 0 && col < m[row].length) {
					if (m[row][col] == 0) {
						m[row][col] = -2;
						clear(m, row, col);
					}
				}
			}
		}
	}

	// min number of deletions to get rid of matching consecutive characters
	static int minDeletions(String s) {
		int num = 0;

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				num++;
			}
		}

		return num;
	}

	static int anagram(String s) {
		if (s.length() % 2 != 0) {
			return -1;
		}
		return makeAnagrams(s.substring(0, s.length() / 2), s.substring(s.length() / 2, s.length()));
	}

	static int makeAnagrams(String a, String b) {
		Map<Character, Integer> aMap = Util.fillMap(a);
		Map<Character, Integer> bMap = Util.fillMap(b);
		return getDifference(aMap, bMap) + getDifference(bMap, aMap);
	}

	private static int getDifference(Map<Character, Integer> one, Map<Character, Integer> two) {
		int count = 0;

		for (char c : one.keySet()) {
			if (two.containsKey(c)) {
				int diff = one.get(c) - two.get(c);
				count += Math.abs(diff);
				two.remove(c);
			} else {
				count += one.get(c);
			}
		}
		one.clear();

		return count;
	}

	int[][] flipAndInvertImage(int[][] A) {
		for (int row = 0; row < A.length; row++) {
			int start = 0;
			int end = A[row].length - 1;
			while (start <= end) {
				int temp = A[row][start];
				A[row][start] = Util.flip(A[row][end]);
				A[row][end] = Util.flip(temp);
				start++;
				end--;
			}
		}
		return A;
	}

	static void reverseVowels() {
		System.out.println("Type a word to reverse vowels or 0 to exit to main menu");
		String word = Util.getUserInput().nextLine();

		while (!word.equals("0")) {
			System.out.print(word + " with vowels reversed is: ");
			reverseVowels(word);
			word = Util.getUserInput().nextLine();
		}
	}

	static void reverseVowels(String str) {
		char[] charArray = str.toCharArray();
		int l = 0;
		int r = charArray.length - 1;
		boolean leftVowel;
		boolean rightVowel;

		while (l < r) {
			leftVowel = Util.isVowel(charArray[l]);
			rightVowel = Util.isVowel(charArray[r]);

			if (leftVowel && rightVowel) {
				char temp = charArray[l];
				charArray[l] = charArray[r];
				charArray[r] = temp;
				l++;
				r--;
			} else {
				if (!leftVowel) {
					l++;
				}
				if (!rightVowel) {
					r--;
				}
			}
		}
		System.out.println(charArray);
	}

	static void reverseWords(String s) {
		Stack<Integer> spaceIndices = new Stack<>();
		spaceIndices.push(0);

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				spaceIndices.push(i);
				spaceIndices.push(i + 1);
			}
		}

		spaceIndices.push(s.length());

		StringBuilder sb = new StringBuilder();

		while (!spaceIndices.isEmpty()) {
			int endIndex = spaceIndices.pop();
			int startIndex = spaceIndices.pop();
			sb.append(s.substring(startIndex, endIndex) + " ");
		}

		System.out.println(sb.toString());
	}

	static void rotateLeft(int[] a, int k) {
		Util.print(a);
		while (k < 0) {
			k += a.length;
		}
		k %= a.length;

		int[] array = Arrays.copyOf(a, a.length);

		for (int i = 0; i < array.length; i++) {
			a[i] = array[(i + k) % array.length];
		}

		Util.print(a);
		System.out.println("\n--------------------------------------------");
	}

	static void rotateLeftInPlace(int[] a, int k) {
		Util.print(a);
		while (k < 0) {
			k += a.length;
		}
		k %= a.length;

		Util.reverse(a);
		Util.reverse(a, 0, a.length - k - 1);
		Util.reverse(a, a.length - k, a.length - 1);

		Util.print(a);
		System.out.println("\n--------------------------------------------");
	}

	static void rotateRight(int[] a, int k) {
		Util.print(a);

		while (k < 0) {
			k += a.length;
		}
		k %= a.length;

		int[] temp = Arrays.copyOf(a, a.length);

		for (int i = 0; i < a.length; i++) {
			a[i] = (i - k) >= 0 ? temp[i - k] : temp[a.length + i - k];
		}

		Util.print(a);
		System.out.println("\n--------------------------------------------");
	}

	static void rotateRightInPlace(int[] a, int k) {
		Util.print(a);

		while (k < 0) {
			k += a.length;
		}
		k %= a.length;

		Util.reverse(a);
		Util.reverse(a, 0, k - 1);
		Util.reverse(a, k, a.length - 1);

		Util.print(a);
		System.out.println("\n--------------------------------------------");
	}

	static void transpose(int[][] array) {
		Util.print(array);

		int[][] newArray = new int[array[0].length][array.length];

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				newArray[j][i] = array[i][j];
			}
		}

		Util.print(newArray);
	}

	// Two strings are isomorphic if the characters in s can be replaced to get t.
	public boolean isIsomorphic(String s, String t) {
		char[] sMap = new char[256];
		char[] tMap = new char[256];
		char[] sArray = s.toCharArray();
		char[] tArray = t.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			char sChar = sArray[i];
			char tChar = tArray[i];

			if (sMap[sChar] == 0 && tMap[tChar] == 0) {
				sMap[sChar] = tChar;
				tMap[tChar] = sChar;
			} else {
				if (sMap[sChar] != tChar || tMap[tChar] != sChar) {
					return false;
				}
			}
		}
		return true;
	}

	static void printFirstDuplicate() {
		printFirstDuplicate("dobrodum");
		printFirstDuplicate("oleksiy");
		printFirstDuplicate("ollie");
	}

	static void printFirstDuplicate(String s) {
		Set<Character> set = new HashSet<>();

		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				System.out.println("First duplicate letter in " + s + " is " + c);
				return;
			}
			set.add(c);
		}

		System.out.println("No duplicates found in word " + s);
	}

	// must be done in-place, maintaining array order
	static void moveZeroes(int[] a) {
		int firstZeroIndex = 0;

		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				Util.swap(a, firstZeroIndex, i);
				firstZeroIndex++;
			}
		}

	}

	static void hasAllUniqueChars(String s) {
		boolean unique = false;
		Set<Character> set = new HashSet<>();

		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				System.out.println("not unique");
				return;
			}
			set.add(c);
		}

		System.out.println(unique);
	}

	static int smallestNonConstructibleValue(int[] a) {
		Arrays.sort(a);
		int max = 0;
		for (int i : a) {
			if (i > max) {
				break;
			}
			max += i;
		}
		return max;
	}

	static void arePermutations(String s1, String s2) {
		if (s1.length() != s2.length()) {
			System.out.println(s1 + " and " + s2 + ": false");
			return;
		}

		Vector<Character> vec = new Vector<>();

		for (char c : s1.toCharArray()) {
			vec.add(c);
		}

		for (char c : s2.toCharArray()) {
			if (vec.contains(c)) {
				vec.remove(Character.valueOf(c));
			} else {
				System.out.println(s1 + " and " + s2 + ": false");
				return;
			}
		}

		System.out.println(s1 + " and " + s2 + ": true");
	}

	static void urlify(char[] s, int trueLength) {
		int spaceCount = 0;

		for (int i = 0; i < trueLength; i++) {
			if (s[i] == ' ') {
				spaceCount++;
			}
		}

		int index = trueLength + spaceCount * 2;

		if (trueLength < spaceCount) {
			s[trueLength] = '\0';
		}

		for (int i = trueLength - 1; i >= 0; i--) {
			if (s[i] == ' ') {
				s[index - 1] = '0';
				s[index - 2] = '2';
				s[index - 3] = '%';
				index -= 3;
			} else {
				s[index - 1] = s[i];
				index--;
			}
		}

		System.out.println(s);
	}

	static void isPermutationOfPalindrome(String str) {
		Set<Character> oddChars = new HashSet<>();
		int count = 0;

		for (Character c : str.toCharArray()) {
			if (c == ' ') {
				continue;
			}

			if (oddChars.contains(c)) {
				oddChars.remove(c);
				count--;
			} else {
				oddChars.add(c);
				count++;
			}
		}

		if (count <= 1) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

	}

	static void compress(String s) {
		System.out.println(s);

		int start = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(start));

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(start)) {
				if (i - start > 1) {
					sb.append(i - start);
				}
				sb.append(s.charAt(i));
				start = i;
			}
		}

		if (s.length() - start > 1) {
			sb.append(s.length() - start);
		}

		System.out.println(sb);
		System.out.println();
	}

	public static int[][] rotate(int[][] a) {
		int n = a.length;
		int[][] rotated = new int[n][n];

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				rotated[row][col] = a[n - col - 1][row];
			}
		}

		return rotated;
	}

	static boolean rotateInPlace(int[][] a) {
		int n = a.length;

		if (n == 0 || n != a[0].length) {
			return false;
		}

		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;

			for (int i = first; i < last; i++) {
				int offset = i - first;
				int top = a[first][i]; // save top
				a[first][i] = a[last - offset][first]; // top = left
				a[last - offset][first] = a[last][last - offset]; // left = bottom
				a[last][last - offset] = a[i][last]; // bottom = right
				a[i][last] = top; // right = top
			}
		}

		return true;
	}

	static void zeroMatrix(int[][] matrix) {

		Util.print(matrix);

		Set<Integer> zeroCols = new HashSet<>();

		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix[i].length; k++) {
				if (!zeroCols.contains(k) && matrix[i][k] == 0) {
					setToZero(matrix, i, k);
					zeroCols.add(k);
					break;
				}
			}
		}

		Util.print(matrix);
	}

	private static void setToZero(int[][] m, int row, int col) {

		System.out.println("setting to zero");

		for (int i = 0; i < m[row].length; i++) {
			m[row][i] = 0;
		}

		for (int i = 0; i < m.length; i++) {
			m[i][col] = 0;
		}

	}

	static void checkRotation(String s1, String s2) {

		if ((s1 + s1).contains(s2)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

	static long substrCount(int n, String s) {
		long count = s.length();

		for (int i = 0; i < s.length(); i++) {
			char startChar = s.charAt(i);
			int diffCharIdx = -1;

			for (int j = i + 1; j < s.length(); j++) {
				char currChar = s.charAt(j);

				if (startChar == currChar) {
					if ((diffCharIdx == -1) || (j - diffCharIdx) == (diffCharIdx - i)) {
						count++;
					}
				} else {
					if (diffCharIdx == -1) {
						diffCharIdx = j;
					} else {
						break;
					}
				}
			}
		}
		return count;
	}

	static void merge(int[] a, int[] b, int countA, int countB) {
		int indexMerged = countB + countA - 1; /* Index of last location of merged array */
		int indexA = countA - 1; /* Index of last element in array b */
		int indexB = countB - 1; /* Index of last element in array a */

		/* Merge a and b, starting from the last element in each */
		while (indexB >= 0) {
			if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of A is bigger than end of B */
				a[indexMerged] = a[indexA]; // copy element
				indexA--;
			} else {
				a[indexMerged] = b[indexB]; // copy element
				indexB--;
			}
			indexMerged--; // move indices
		}
	}

	static boolean isSpecialPalindrome(String s) {
		boolean even = s.length() % 2 == 0;
		final char c = s.charAt(0);

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != c) {
				if (even || i != s.length() / 2) {
					return false;
				}
			}
		}

		return true;
	}

	static void whatFlavors(int[] cost, int money) {
		Map<Integer, Integer> costSet = new HashMap<>();

		for (int i = 0; i < cost.length; i++) {
			if (costSet.containsKey(money - cost[i])) {
				System.out.println(costSet.get(money - cost[i]) + " " + (i + 1));
				return;
			}

			costSet.put(cost[i], i + 1);
		}
	}

	static int[] plusOne(int[] a) {
		boolean carry = true;

		for (int i = a.length - 1; i >= 0; i--) {
			if (carry) {
				a[i]++;
			}

			carry = a[i] == 10;
			a[i] %= 10;
		}

		if (carry) {
			int[] newA = new int[a.length + 1];
			newA[0] = 1;
			return newA;
		}

		return a;
	}

	static int lengthOfLongestSubstringOfRepeatingChars(char[] s) {
		Set<Character> set = new HashSet<>();
		int max = 0;
		int start = 0;
		int end = 0;

		while (end < s.length) {
			if (!set.contains(s[end])) {
				set.add(s[end]);
				end++;
				max = Math.max(max, set.size());
			} else {
				set.remove(s[start]);
				start++;
			}
		}

		return max;
	}

	static boolean ransomNote(String[] magazine, String[] note) {
		HashMap<String, Integer> noteMap = new HashMap<>();

		for (String s : note) {
			if (noteMap.containsKey(s)) {
				noteMap.put(s, noteMap.get(s) + 1);
			} else {
				noteMap.put(s, 1);
			}
		}

		for (String s : magazine) {
			if (noteMap.containsKey(s)) {
				if (noteMap.get(s) - 1 == 0) {
					noteMap.remove(s);
				} else {
					noteMap.put(s, noteMap.get(s) - 1);
				}
			}
		}
		return noteMap.isEmpty();
	}

	static int sherlockAndAnagrams(String s) {
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				char[] sArray = s.substring(i, j).toCharArray();
				Arrays.sort(sArray);
				String str = new String(sArray);
				map.put(str, map.getOrDefault(str, 0) + 1);
			}
		}

		int count = 0;
		for (String str : map.keySet()) {
			int c = map.get(str);
			count += (c * (c - 1) / 2);
		}
		return count;
	}

	static void combine(String s) {
		combine(0, s, new StringBuilder());
	}

	private static void combine(int start, String s, StringBuilder sb) {
		for (int i = start; i < s.length(); i++) {
			sb.append(s.charAt(i));
			System.out.println(sb);
			if (i < s.length()) {
				combine(i + 1, s, sb);
			}
			sb.setLength(sb.length() - 1);
		}
	}

	static void printNumberWords(int[] phoneNum) {
		printNumberWords(0, phoneNum, new char[phoneNum.length]);
		System.out.println();
		printNumberWordsIter(phoneNum, new char[phoneNum.length]);
	}

	private static void printNumberWords(int curDigit, int[] phoneNum, char[] result) {
		if (curDigit == phoneNum.length) {
			System.out.println(new String(result));
			return;
		}

		for (int i = 0; i < chars[phoneNum[curDigit]].length; ++i) {
			result[curDigit] = chars[phoneNum[curDigit]][i];
			printNumberWords(curDigit + 1, phoneNum, result);
			if (phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1) {
				return;
			}
		}
	}

	private static void printNumberWordsIter(int[] phoneNum, char[] result) {
		// Initialize result with first telephone word
		for (int i = 0; i < phoneNum.length; ++i) {
			result[i] = chars[phoneNum[i]][0];
		}

		while (true) {
			System.out.println(new String(result));
			for (int i = phoneNum.length - 1; i >= -1; --i) {
				if (i == -1) {
					return;
				}

				if (chars[phoneNum[i]][2] == result[i] || phoneNum[i] == 0 || phoneNum[i] == 1) {
					result[i] = chars[phoneNum[i]][0];
				} else if (chars[phoneNum[i]][0] == result[i]) {
					result[i] = chars[phoneNum[i]][1];
					break;
				} else if (chars[phoneNum[i]][1] == result[i]) {
					result[i] = chars[phoneNum[i]][2];
					break;
				}
			}
		}
	}

	public List<Integer> spiralOrder(int[][] m) {
		List<Integer> list = new ArrayList<>();
		if (m.length == 0) {
			return list;
		}

		int size = m.length * m[0].length;
		int left = 0, right = m[0].length - 1, top = 0, bottom = m.length - 1;

		while (list.size() < size) {
			if (top <= bottom) {
				for (int i = left; i <= right; i++) {
					list.add(m[top][i]);
				}
				top++;
			}

			if (left <= right) {
				for (int i = top; i <= bottom; i++) {
					list.add(m[i][right]);
				}
				right--;
			}

			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					list.add(m[bottom][i]);
				}
				bottom--;
			}

			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					list.add(m[i][left]);
				}
				left++;
			}
		}

		return list;
	}

	private static char[][] chars = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
			{ 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	/*
	 * Given int[], find the first missing positive int in linear time and constant
	 * space. I.e. find the lowest positive int that does not exist in the array.
	 * Input can contain duplicates and negative numbers as well. E.g. [3, 4, -1, 1]
	 * -> 2. [1,2,0] -> 3.
	 */
	static int findFirstMissingPositiveInt(int[] a) {
		if (a.length == 0) {
			return 1;
		}

		int i = 0;
		while (i < a.length) {
			if (a[i] >= 0 && a[i] < a.length && a[a[i]] != a[i]) {
				Util.swap(a, i, a[i]);
			} else {
				i++;
			}
		}

		int k = 1;
		while (k < a.length && a[k] == k) {
			k++;
		}

		if (k < a.length) {
			return k;
		} else {
			return a[0] == k ? k + 1 : k;
		}
	}

	// this works for sorted arrays
	static int findFirstMissingPositiveSorted(int[] a) {
		if (a.length == 0) {
			return -1;
		}

		int lo = 0;
		int hi = a.length - 1;

		while (lo < hi) {
			if (lo != a[lo] && a[lo] >= 0) {
				return lo;
			}

			int mid = (lo + hi) / 2;
			if (mid == a[mid]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return a[a.length - 1] + 1;
	}

	static String getShortestUniqueSubstring(char[] arr, String str) {
		int headIndex = 0;
		String result = "";
		int uniqueCount = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (char c : arr) {
			map.put(c, 0);
		}

		for (int tailIndex = 0; tailIndex < str.length(); tailIndex++) {
			char tail = str.charAt(tailIndex);

			if (!map.containsKey(tail)) {
				continue;
			}

			if (map.get(tail) == 0) {
				uniqueCount++;
			}

			map.put(tail, map.get(tail) + 1);

			while (uniqueCount == arr.length) {
				int tempLength = tailIndex - headIndex + 1;
				if (tempLength == arr.length) {
					return str.substring(headIndex, tailIndex + 1);
				}

				if (result.equals("") || tempLength < result.length()) {
					result = str.substring(headIndex, tailIndex + 1);
				}

				char headChar = str.charAt(headIndex);

				if (map.containsKey(headChar)) {
					int headCount = map.get(headChar) - 1;
					if (headCount == 0) {
						uniqueCount--;
					}
					map.put(headChar, headCount);
				}

				headIndex++;
			}
		}

		return result;
	}

	public static Integer mostFreqent(int[] givenArray) {
		int maxCount = -1;
		Integer maxItem = null;
		HashMap<Integer, Integer> count = new HashMap<>();

		for (int i : givenArray) {
			if (count.containsKey(i)) {
				count.put(i, count.get(i) + 1);
			} else {
				count.put(i, 1);
			}

			if (count.get(i) > maxCount) {
				maxCount = count.get(i);
				maxItem = i;
			}
		}

		return maxItem;
	}

	public static boolean isMatch(String s, String p) {
		int sIndex = 0;
		int pIndex = 0;

		while (sIndex < s.length()) {
			if (pIndex >= p.length()) {
				return false;
			}

			if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
				while (sIndex < s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
					sIndex++;
				}
				pIndex += 2;
				continue;
			}

			if (s.charAt(sIndex) != p.charAt(pIndex) && p.charAt(pIndex) != '.') {
				return false;
			}

			sIndex++;
			pIndex++;
		}

		return true;
	}

	public static int getMaxProfit(int[] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("Getting a profit requires at least 1 price");
		}

		int minPrice = a[0];
		int maxProfit = -minPrice;

		for (int i = 1; i < a.length; i++) {
			maxProfit = Math.max(maxProfit, a[i] - minPrice);
			minPrice = Math.min(minPrice, a[i]);
		}

		return maxProfit;
	}

	public int[] shortestToChar(String s, char c) {
		int[] res = new int[s.length()];
		int index = -s.length();

		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == c) {
				index = i;
			}
			res[i] = i - index;
		}

		for (int i = s.length() - 1; i >= 0; --i) {
			if (s.charAt(i) == c) {
				index = i;
			}

			res[i] = Math.min(res[i], Math.abs(i - index));
		}
		return res;
	}

	static int highestPopulationYear(int[][] birthsAndDeaths) {
		Map<Integer, Integer> years = new TreeMap<>();
		for (int[] person : birthsAndDeaths) {
			years.put(person[0], years.getOrDefault(person[0], 0) + 1);
			years.put(person[1], years.getOrDefault(person[1], 0) - 1);
		}

		int maxYear = -1;
		int maxPopulation = Integer.MIN_VALUE;
		int currentPopulation = 0;

		for (Map.Entry<Integer, Integer> yearEntry : years.entrySet()) {
			currentPopulation += yearEntry.getValue();

			if (currentPopulation > maxPopulation) {
				maxYear = yearEntry.getKey();
				maxPopulation = currentPopulation;
			}
		}

		return maxYear;
	}

	public int repeatedStringMatch(String a, String b) {
		StringBuilder sb = new StringBuilder();

		while (sb.length() <= b.length() + a.length()) {
			sb.append(a);
			if (sb.toString().contains(b)) {
				return sb.length() / a.length();
			}
		}

		return -1;
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() < 2) {
			return intervals;
		}

		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		List<Interval> merged = new ArrayList<>();
		Interval last = intervals.get(0);

		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.start <= last.end) {
				if (current.end > last.end) {
					last.end = current.end;
				}
			} else {
				merged.add(last);
				last = current;
			}
		}

		merged.add(last);
		return merged;
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		char[] a = s.toCharArray();
		int maxLength = 0;
		Map<Character, Integer> map = new HashMap<>();
		int start = 0, end = 0;

		while (end < s.length()) {
			map.put(a[end], map.getOrDefault(a[end], 0) + 1);

			while (map.size() > k) {
				map.put(a[start], map.get(a[start]) - 1);
				map.remove(a[start], 0);
				start++;
			}

			end++;
			maxLength = Math.max(maxLength, end - start);
		}

		return maxLength;
	}

	static String minWindow(String s, String t) {
		String min = "";

		Map<Character, Integer> tMap = Util.fillMap(t);
		Map<Character, Integer> sMapTemp = new HashMap<>(tMap);
		Map<Character, Integer> sMap = new HashMap<>();

		int start = 0;
		int end = 0;

		while (true) {
			// 0. Move start until first desirable letter
			while (start <= s.length() - t.length() && !tMap.containsKey(s.charAt(start))) {
				start++;
			}

			if (start > s.length() - t.length()) {
				break;
			}

			if (end < start) {
				end = start;
			}

			// 1. Look for valid window
			while (end < s.length() && !sMapTemp.isEmpty()) {
				char c = s.charAt(end);
				if (tMap.containsKey(c)) {
					sMapTemp.put(c, sMapTemp.getOrDefault(c, 1) - 1);
					sMapTemp.remove(c, 0);
					sMap.put(c, sMap.getOrDefault(c, 0) + 1);
				}
				end++;
			}

			// 2. If no valid window, break
			if (!sMapTemp.isEmpty()) {
				System.out.println("not empty :(");
				break;
			}

			// 3. Save window
			if (min.isEmpty() || min.length() > end - start) {
				min = s.substring(start, end);
				if (min.length() == t.length()) {
					break;
				}
			}

			// 4. Move start pointer once
			char startChar = s.charAt(start);
			sMap.put(startChar, sMap.get(startChar) - 1);
			if (sMap.get(startChar) < tMap.get(startChar)) {
				sMapTemp.put(startChar, 1);
			}
			start++;
		}

		return min;
	}
}