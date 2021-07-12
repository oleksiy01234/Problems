import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * <p>
 * Example 1: 11110
 * 11010
 * 11000
 * 00000
 * Output: 1
 * <p>
 * <p>
 * Example 2: 11000
 * 11000
 * 00100
 * 00011
 * Output: 3
 */
public class NumberOfIslands {
  // to keep the grid the same, we can either:
  // 1) flag the cell as '2', so we know not to process it, and clean it up later (set it back to 1)
  // 2) keep a hashset of visited cells, with strings of "row,col" format.
  public static void main(String[] args) {
    System.out.println(new NumberOfIslands().numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
  }

  // bfs solution
  public int numIslands(char[][] grid) {
    int count = 0;

    for (int i = 0; i < grid.length; ++i) {
      for (int k = 0; k < grid[i].length; ++k) {
        if (grid[i][k] == '1') {
          count++;
          bfs(grid, i, k);
        }
      }
    }

    return count;
  }

  void bfs(char[][] grid, int i, int k) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{i, k});

    while (!q.isEmpty()) {
      int[] id = q.poll();
      int row = id[0];
      int col = id[1];

      if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] != '1') {
        continue;
      }

      grid[row][col] = '2';

      q.add(new int[]{row + 1, col});
      q.add(new int[]{row - 1, col});
      q.add(new int[]{row, col - 1});
      q.add(new int[]{row, col + 1});
    }
  }

  // dfs solution
  public int numIslands2(char[][] grid) {
    int count = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (grid[i][k] == '1') {
          dfs(grid, i, k);
          count++;
        }
      }
    }

    return count;
  }

  void dfs(char[][] grid, int row, int col) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] == '0') {
      return;
    }

    grid[row][col] = '2';

    dfs(grid, row + 1, col);
    dfs(grid, row - 1, col);
    dfs(grid, row, col - 1);
    dfs(grid, row, col + 1);
  }
}