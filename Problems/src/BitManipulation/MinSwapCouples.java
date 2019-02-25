package BitManipulation;

public class MinSwapCouples {

  public int minSwapsCouples(int[] row) {
    int result = 0;

    for (int i = 0; i < row.length; i += 2) {
      int p1 = row[i];
      int p2 = p1 ^ 1; // odd -> odd-1. even -> even+1

      if (row[i + 1] == p2) {
        continue; // no need to swap
      }

      result++;

      for (int j = i + 1; j < row.length; j++) {
        if (row[j] == p2) {
          row[j] = row[i + 1];
          row[i + 1] = p2;
          break;
        }
      }
    }

    return result;
  }
}