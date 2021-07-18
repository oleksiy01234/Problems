package Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
  public List<Integer> spiralOrder(int[][] m) {
    List<Integer> res = new ArrayList<>();

    // inclusive edges
    int topEdge = 0;
    int leftEdge = 0;
    int bottomEdge = m.length - 1;
    int rightEdge = m[0].length - 1;

    while (res.size() < m.length * m[0].length) {
      if (topEdge <= bottomEdge) {
        for (int i = leftEdge; i <= rightEdge; i++) {
          res.add(m[topEdge][i]);
        }
        topEdge++;
      }

      if (leftEdge <= rightEdge) {
        for (int i = topEdge; i <= bottomEdge; i++) {
          res.add(m[i][rightEdge]);
        }
        rightEdge--;
      }

      if (topEdge <= bottomEdge) {
        for (int i = rightEdge; i >= leftEdge; i--) {
          res.add(m[bottomEdge][i]);
        }
        bottomEdge--;
      }

      if (leftEdge <= rightEdge) {
        for (int i = bottomEdge; i >= topEdge; i--) {
          res.add(m[i][leftEdge]);
        }
        leftEdge++;
      }
    }

    return res;
  }
}
