package DP;

class MaximalSubmatrix {

  public static void main(String[] args) {
    char[][] m = new char[][] { { '1', '0', '1', '1', '1', '0', '0', '0', '1', '0' },
        { '0', '1', '0', '0', '0', '0', '0', '1', '1', '0' }, { '0', '1', '0', '1', '0', '0', '0', '0', '1', '1' },
        { '1', '1', '1', '0', '0', '0', '0', '0', '1', '0' }, { '0', '1', '1', '1', '0', '0', '1', '0', '1', '0' },
        { '1', '1', '0', '1', '1', '0', '1', '1', '1', '0' } };

    maximalRectangle(m);
  }

  /**
   * 2D version
   */
  static int maximalSquare2D(char[][] m) {
    if (m.length == 0 || m[0].length == 0) {
      return 0;
    }

    int[][] dp = new int[m.length + 1][m[0].length + 1];
    int max = 0;

    for (int i = 1; i < dp.length; i++) {
      for (int k = 1; k < dp[i].length; k++) {

        if (m[i - 1][k - 1] == '1') {
          dp[i][k] = Math.min(dp[i - 1][k], Math.min(dp[i - 1][k - 1], dp[i][k - 1])) + 1;
          max = Math.max(max, dp[i][k]);
        }

      }
    }

    return max * max;
  }

  /**
   * 1D optimization
   */
  static int maximalSquare(char[][] m) {
    if (m.length == 0 || m[0].length == 0) {
      return 0;
    }

    int[] dp = new int[m[0].length + 1];
    int max = 0;

    for (int i = 0; i < m.length; i++) {
      int prev = 0;
      for (int k = 0; k < m[i].length; k++) {
        int temp = dp[k + 1];

        if (m[i][k] == '1') {
          dp[k + 1] = Math.min(prev, Math.min(dp[k + 1], dp[k])) + 1;
          max = Math.max(max, dp[k + 1]);
        } else {
          dp[k + 1] = 0;
        }

        prev = temp;
      }
    }

    return max * max;
  }

  static class Rect {
    int height;
    int length;

    void clear() {
      height = length = 0;
    }

    int getArea() {
      return height * length;
    }

    @Override
    public String toString() {
      return "h:" + height + " l:" + length;
    }
  }

  static int maximalRectangle(char[][] m) {
    if (m.length == 0 || m[0].length == 0) {
      return 0;
    }

    Rect[] dp = new Rect[m[0].length + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = new Rect();
    }
    int max = 0;

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == '1') {
          int thisHeight = dp[k + 1].height;
          int thisLength = dp[k + 1].length;
          int leftLength = dp[k].length;
          int leftHeight = dp[k].height;

          int extendHeightArea = (thisHeight + 1) * Math.min(thisLength, leftLength + 1);
          int extendLengthArea = (leftLength + 1) * Math.min(leftHeight, thisHeight + 1);

          if (extendHeightArea == 0 && extendLengthArea == 0) {
            dp[k + 1].length = dp[k + 1].height = 1;
          } else if (extendHeightArea > extendLengthArea) {
            dp[k + 1].height = thisHeight + 1;
            dp[k + 1].length = Math.min(thisLength, leftLength + 1);
          } else {
            dp[k + 1].length = leftLength + 1;
            dp[k + 1].height = Math.min(leftHeight, thisHeight + 1);
          }

          max = Math.max(max, dp[k + 1].getArea());
        } else {
          dp[k + 1].clear();
        }
      }
    }

    return max;
  }
}