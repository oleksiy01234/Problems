import java.util.Arrays;

class CoinChange {
  public static void main(String[] args) {
    int[] coins = new int[] { 1, 2, 3 };
    System.out.println(coinChange(coins, 5));
    System.out.println(coinChange2(coins, 5));
  }

  /**
   * How many ways can we make this amount with these coins?
   */
  private static int coinChange(int[] coins, int amount) {
    int[][] m = new int[coins.length + 1][amount + 1];

    for (int i = 1; i < m.length; i++) {
      m[i][0] = 1;
      for (int k = 1; k <= amount; k++) {
        int excl = m[i - 1][k];
        if (k >= coins[i - 1]) {
          int incl = excl + m[i][k - coins[i - 1]];
          m[i][k] = incl;
        } else {
          m[i][k] = excl;
        }
      }
    }

    return m[m.length - 1][amount];
  }

  /**
   * What is the minimum number of coins required to make this amount?
   */
  private static int coinChange2(int[] coins, int amount) {
    int max = amount + 1;
    int[] m = new int[amount + 1];
    Arrays.fill(m, max);
    m[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (coin <= i) {
          m[i] = Math.min(m[i], 1 + m[i - coin]);
        }
      }
    }

    return m[amount] > amount ? -1 : m[amount];
  }

}