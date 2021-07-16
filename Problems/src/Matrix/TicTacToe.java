package Matrix;

class TicTacToe {
  int[] rows;
  int[] cols;
  int diagTopLeftToBottomRight = 0;
  int diagBottomLeftToTopRight = 0;
  int size;

  public TicTacToe(int n) {
    rows = new int[n];
    cols = new int[n];
    this.size = n;
  }

  /**
   * Player {player} makes a move at ({row}, {col}).
   *
   * @param row    The row of the board.
   * @param col    The column of the board.
   * @param player The player, can be either 1 or 2.
   * @return The current winning condition, can be either:
   * 0: No one wins.
   * 1: Player 1 wins.
   * 2: Player 2 wins.
   */
  public int move(int row, int col, int player) {
    int mult = player == 1 ? 1 : -1;

    rows[row] += mult;
    cols[col] += mult;
    if (row == col) {
      diagTopLeftToBottomRight += mult;
    }
    if (row == size - col - 1 || col == size - row - 1) {
      diagBottomLeftToTopRight += mult;
    }

    int vic = mult * size;
    if (rows[row] == vic || cols[col] == vic || diagTopLeftToBottomRight == vic || diagBottomLeftToTopRight == vic) {
      return player;
    }

    return 0;
  }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */