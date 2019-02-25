package DP;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

  public String longestPalindrome(String s) {
    String max = "";

    for (int i = 0; i < s.length(); i++) {
      String len1 = maxPalindrome(s, i, i);
      String len2 = maxPalindrome(s, i, i + 1);
      String maxAroundI = longest(len1, len2);
      max = longest(max, maxAroundI);
    }

    return max;
  }

  static String longest(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return s1;
    } else {
      return s2;
    }
  }

  private String maxPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }

    return s.substring(left + 1, right);
  }

}