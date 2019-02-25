package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Combinations {
  public static void main(String[] args) {
    System.out.println(subsets(new int[] { 1, 2, 2, 3 }).toString());
    System.out.println(subsets2(new int[] { 1, 2, 2, 3 }).toString());
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
  static List<List<Integer>> subsets2(int[] a) {
    List<List<Integer>> res = new ArrayList<>();
    generateSubsets2(a, new ArrayList<>(), 0, res);
    return res;
  }

  static void generateSubsets2(int[] a, List<Integer> chosen, int index, List<List<Integer>> subsets) {
    if (index == a.length) {
      subsets.add(new ArrayList<>(chosen));
      return;
    }

    chosen.add(a[index]);
    generateSubsets2(a, chosen, index + 1, subsets);
    chosen.remove(chosen.size() - 1);
    generateSubsets2(a, chosen, index + 1, subsets);
  }
}