package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. Subsets
 * https://leetcode.com/problems/subsets/
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
class Subsets {
  public static void main(String[] args) {
    System.out.println(subsets(new int[]{1, 2, 2, 3}));
    System.out.println(subsets2(new int[]{1, 2, 2, 3}));
  }

  static List<List<Integer>> subsets(int[] a) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(a); // for duplicate detection
    generateSubsets(res, new ArrayList<>(), a, 0);
    return res;
  }

  static void generateSubsets(List<List<Integer>> res, List<Integer> curr, int[] a, int start) {
    res.add(new ArrayList<>(curr));

    for (int i = start; i < a.length; i++) {
      if (i > start && a[i] == a[i - 1]) {
        continue; // for duplicate detection
      }

      curr.add(a[i]);
      generateSubsets(res, curr, a, i + 1);
      curr.remove(curr.size() - 1);
    }
  }

  /**
   * Method 2: no duplicate detection.
   */
  public static List<List<Integer>> subsets2(int[] a) {
    List<List<Integer>> res = new ArrayList<>();
    generateSubsets2(0, a, new LinkedList<>(), res);
    return res;
  }

  private static void generateSubsets2(int index, int[] a, LinkedList<Integer> subset, List<List<Integer>> res) {
    if (index == a.length) {
      res.add(new ArrayList<>(subset));
      return;
    }

    subset.add(a[index]);
    generateSubsets2(index + 1, a, subset, res);

    subset.removeLast();
    generateSubsets2(index + 1, a, subset, res);
  }
}