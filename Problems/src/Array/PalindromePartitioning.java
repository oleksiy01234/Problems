package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * PalindromePartitioning
 */
public class PalindromePartitioning {

  public List<List<String>> partition(String s) {
    List<List<String>> validDecompositions = new ArrayList<>();
    decomposeString(s, 0, new ArrayList<>(), validDecompositions);
    return validDecompositions;
  }

  private void decomposeString(String s, int index, List<String> curr, List<List<String>> res) {
    if (index == s.length()) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = index; i < s.length(); i++) {
      String pal = s.substring(index, i + 1); // potential palindrome

      if (isPalindrome(pal)) {
        curr.add(pal);
        decomposeString(s, i + 1, curr, res);
        curr.remove(curr.size() - 1);
      }
    }
  }

  public boolean isPalindrome(String s) {
    return new StringBuilder(s).reverse().toString().equals(s);
  }

}