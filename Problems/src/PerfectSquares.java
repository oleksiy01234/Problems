class PerfectSquares {

  public static void main(String[] args) {
    numSquares(30);
  }

  static int numSquares(int n) {
    int[] dp = new int[n + 1];

    for (int i = 0; i <= n; i++) {
      dp[i] = i;
      for (int j = 1; square(j) <= i; j++) {
        dp[i] = Math.min(dp[i - square(j)] + 1, dp[i]);
      }
    }

    return dp[n];
  }

  static int square(int n) {
    return n * n;
  }
}