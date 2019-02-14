class AndroidUnlockPatterns {

  public static void main(String[] args) {
    System.out.println(numberOfPatterns(3, 3));
  }

  static int numberOfPatterns(int min, int max) {
    int[] result = new int[1];
    androidUnlockHelper(0, min, max, 0, new boolean[10], result);
    return result[0];
  }

  private static void androidUnlockHelper(int n, int min, int max, int lastDigit, boolean[] used, int[] count) {
    if (n > max) {
      return;
    }

    if (n >= min) {
      count[0]++;
    }

    for (int i = 1; i <= 9; i++) {
      if (validDigit(used, lastDigit, i)) {
        used[i] = true;
        androidUnlockHelper(n + 1, min, max, i, used, count);
        used[i] = false;
      }
    }
  }

  static boolean validDigit(boolean[] used, int last, int i) {
    if (used[i]) {
      return false;
    }

    if (i == 1) {
      if ((last == 3 && !used[2]) || (last == 9 && !used[5]) || (last == 7 && !used[4])) {
        return false;
      }
    } else if (i == 2) {
      if (last == 8 && !used[5]) {
        return false;
      }
    } else if (i == 3) {
      if ((last == 1 && !used[2]) || (last == 7 && !used[5]) || (last == 9 && !used[6])) {
        return false;
      }
    } else if (i == 4) {
      if (last == 6 && !used[5]) {
        return false;
      }
    } else if (i == 6) {
      if (last == 4 && !used[5]) {
        return false;
      }
    } else if (i == 7) {
      if ((last == 1 && !used[4]) || (last == 3 && !used[5]) || (last == 9 && !used[8])) {
        return false;
      }
    } else if (i == 8) {
      if (last == 2 && !used[5]) {
        return false;
      }
    } else if (i == 9) {
      if ((last == 3 && !used[6]) || (last == 1 && !used[5]) || (last == 7 && !used[8])) {
        return false;
      }
    }

    return true;
  }
}