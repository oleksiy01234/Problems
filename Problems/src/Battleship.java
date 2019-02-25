import java.util.Arrays;

public class Battleship {
  private final static int BOARD_SIZE = 10;

  private enum Cell {
    Empty, Ship, DamagedShip
  }

  private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

  public void createBoard() {
    Arrays.fill(board[0], Cell.Empty);
  }

  private void printBoard() {
    for (Cell[] row : board) {
      for (Cell cell : row) {
        System.out.print(cell);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Battleship b = new Battleship();

    b.createBoard();
    b.printBoard();
  }

}