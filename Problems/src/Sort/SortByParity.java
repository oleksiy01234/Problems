package Sort;

import java.util.Arrays;

import Util.Util;

/**
* Leetcode #905. Sort Array By Parity
* https://leetcode.com/problems/sort-array-by-parity/
*/
public class SortByParity {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      int[] a = Util.constructArray(5, 0, 10);
      System.out.println(Arrays.toString(a));
      sortArrayByParity(a);
      System.out.println(Arrays.toString(a) + "\n");
    }
  }

  static int[] sortArrayByParity(int[] a) {
    int hi = a.length - 1;
    int lo = 0;

    while (lo < hi) {
      boolean oddLo = a[lo] % 2 == 1;
      boolean evenHi = a[hi] % 2 == 0;

      if (oddLo && evenHi) {
        Util.swap(a, lo, hi);
        hi--;
        lo++;
      }

      if (!oddLo) {
        lo++;
      }

      if (!evenHi) {
        hi--;
      }
    }

    return a;
  }
}