package DP;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 */
public class NumberOfUniqueBSTs {

  public int numTreesIter(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }
    return dp[n];
  }

  /**
   * Recursive memoized Solution
   */
  static int numTrees(int n) {
    return numTrees(n, new HashMap<>());
  }

  static int numTrees(int n, Map<Integer, Integer> map) {
    if (n <= 1) {
      return 1;
    }

    if (map.containsKey(n)) {
      return map.get(n);
    }

    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += numTrees(i - 1, map) * numTrees(n - i, map);
    }

    map.put(n, sum);
    return sum;
  }
}