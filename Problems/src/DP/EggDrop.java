package DP;

public class EggDrop {
  public static void main(String args[]) {
    System.out.println(solve(3, 6));
  }

  /**
   * Fast method. m[totalEggs][count] is max amt of floors
   * we can check with k eggs
   */
  static int solve(int totalEggs, int totalFloors) {
    int[][] m = new int[totalEggs + 1][totalFloors + 1];
    int count = 0;

    while (m[totalEggs][count] < totalFloors) {
      count++;
      for (int k = 1; k <= totalEggs; k++) {
        m[k][count] = m[k - 1][count - 1] + m[k][count - 1] + 1;
      }
    }

    return count;
  }

  /**
   * Slower method.
   */
  public int superEggDrop(int eggs, int floors) {

    int m[][] = new int[eggs + 1][floors + 1];
    for (int i = 0; i <= floors; i++) {
      m[1][i] = i;
    }

    for (int i = 2; i <= eggs; i++) {
      for (int j = 1; j <= floors; j++) {
        m[i][j] = Integer.MAX_VALUE;

        // try to drop it on every floor, to find the best worst case.
        for (int k = 1; k <= j; k++) {
          m[i][j] = Math.min(m[i][j], 1 + Math.max(m[i - 1][k - 1], m[i][j - k]));
        }
      }
    }

    return m[eggs][floors];

  }

  static int solveRec(int eggs, int floors) {
    if (eggs == 1) {
      return floors;
    }
    if (floors == 0) {
      return 0;
    }

    int min = 1000;
    for (int i = 1; i <= floors; i++) {
      int val = 1 + Math.max(solveRec(eggs - 1, i - 1), solveRec(eggs, floors - i));
      if (val < min) {
        min = val;
      }
    }
    return min;
  }

}