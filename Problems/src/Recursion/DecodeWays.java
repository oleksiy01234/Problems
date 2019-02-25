package Recursion;

import java.util.Arrays;

/**
 * 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

  public static void main(String[] args) {
    System.out.println(numDecodings("01"));
  }

  /**
   * Recursive memoized solution
   */
  static int numDecodings2(String s) {
    int[] previousAnswers = new int[s.length() + 1];
    Arrays.fill(previousAnswers, -1);

    return numDecodings(s, 0, previousAnswers);
  }

  static int numDecodings(String s, int decodePointer, int[] previousAnswers) {
    if (decodePointer >= s.length()) {
      return 1;
    }

    if (previousAnswers[decodePointer] > -1) {
      return previousAnswers[decodePointer];
    }

    int totalWaysFromHere = 0;

    if (decodePointer < s.length()) {
      String firstWay = s.substring(decodePointer, decodePointer + 1);
      if (isValid(firstWay)) {
        totalWaysFromHere += numDecodings(s, decodePointer + 1, previousAnswers);
      }
    }

    if (decodePointer < s.length() - 1) {
      String secondWay = s.substring(decodePointer, decodePointer + 2);
      if (isValid(secondWay)) {
        totalWaysFromHere += numDecodings(s, decodePointer + 2, previousAnswers);
      }
    }

    previousAnswers[decodePointer] = totalWaysFromHere;
    return previousAnswers[decodePointer];
  }

  static boolean isValid(String s) {
    return s.charAt(0) != '0' && Integer.parseInt(s) <= 26;
  }

  /**
   * Iterative solution
   */
  static int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) != '0' ? 1 : 0;

    for (int i = 2; i < dp.length; i++) {
      if (isValid(s.substring(i - 1, i))) {
        dp[i] += dp[i - 1];
      }

      if (isValid(s.substring(i - 2, i))) {
        dp[i] += dp[i - 2];
      }
    }

    return dp[s.length()];
  }
}