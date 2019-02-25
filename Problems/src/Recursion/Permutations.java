package Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Util.Util;

class Permutations {
  public static void main(String[] args) {
    String s = "011";
    System.out.println(permute(s.toCharArray()).toString());
    System.out.println(permute2(s).toString());
    System.out.println(permute3(s).toString());
    System.out.println(permute4(s).toString());
    System.out.println(permute5(s).toString());
    System.out.println(permute6(new int[] { 0, 1, 1 }).toString());
  }

  /**
   * Method 1: Swapping. Doesn't differentiate unique and duplicate elements.
   */
  static List<List<Character>> permute(char[] a) {
    List<List<Character>> results = new ArrayList<>();
    permute(a, 0, results);
    return results;
  }

  private static void permute(char[] a, int start, List<List<Character>> results) {
    if (start >= a.length) {
      results.add(Util.toList(a));
      return;
    }

    for (int i = start; i < a.length; i++) {
      Util.swap(a, i, start);
      permute(a, start + 1, results);
      Util.swap(a, i, start);
    }
  }

  /**
   * Method 2: Building on permutations after this char. Putting it in all spots.
   */
  static List<String> permute2(String s) {
    List<String> permutations = new ArrayList<String>();
    if (s.isEmpty()) {
      permutations.add("");
    } else {

      char firstChar = s.charAt(0);
      String remainder = s.substring(1);
      List<String> permutationsAfterFirst = permute2(remainder);

      // Cycle permutations after this char
      for (String w : permutationsAfterFirst) {
        for (int j = 0; j <= w.length(); j++) {
          // add it in all possible positions of all those permutations
          permutations.add(w.substring(0, j) + firstChar + w.substring(j));
        }
      }

    }
    return permutations;
  }

  /**
   * Method 3: Similar to Method 2. Building on permutations of all n-1 substrings
   */
  public static List<String> permute3(String remainder) {
    List<String> result = new ArrayList<>();
    if (remainder.isEmpty()) {
      result.add("");
    } else {

      for (int i = 0; i < remainder.length(); i++) {
        // Remove char i and find permutations of remaining characters.
        String before = remainder.substring(0, i);
        String after = remainder.substring(i + 1);
        List<String> partials = permute3(before + after);

        // Prepend char i to all permutations
        for (String s : partials) {
          result.add(remainder.charAt(i) + s);
        }
      }

    }

    return result;
  }

  /**
   * Method 4: Push prefix down the stack. When base case, prefix is a full perm.
   */
  private static List<String> permute4(String s) {
    List<String> result = new ArrayList<>();
    permute4("", s, result);
    return result;
  }

  private static void permute4(String prefix, String suffix, List<String> results) {
    if (suffix.isEmpty()) {
      results.add(prefix);
    } else {
      for (int i = 0; i < suffix.length(); i++) {
        // cycle thru suffix, transfer every char into prefix. base case: suffix empty
        String newPrefix = prefix + suffix.charAt(i);
        String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1, suffix.length());
        permute4(newPrefix, newSuffix, results);
      }
    }
  }

  /**
   * Method 5: Prefix version. Detects duplicates by using a map
   */
  static List<String> permute5(String s) {
    List<String> result = new ArrayList<>();
    permute5(Util.fillMap(s), "", s.length(), result);
    return result;
  }

  private static void permute5(Map<Character, Integer> map, String prefix, int rem, List<String> res) {
    if (rem == 0) {
      res.add(prefix);
      return;
    }

    for (Character c : map.keySet()) {
      int count = map.get(c);
      if (count > 0) {
        map.put(c, count - 1);
        permute5(map, prefix + c, rem - 1, res);
        map.put(c, count + 1);
      }
    }
  }

  /**
   * Method 6: Like method 5, but array version.
   */
  static List<List<Integer>> permute6(int[] a) {
    List<List<Integer>> result = new ArrayList<>();
    permute6(result, new ArrayList<>(), Util.fillMap(a), a.length);
    return result;
  }

  private static void permute6(List<List<Integer>> res, List<Integer> curr, Map<Integer, Integer> m, int rem) {
    if (rem == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int n : m.keySet()) {
      int count = m.get(n);
      if (count > 0) {
        curr.add(n);
        m.put(n, count - 1);
        permute6(res, curr, m, rem - 1);
        m.put(n, count + 1);
        curr.remove(curr.size() - 1);
      }
    }
  }
}