package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "the sky is blue"
 * <p>
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "  hello world  "
 * <p>
 * Output: "world hello"
 * <p>
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "a good   example"
 * <p>
 * Output: "example good a"
 * <p>
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Example 4:
 * <p>
 * Input: s = "  Bob    Loves  Alice   "
 * <p>
 * Output: "Alice Loves Bob"
 * <p>
 * Example 5:
 * <p>
 * Input: s = "Alice does not even like bob"
 * <p>
 * Output: "bob like even not does Alice"
 */
public class ReverseWords {

  // string.split
  public String reverseWords(String s) {
    String[] words = s.split(" ");
    StringBuilder res = new StringBuilder();
    for (String word : words) {
      if (word.trim().isEmpty()) {
        continue;
      }
      res.insert(0, word.trim() + " ");
    }

    return res.toString().trim();
  }

  // add reversed words into list
  public String reverseWords2(String s) {
    List<String> words = new ArrayList<>();
    StringBuilder word = new StringBuilder();

    for (char c : s.toCharArray()) {
      if (c == ' ') {
        if (word.length() != 0) {
          words.add(word.toString());
          word.setLength(0);
        }
      } else {
        word.append(c);
      }
    }

    if (word.length() != 0) {
      words.add(word.toString());
    }

    StringBuilder res = new StringBuilder();
    for (String newWord : words) {
      if (res.length() > 0) {
        res.insert(0, " ");
      }

      res.insert(0, newWord);
    }

    return res.toString();
  }

  // in place array reverse (DOES NOT REMOVE EXTRA SPACES)
  public static void reverseWords(char[] a) {
    reverse(a, 0, a.length - 1);

    int start = 0;
    for (int i = 1; i <= a.length; i++) {
      if (i - start > 1 && (i == a.length || a[i] == ' ')) {
        reverse(a, start, i - 1);
        start = i + 1;
      }
    }
  }

  private static void reverse(char[] array, int start, int end) {
    while (start < end) {
      char temp = array[start];
      array[start] = array[end];
      array[end] = temp;
      start++;
      end--;
    }
  }
}
