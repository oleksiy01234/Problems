package Array;

import java.util.Map;

import Util.Util;

/**
 * LongestSubstringWithAtLeastKRepeatingCharacters
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

  public int longestSubstringToDo(String s, int k) {
    Map<Character, Integer> charMap = Util.fillMap(s);
    for (char c : charMap.keySet()) {
      if (charMap.get(c) < k) {
        return s.length();
      }
    }

    return 0;
  }

  public int longestSubstring(String s, int k) {
    if (s == null || s.length() < k) {
      return 0;
    }

    return helper(s.toCharArray(), 0, s.length() - 1, k);
  }

  private int helper(char[] arr, int start, int end, int k) {
    if (end - start + 1 < k) {
      return 0;
    }
    int[] dict = new int[26];
    for (int i = start; i <= end; i++) {
      dict[arr[i] - 'a']++;
    }
    for (int i = 0; i < 26; i++) {
      if (dict[i] == 0) {
        continue;
      } else if (dict[i] < k) {
        for (int j = start; j <= end; j++) {
          if (arr[j] == (char) ('a' + i)) {
            int left = helper(arr, start, j - 1, k);
            int right = helper(arr, j + 1, end, k);
            return Math.max(left, right);
          }
        }
      }
    }
    return end - start + 1;
  }
}