package Array;

/**
 * GameOfLife
 */
public class GameOfLife {

  private enum STATE {
    DEAD, ALIVE, RESURRECTING, DYING
  }

  public void gameOfLife(int[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        calculateNextState(m, i, k);
      }
    }

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == STATE.RESURRECTING.ordinal()) {
          m[i][k] = STATE.ALIVE.ordinal();
        } else if (m[i][k] == STATE.DYING.ordinal()) {
          m[i][k] = STATE.DEAD.ordinal();
        }
      }
    }
  }

  private void calculateNextState(int[][] m, int row, int col) {
    int count = 0;

    for (int i = row - 1; i <= row + 1; i++) {
      for (int k = col - 1; k <= col + 1; k++) {
        if (row != i || col != k) {
          if (isCellAlive(m, i, k)) {
            count++;
          }
        }
      }
    }

    if (count == 3 && m[row][col] == STATE.DEAD.ordinal()) {
      m[row][col] = STATE.RESURRECTING.ordinal();
    } else if ((count < 2 || count > 3) && m[row][col] == STATE.ALIVE.ordinal()) {
      m[row][col] = STATE.DYING.ordinal();
    }
  }

  private boolean isCellAlive(int[][] m, int row, int col) {
    if (row < 0 || row >= m.length || col < 0 || col >= m[row].length) {
      return false;
    }

    return m[row][col] == STATE.ALIVE.ordinal() || m[row][col] == STATE.DYING.ordinal();
  }
}