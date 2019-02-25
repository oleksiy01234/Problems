package DP;

import Util.Util;

class HighestProductOf3 {

  public static void main(String[] args) {
    int[] a = new int[] { 1, -2, 0, 1, 4, 2, 4, -5, 4 };
    System.out.println(solve(a));
  }

  private static int solve(int[] a) {
    if (a.length < 3) {
      throw new IllegalArgumentException("Array must have at least 3 items.");
    }

    int max = Util.max(a[0], a[1], a[2]);
    int min = Util.min(a[0], a[1], a[2]);

    int max2 = Util.max(a[0] * a[1], a[1] * a[2], a[0] * a[2]);
    int min2 = Util.min(a[0] * a[1], a[1] * a[2], a[0] * a[2]);

    int max3 = a[0] * a[1] * a[3];

    for (int i = 3; i < a.length; i++) {
      max3 = Util.max(max3, a[i] * max2, a[i] * min2);

      max2 = Util.max(max2, a[i] * max, a[i] * min);
      min2 = Util.min(min2, a[i] * max, a[i] * min);

      max = Math.max(max, a[i]);
      min = Math.min(min, a[i]);
    }

    return max3;
  }

}