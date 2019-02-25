package DP;

import java.util.Arrays;
import java.util.Comparator;

public class MaxPairChain {

  /**
   * in any pair [a, b], a < b
   * [a, b], [c, d] make a chain iff b < c
   */
  public int findLongestChain(int[][] pairs) {
    // sort by second element
    Arrays.sort(pairs, new Comparator<int[]>() {
      public int compare(int[] a1, int[] a2) {
        return a1[1] - a2[1];
      }
    });

    Integer lastEnd = null;
    int result = 0;

    // any time pairs[i][0] > pairs[i - 1][1], extend chain
    for (int[] pair : pairs) {
      if (lastEnd == null || pair[0] > lastEnd) {
        lastEnd = pair[1];
        result++;
      }
    }

    return result;
  }

}