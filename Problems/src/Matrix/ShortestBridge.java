package Matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934. Shortest Bridge
 * https://leetcode.com/problems/shortest-bridge/
 * <p>
 * In a given 2D binary array grid, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * <p>
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * <p>
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * <p>
 * Example 3:
 * <p>
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 2 <= grid.length == grid[0].length <= 100
 * grid[i][j] == 0 or grid[i][j] == 1
 */
public class ShortestBridge {
  private final int[][] dirs = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

  public int shortestBridge(int[][] a) {
    boolean[][] visited = new boolean[a.length][a[0].length];
    boolean found = false;
    Queue<int[]> q = new LinkedList<>();  // store visited cells with x and y in an array

    // 1. dfs to find the first island, mark its cells in visited
    for (int i = 0; i < a.length && !found; i++) {
      for (int j = 0; j < a[i].length && !found; j++) {
        if (a[i][j] == 1) {
          dfs(visited, a, i, j, q);
          found = true;
        }
      }
    }

    // 2. bfs from all visited cells, to expand the first island and reach second island
    int count = 0;
    while (!q.isEmpty()) {

      for (int size = q.size(); size > 0; size--) {
        int[] cell = q.poll();
        for (int[] d : dirs) { // try to expand in every direction from every visited cell
          int x = cell[0] + d[0];
          int y = cell[1] + d[1];
          if (x < 0 || y < 0 || x >= a.length || y >= a[x].length || visited[x][y]) {
            continue; // out of bounds, or in the first island, or already seen/expanded into
          }

          if (a[x][y] == 1) { // found another island! this is shortest, since we expanded from all visited cells
            return count;
          }
          q.add(new int[]{x, y});
          visited[x][y] = true;
        }
      }

      count++;
    }
    return -1;
  }

  // adds visited to the q, so we can later BFS from all cells
  private void dfs(boolean[][] visited, int[][] grid, int x, int y, Queue<int[]> q) {
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || visited[x][y] || grid[x][y] == 0) {
      return;
    }

    visited[x][y] = true;
    q.add(new int[]{x, y});
    for (int[] d : dirs) {
      dfs(visited, grid, x + d[0], y + d[1], q);
    }
  }
}
