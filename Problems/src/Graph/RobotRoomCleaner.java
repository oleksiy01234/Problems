package Graph;

import java.util.HashSet;
import java.util.Set;

class RobotRoomCleaner {

  interface Robot {
    public boolean move();

    public void turnLeft();

    public void turnRight();

    public void clean();
  }

  enum Direction {
    UP, RIGHT, DOWN, LEFT;
  }

  static void cleanRoom(Robot robot) {
    Set<String> visited = new HashSet<>();
    dfs(robot, visited, 0, 0, Direction.UP);
  }

  private static void dfs(Robot robot, Set<String> visited, int row, int col, Direction curDirection) {
    String path = row + "," + col;
    if (visited.contains(path)) {
      return;
    }

    visited.add(path);
    robot.clean();

    for (int i = 0; i < 4; i++) {
      if (robot.move()) {
        // go all the way till cannot move, then back one step
        if (curDirection == Direction.UP) {
          dfs(robot, visited, row + 1, col, curDirection);
        } else if (curDirection == Direction.RIGHT) {
          dfs(robot, visited, row, col + 1, curDirection);
        } else if (curDirection == Direction.DOWN) {
          dfs(robot, visited, row - 1, col, curDirection);
        } else {
          dfs(robot, visited, row, col - 1, curDirection);
        }

        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
      }

      robot.turnRight();
      curDirection = turnRight(curDirection);
    }
  }

  private static Direction turnRight(Direction curDirection) {
    if (curDirection == Direction.UP) {
      return Direction.RIGHT;
    } else if (curDirection == Direction.RIGHT) {
      return Direction.DOWN;
    } else if (curDirection == Direction.DOWN) {
      return Direction.LEFT;
    } else {
      return Direction.UP;
    }
  }
}