package Array;

import java.util.HashSet;
import java.util.Set;

/**
 * 532. K-diff Pairs in an Array
 * <p>
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 *
 * <p>
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,4,1,5], k = 2
 * <p>
 * Output: 2
 * <p>
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 1
 * <p>
 * Output: 4
 * <p>
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,3,1,5,4], k = 0
 * <p>
 * Output: 1
 * <p>
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * <p>
 * Example 4:
 * <p>
 * Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
 * <p>
 * Output: 2
 * <p>
 * <p>
 * Example 5:
 * <p>
 * Input: nums = [-1,-2,-3], k = 1
 * <p>
 * Output: 2
 */
public class KDiffPairs {
  public int findPairs(int[] a, int k) {
    Set<String> pairs = new HashSet<>();
    Set<Integer> seen = new HashSet<>();

    for (int i = a.length - 1; i >= 0; i--) {
      if (seen.contains(k + a[i])) {
        pairs.add(sortPair(a[i], k + a[i]));
      }

      if (seen.contains(a[i] - k)) {
        pairs.add(sortPair(a[i], a[i] - k));
      }

      seen.add(a[i]);
    }

    return pairs.size();
  }

  private String sortPair(int i1, int i2) {
    return i1 <= i2 ? i1 + "," + i2 : i2 + "," + i1;
  }
}
