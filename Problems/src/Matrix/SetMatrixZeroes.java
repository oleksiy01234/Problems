package Matrix;

import java.util.Arrays;

/**
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0, in place.
 */
public class SetMatrixZeroes {
  // Could save zeroCols and zeroRows in sets, then set the matrix
  // O(1) alternative: use top row and left col to flag that we should set entire row/col to 0
  public void setZeroes(int[][] m) {
    boolean firstRowZero = false;
    boolean firstColZero = false;

    // see if top row should be set to 0
    for (int n : m[0]) {
      if (n == 0) {
        firstRowZero = true;
        break;
      }
    }

    // see if left col should be set to 0
    for (int[] row : m) {
      if (row[0] == 0) {
        firstColZero = true;
        break;
      }
    }

    // flag what other rows and cols we need to nullify
    for (int i = 1; i < m.length; i++) {
      for (int k = 1; k < m[i].length; k++) {
        if (m[i][k] == 0) {
          m[i][0] = 0;
          m[0][k] = 0;
        }
      }
    }

    // actually nullify them
    for (int row = 1; row < m.length; row++) {
      if (m[row][0] == 0) {
        Arrays.fill(m[row], 0);
      }
    }

    for (int col = 1; col < m[0].length; col++) {
      if (m[0][col] == 0) {
        for (int[] row : m) {
          row[col] = 0;
        }
      }
    }

    // nullify left col if needed
    if (firstRowZero) {
      Arrays.fill(m[0], 0);
    }

    // nullify left col if needed
    if (firstColZero) {
      for (int[] n : m) {
        n[0] = 0;
      }
    }
  }
}
