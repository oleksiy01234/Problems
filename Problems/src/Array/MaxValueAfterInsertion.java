package Array;

/**
 * 1881. Maximum Value after Insertion
 * https://leetcode.com/problems/maximum-value-after-insertion/
 * <p>
 * You are given a very large integer n, represented as a string, and an integer digit x.
 * The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 * <p>
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n.
 * You cannot insert x to the left of the negative sign.
 * <p>
 * For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n after the insertion.
 * <p>
 * Example 1:
 * Input: n = "99", x = 9
 * Output: "999"
 * Explanation: The result is the same regardless of where you insert 9.
 * <p>
 * Example 2:
 * Input: n = "-13", x = 2
 * Output: "-123"
 * Explanation: You can make n one of {-213, -123, -132}, and the largest of those three is -123.
 */
public class MaxValueAfterInsertion {
  public String maxValue(String s, int n) {
    boolean positive = s.charAt(0) != '-';

    for (int i = positive ? 0 : 1; i < s.length(); i++) {
      int val = s.charAt(i) - '0';

      if ((positive && n > val) || (!positive && n < val)) {
        return s.substring(0, i) + n + s.substring(i);
      }

    }

    return s + n;
  }
}
