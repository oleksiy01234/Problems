package Array;

import Util.Util;

/**
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Given int[], find the first missing positive int in linear time and constant
 * space. I.e. find the lowest positive int that does not exist in the array.
 * Input can contain duplicates and negative numbers as well. E.g. [3, 4, -1, 1]
 * -> 2. [1,2,0] -> 3.
 * <p>
 * n^2: for all ints starting with 1, check if the array contains it
 * n + set: check if the set contains it
 * n log n: sort
 */
public class FirstMissingPositive {
  public static void main(String[] args) {
    System.out.println(findFirstMissingPositiveInt(new int[]{}));
  }

  // solution utilizing the array itself to flag existing 1..n elements
  public int firstMissingPositive(int[] a) {
    // 1. mark numbers <= 0 and > a.length with a special marker number (n+1)
    // (we can ignore those because if all numbers are > n then we'll simply return 1)
    for (int i = 0; i < a.length; i++) {
      if (a[i] <= 0 || a[i] > a.length) {
        a[i] = a.length + 1;
      }
    }
    // note: all number in the array are now positive, and on the range 1..n+1

    // 2. mark each cell appearing in the array, by converting the index for that number to negative
    for (int i = 0; i < a.length; i++) {
      int num = Math.abs(a[i]);
      if (num > a.length) {
        continue; // ignore numbers > a.length, like we instructed in step 1
      }

      num--; // -1 for zero index based array (so the number 1 will be at pos 0)
      a[num] = -Math.abs(a[num]); // prevent double negation in case of duplicates
    }

    // 3. find the first cell which isn't negative (doesn't appear in the array)
    for (int i = 0; i < a.length; i++) {
      if (a[i] >= 0) {
        return i + 1;
      }
    }

    // 4. no positive numbers were found, which means the array contains all numbers 1..n
    return a.length + 1;
  }


  // swap solution
  static int findFirstMissingPositiveInt(int[] a) {
    if (a.length == 0) {
      return 1;
    }

    int i = 0;
    while (i < a.length) {
      if (a[i] >= 0 && a[i] < a.length && a[a[i]] != a[i]) { // swap if within bounds and different
        Util.swap(a, i, a[i]);
      } else {
        i++;
      }
    }

    int k = 1;
    while (k < a.length && a[k] == k) {
      k++;
    }

    if (k < a.length) {
      return k;
    }

    return a[0] == k ? k + 1 : k;
  }
}
