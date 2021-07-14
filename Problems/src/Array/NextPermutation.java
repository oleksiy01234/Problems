package Array;

import java.util.Arrays;

/**
 * 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * Example 4:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 */
class NextPermutation {

  public static void main(String[] args) {
    int[] a = new int[]{1, 5, 1};
    nextPermutation(a);
    System.out.println(Arrays.toString(a));
  }

  public static void nextPermutation(int[] a) {
    int swapIndex = -1;

    // 1. find 2 elements in order (left < right)
    for (int i = a.length - 2; i >= 0; i--) {
      if (a[i] < a[i + 1]) {
        swapIndex = i;
        break;
      }
    }

    // 2. if there are none, this is a descending sequence: we need to reverse it entirely.
    if (swapIndex == -1) {
      reverse(a, 0, a.length - 1);
      return;
    }

    // 3. find index of next greatest item after swap (for next perm)
    int indexOfNext = -1;
    for (int i = swapIndex + 1; i < a.length; i++) {
      if (a[i] <= a[swapIndex]) {
        break;
      }
      indexOfNext = i;
    }

    // 4. swap the items
    swap(a, swapIndex, indexOfNext);

    // 5. reverse the remaining elements on the right to be in order.
    reverse(a, swapIndex + 1, a.length - 1);
  }

  private static void reverse(int[] a, int start, int end) {
    while (start < end) {
      swap(a, start, end);
      start++;
      end--;
    }
  }

  private static void swap(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
}