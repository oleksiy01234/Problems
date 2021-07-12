/**
 * 1304. Find N Unique Integers Sum up to Zero
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 *
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 *
 * Input: n = 3
 * Output: [-1,0,1]
 *
 * Input: n = 1
 * Output: [0]
 */
public class NUniqueIntegersZeroSum {
  public int[] sumZero(int n) {
    int[] res = new int[n];

    for (int i = 0; i < n / 2; i++) {
      res[i] = -(n / 2) + i;
      res[n - 1 - i] = n / 2 - i;
    }

    return res;
  }
}
