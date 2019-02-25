package DP;

/**
 * MaxSumSubarray
 */
public class MaxSumSubarray {

  static int maxSubArray(int[] a) {
    int currentMax = a[0];
    int totalMax = currentMax;

    for (int i = 1; i < a.length; i++) {
      currentMax = Math.max(a[i], currentMax + a[i]);
      totalMax = Math.max(totalMax, currentMax);
    }

    return totalMax;
  }

}