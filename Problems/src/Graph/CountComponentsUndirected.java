package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * CountComponentsUndirected
 */
public class CountComponentsUndirected {

  public static void main(String[] args) {
    System.out.println(countComponentsDFS2(5, new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }));
  }

  public int countComponentsUnionFind(int n, int[][] edges) {
    if (n <= 1) {
      return n;
    }
    int[] roots = new int[n];
    for (int i = 0; i < n; i++) {
      roots[i] = i;
    }
    for (int[] edge : edges) {
      int x = find(roots, edge[0]);
      int y = find(roots, edge[1]);
      if (x != y) {
        roots[x] = y;
        n--;
      }
    }

    return n;
  }

  public int find(int[] roots, int id) {
    int x = id;
    while (roots[id] != id) {
      id = roots[id];
    }
    while (roots[x] != id) {
      int fa = roots[x];
      roots[x] = id;
      x = fa;
    }

    return id;
  }

  public int countComponentsDFS(int n, int[][] edges) {
    if (n <= 1) {
      return n;
    }

    List<List<Integer>> adjList = new ArrayList<List<Integer>>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<Integer>());
    }

    for (int[] edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
      adjList.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        count++;
        dfs(visited, i, adjList);
      }
    }

    return count;
  }

  public void dfs(boolean[] visited, int index, List<List<Integer>> adjList) {
    visited[index] = true;
    for (int i : adjList.get(index)) {
      if (!visited[i]) {
        dfs(visited, i, adjList);
      }
    }
  }

  // my implementation
  static int countComponentsDFS2(int n, int[][] edges) {
    if (n <= 1) {
      return n;
    }

    boolean[] visited = new boolean[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        count++;
        dfs2(visited, i, edges);
      }
    }

    return count;
  }

  static void dfs2(boolean[] visited, int index, int[][] edges) {
    visited[index] = true;
    for (int[] edge : edges) {
      if (index == edge[1] && !visited[edge[0]]) {
        dfs2(visited, edge[0], edges);
      }

      if (index == edge[0] && !visited[edge[1]]) {
        dfs2(visited, edge[1], edges);
      }
    }
  }

  public int countComponentsBFS(int n, int[][] edges) {
    if (n <= 1) {
      return n;
    }
    List<List<Integer>> adjList = new ArrayList<List<Integer>>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<Integer>());
    }
    for (int[] edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
      adjList.get(edge[1]).add(edge[0]);
    }
    boolean[] visited = new boolean[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        count++;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i);
        while (!queue.isEmpty()) {
          int index = queue.poll();
          visited[index] = true;
          for (int next : adjList.get(index)) {
            if (!visited[next]) {
              queue.offer(next);
            }
          }
        }
      }
    }

    return count;
  }
}