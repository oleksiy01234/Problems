package Recursion;

public class Sudoku {
  static final int BOARD_SIZE = 9;
  static final int BOX_SIZE = 3;
  static final char EMPTY = '.';

  static boolean hasValidSolution(char[][] g, int row, int col) {
    if (row == BOARD_SIZE) {
      row = 0;
      col++;

      if (col == BOARD_SIZE) {
        return true;
      }
    }

    if (g[row][col] != EMPTY) { // skip pre-filled values
      return hasValidSolution(g, row + 1, col);
    }

    for (int i = 1; i <= 9; i++) {
      char attempt = Character.forDigit(i, 10);
      if (!isValid(g, row, col, attempt)) {
        continue; // not valid: try another number
      }

      g[row][col] = attempt;

      if (hasValidSolution(g, row + 1, col)) {
        return true;
      }
    }

    // backtrack
    g[row][col] = EMPTY;

    // no numbers valid for this cell
    return false;
  }

  static boolean isValid(char[][] g, int row, int col, char attempt) {
    // check row
    for (int i = 0; i < BOARD_SIZE; i++) {
      if (g[row][i] == attempt) {
        return false;
      }
    }

    // check column
    for (int i = 0; i < BOARD_SIZE; i++) {
      if (g[i][col] == attempt) {
        return false;
      }
    }

    // check box
    int boxRowStart = row - (row % BOX_SIZE);
    int boxColStart = col - (col % BOX_SIZE);
    int boxRowEnd = boxRowStart + BOX_SIZE;
    int boxColEnd = boxColStart + BOX_SIZE;

    for (int i = boxRowStart; i < boxRowEnd; i++) {
      for (int k = boxColStart; k < boxColEnd; k++) {
        if (g[i][k] == attempt) {
          return false;
        }
      }
    }

    return true;
  }
}