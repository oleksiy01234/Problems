package Sort;

import java.util.Arrays;

import Util.Util;

/**
 * QuickSort
 */
public class QuickSort {

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
      int[] a = Util.constructArray(5, 0, 100);
      System.out.println(Arrays.toString(a));
      quickSort(a, 0, a.length - 1);
      System.out.println(Arrays.toString(a) + "\n");
    }
  }

  // hi inclusive
  public static void quickSort(int[] a, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int pivotIndex = partition(a, lo, hi);
    quickSort(a, lo, pivotIndex - 1);
    quickSort(a, pivotIndex, hi);
  }

  public static int partition(int[] a, int lo, int hi) {
    int pivot = a[lo + (hi - lo) / 2];

    while (lo <= hi) {
      while (a[lo] < pivot) {
        lo++;
      } // Find element on left that should be on right
      while (a[hi] > pivot) {
        hi--;
      } // Find element on right that should be on left

      if (lo <= hi) {
        Util.swap(a, lo, hi);
        lo++;
        hi--;
      }
    }

    return lo;
  }
}