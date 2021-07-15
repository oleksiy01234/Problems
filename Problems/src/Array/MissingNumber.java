package Array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {

  // another soln is to sort the array

  public int missingNumber(int[] a) {
    int expectedSum = 0;
    for (int i = 0; i <= a.length; i++) {
      expectedSum += i;
    }

    int actualSum = 0;
    for (int n : a) {
      actualSum += n;
    }

    return expectedSum - actualSum;
  }

  public int missingNumber2(int[] a) {
    Set<Integer> set = new HashSet<>();

    for (int n : a) {
      set.add(n);
    }

    for (int i = 0; i < a.length; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }

    return a.length;
  }
}
