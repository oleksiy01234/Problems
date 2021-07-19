package Recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * 694. Number of Distinct Islands
 * https://leetcode.com/problems/number-of-distinct-islands/
 * <p>
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one
 * island can be translated (and not rotated or reflected) to equal the other.
 * <p>
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * <p>
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 * <p>
 * Notice that:
 * 11
 * 1
 * and
 * 1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 */
public class NumberOfDistinctIslands {

  // path signature. O(rc)
  // count the offsets. offsets are going to be the same regardless where the island starts
  public int numDistinctIslands(int[][] grid) {
    Set<String> islands = new HashSet<>();

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (grid[i][k] == 1) {
          StringBuilder sb = new StringBuilder();
          dfs(sb, grid, i, k, 0, 0);
          islands.add(sb.toString());
        }
      }
    }

    return islands.size();
  }

  void dfs(StringBuilder sb, int[][] grid, int i, int k, int iOffset, int kOffset) {
    if (i < 0 || k < 0 || i >= grid.length || k >= grid[i].length || grid[i][k] != 1) {
      return;
    }

    grid[i][k] = 0;
    sb.append(iOffset).append(",").append(kOffset).append(",");

    dfs(sb, grid, i, k + 1, iOffset, kOffset + 1);
    dfs(sb, grid, i + 1, k, iOffset + 1, kOffset);
    dfs(sb, grid, i, k - 1, iOffset, kOffset - 1);
    dfs(sb, grid, i - 1, k, iOffset - 1, kOffset);
  }
}