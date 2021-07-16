package Recursion;

/**
 * 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 * <p>
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * <p>
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 */
public class ClimbStairs {
  // iterative solution O(1) space
  public int climbStairs(int n) {
    int twoBehind = 1;
    int oneBehind = 1;

    int count = 1;

    for (int i = 2; i <= n; i++) {
      count = twoBehind + oneBehind;
      twoBehind = oneBehind;
      oneBehind = count;
    }

    return count;
  }

  // 2. recursive solution O(n) space
  public int climbStairs2(int n) {
    int[] memo = new int[n + 1];
    memo[0] = memo[1] = 1;

    return climbStairs(n, memo);
  }

  private int climbStairs(int n, int[] memo) {
    if (memo[n] != 0) {
      return memo[n];
    }

    int result = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
    memo[n] = result;
    return result;
  }
}
