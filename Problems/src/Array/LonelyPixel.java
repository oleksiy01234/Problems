package Array;

/**
 * 531. Lonely Pixel I
 * https://leetcode.com/problems/lonely-pixel-i/
 * <p>
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 * <p>
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 * <p>
 * Example 1:
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * <p>
 * Example 2:
 * Input: picture = [["B","B","B"],["B","B","B"],["B","B","B"]]
 * Output: 0
 */
public class LonelyPixel {
  // O(n + m) space, O(nm) time. O(1) space would use first row/col to store pixel count
  public int findLonelyPixel1(char[][] m) {
    int[] blacksInRows = new int[m.length];
    int[] blacksInCols = new int[m[0].length];

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 'B') {
          blacksInRows[i]++;
          blacksInCols[k]++;
        }
      }
    }

    int count = 0;

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 'B' && blacksInRows[i] == 1 && blacksInCols[k] == 1) {
          count++;
        }
      }
    }

    return count;
  }
}
