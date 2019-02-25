package DP;

/**
 * PaintHouse
 */
public class PaintHouse {

  /**
   * Return the total number of ways to paint n posts with k colors. 
   * No more than 2 adjacent posts have the same color.
   */
  public int numWays(int n, int k) {
    // no posts - no ways to paint them
    if (n == 0) {
      return 0;
    }

    // one post - k ways to paint it
    if (n == 1) {
      return k;
    }

    // two posts = can't  free to paint however you want.
    // first post, k options. second post, k options
    if (n == 2) {
      return k * k;
    }

    int dp[] = new int[n + 1];
    dp[0] = 0;
    dp[1] = k;
    dp[2] = k * k;
    for (int i = 3; i <= n; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
    }

    return dp[n];
  }

}