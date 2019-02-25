package Array;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {

  public int snakesAndLadders(int[][] m) {
    int n = m.length;
    int[] a = convertTo1D(m);

    boolean[] visited = new boolean[n * n];
    Queue<Integer> q = new LinkedList<>();
    int start = a[0] > -1 ? a[0] - 1 : 0;
    q.offer(start);
    visited[start] = true;
    int step = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        int cur = q.poll();
        if (cur == n * n - 1) {
          return step;
        }
        for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
          int dest = a[next] > -1 ? a[next] - 1 : next;
          if (!visited[dest]) {
            visited[dest] = true;
            q.offer(dest);
          }
        }
      }
      step++;
    }
    return -1;
  }

  int[] convertTo1D(int[][] m) {
    int[] a = new int[m.length * m.length];

    for (int i = 1; i <= a.length; i++) {
      int nextRow = m.length - 1 - (i / m.length);
      int nextCol = (i + (m.length - nextRow)) % m.length;

      a[i - 1] = m[nextRow][nextCol];
    }

    return a;
  }
}