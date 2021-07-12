package Recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. Combinations
 * https://leetcode.com/problems/combinations/
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
public class Combinations {
  public static void main(String[] args) {
    List<List<Integer>> res = combine(3, 5);
    res.forEach(System.out::println);
  }

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    combine(1, n, k, new LinkedList<>(), res);
    return res;
  }

  private static void combine(int start, int end, int k, LinkedList<Integer> combo, List<List<Integer>> res) {
    if (combo.size() == k) {
      res.add(new ArrayList<>(combo));
      return;
    }

    for (int i = start; i <= end; i++) {
      combo.add(i);
      combine(i + 1, end, k, combo, res);
      combo.removeLast();
    }
  }
}
