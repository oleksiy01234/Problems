package Recursion;

/**
 * Maze
 */
public class Maze {

  public static void main(String[] args) {
    int[][] maze = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
        { 0, 0, 0, 0, 0 } };

    System.out.println(hasPath(maze, new int[] { 0, 4 }, new int[] { 3, 2 }));
  }

  static boolean hasPath(int[][] maze, int[] start, int[] dest) {
    return hasPath(maze, start, null, dest, new boolean[maze.length][maze[0].length]);
  }

  static boolean hasPath(int[][] maze, int[] pos, int[] prev, int[] dest, boolean[][] visited) {
    int row = pos[0];
    int col = pos[1];

    if (isWall(maze, row, col) || visited[row][col]) {
      return false;
    }

    if (row == dest[0] && col == dest[1]) {
      if (canStop(maze, row, col)) {
        return true;
      }
    }

    visited[row][col] = true;

    if (hasPath(maze, new int[] { row + 1, col }, pos, dest, visited)
        || hasPath(maze, new int[] { row, col + 1 }, pos, dest, visited)
        || hasPath(maze, new int[] { row - 1, col }, pos, dest, visited)
        || hasPath(maze, new int[] { row, col - 1 }, pos, dest, visited)) {
      return true;
    }

    return false;
  }

  static boolean canStop(int[][] maze, int row, int col) {
    if (isWall(maze, row + 1, col) != isWall(maze, row - 1, col)) {
      return true;
    }

    if (isWall(maze, row, col + 1) != isWall(maze, row, col - 1)) {
      return true;
    }

    return false;
  }

  static boolean isWall(int[][] maze, int row, int col) {
    return row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1;
  }
}