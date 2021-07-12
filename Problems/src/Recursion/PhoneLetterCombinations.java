package Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 *  17. Letter Combinations of a Phone Number
 *  https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *  Given a string containing digits from 2-9 inclusive,
 *  return all possible letter combinations that the number could represent.
 *
 *  2 {'a', 'b', 'c'},
 *  3 {'d', 'e', 'f'},
 *  4 {'g', 'h', 'i'},
 *  5 {'j', 'k', 'l'},
 *  6 {'m', 'n', 'o'},
 *  7 {'p', 'q', 'r', 's'},
 *  8 {'t', 'u', 'v'},
 *  9 {'w', 'x', 'y', 'z'}
 */
public class PhoneLetterCombinations {
  private final char[][] mappings = {
      {},
      {},
      {'a', 'b', 'c'},
      {'d', 'e', 'f'},
      {'g', 'h', 'i'},
      {'j', 'k', 'l'},
      {'m', 'n', 'o'},
      {'p', 'q', 'r', 's'},
      {'t', 'u', 'v'},
      {'w', 'x', 'y', 'z'}
  };

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    permute("", digits, res);
    return res;
  }

  private void permute(String pref, String digits, List<String> res) {
    if (digits.isEmpty()) {
      if (!pref.isEmpty()) {
        res.add(pref);
      }
      return;
    }

    int firstDigit = Character.getNumericValue(digits.charAt(0));

    for (char letter : mappings[firstDigit]) {
      permute(pref + letter, digits.substring(1), res);
    }
  }
}
