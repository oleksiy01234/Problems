package DP;

import java.util.Arrays;

class LongestIncreasingSubsequence {

  public static void main(String[] args) {
    int[] test = new int[] { 4, 3, 1, 5, 6, 2, 4, 8 };
    System.out.println(lengthOfLIS(test));
    System.out.println(lengthOfLISBinarySearch(test));
  }

  /**
   * This algorithm is O(n log n)
   */
  private static int lengthOfLISBinarySearch(int[] a) {
    int[] dp = new int[a.length];
    int max = 0;

    for (int n : a) {
      int index = Arrays.binarySearch(dp, 0, max, n);
      if (index < 0) {
        // returns -1 if should've been at 0
        // returns -2 if should've been at 1, etc
        index = Math.abs(index) - 1;
      }

      dp[index] = n;
      max = Math.max(max, index + 1);
    }

    return max;
  }

  /**
   * This algorithm is O(n^2)
   */
  private static int lengthOfLIS(int[] a) {
    int[] lis = new int[a.length];
    int max = 0;

    for (int i = 0; i < a.length; i++) {
      lis[i] = 1;

      for (int k = 0; k < i; k++) {
        if (a[k] < a[i] && lis[k] >= lis[i]) {
          lis[i] = 1 + lis[k];
        }
      }

      max = Math.max(max, lis[i]);
    }

    return max;
  }
}