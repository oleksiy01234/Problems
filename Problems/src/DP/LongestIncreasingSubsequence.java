package DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class LongestIncreasingSubsequence {

  public static void main(String[] args) {
    int[] test = new int[]{4, 3, 1, 5, 6, 2, 4, 8};
    System.out.println(longestConsecutive(test));
  }

  // O(n)
  public static int longestConsecutive(int[] a) {
    Set<Integer> set = new HashSet<>();
    for (int n : a) {
      set.add(n);
    }

    int max = 0;

    for (int n : set) {
      // start counting only from lowest element in the sequence
      // means we ignore all that aren't lowest
      if (set.contains(n - 1)) {
        continue;
      }

      // now we're looking at lowest. count up from it
      int currentLength = 0;
      while (set.contains(n)) {
        n++;
        currentLength++;
      }

      max = Math.max(max, currentLength);
    }

    return max;
  }

  // O(n log n) -- sort + walk through
  public static int longestConsecutive2(int[] a) {
    if (a.length < 2) {
      return a.length;
    }

    Arrays.sort(a);
    int max = 0;
    int currentMax = 1;

    for (int i = 1; i < a.length; i++) {
      // ignore duplicates
      if (a[i] == a[i - 1]) {
        continue;
      }

      if (a[i] == a[i - 1] + 1) {
        currentMax++;
      } else {
        max = Math.max(max, currentMax);
        currentMax = 1;
      }
    }

    return Math.max(max, currentMax);
  }
}