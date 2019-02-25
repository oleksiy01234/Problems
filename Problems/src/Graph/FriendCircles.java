package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FriendCircles
 */
public class FriendCircles {

  /**
   * DFS (n^2) Solution.
   * Reduce to the problem of finding the number of connected components in an undirected graph
   */
  static int findCircleNum(int[][] m) {
    boolean[] visited = new boolean[m.length];
    int count = 0;

    for (int i = 0; i < m.length; i++) {
      if (!visited[i]) {
        dfs(m, visited, i);
        count++;
      }
    }

    return count;
  }

  static void dfs(int[][] m, boolean[] visited, int i) {
    for (int j = 0; j < m.length; j++) {
      if (m[i][j] == 1 && !visited[j]) {
        visited[j] = true;
        dfs(m, visited, j);
      }
    }
  }

  /**
   * Alternative DFS: altering the matrix 
   */
  public int findCircleNum2(int[][] m) {
    int count = 0;

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 1) {
          dfs2(m, i);
          count++;
        }
      }
    }

    return count;
  }

  public void dfs2(int[][] m, int row) {
    for (int j = 0; j < m.length; j++) {
      if (m[row][j] == 1) {
        m[row][j] = 0;
        dfs2(m, j);
      }
    }
  }

  /**
   * BFS O(n^2) Solution. 
   */
  public int findCircleNum3(int[][] m) {
    boolean[] visited = new boolean[m.length];
    int count = 0;

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < m.length; i++) {
      if (visited[i]) {
        continue;
      }

      queue.add(i);
      while (!queue.isEmpty()) {
        int s = queue.poll();
        visited[s] = true;

        for (int j = 0; j < m.length; j++) {
          if (m[s][j] == 1 && !visited[j]) {
            queue.add(j);
          }
        }
      }
      count++;
    }
    return count;
  }

  /**
   * Method 4: Union-Find Algorithm
   */
  static int findCircleNum4(int[][] m) {
    int[] parent = new int[m.length];
    Arrays.fill(parent, -1);
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m.length; j++) {
        if (m[i][j] == 1 && i != j) {
          union(parent, i, j);
        }
      }
    }

    int count = 0;
    for (int i = 0; i < parent.length; i++) {
      if (parent[i] == -1) {
        count++;
      }
    }
    return count;
  }

  static void union(int parent[], int x, int y) {
    int xset = find(parent, x);
    int yset = find(parent, y);
    if (xset != yset) {
      parent[xset] = yset;
    }
  }

  static int find(int parent[], int i) {
    if (parent[i] == -1) {
      return i;
    }
    
    return find(parent, parent[i]);
  }
}