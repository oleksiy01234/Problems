import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util.java contains common utility functions used by many other classes.
 */
class Util {
  static int max(int n1, int n2, int n3) {
    return Math.max(n1, Math.max(n2, n2));
  }

  static int min(int n1, int n2, int n3) {
    return Math.min(n1, Math.min(n2, n2));
  }

  static void print(int[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        System.out.print(m[i][k] + "\t");
      }
      System.out.println();
    }
  }

  static void print(List<List<Integer>> lists) {
    for (List<Integer> list : lists) {
      System.out.print('[');
      for (int n : list) {
        System.out.print(n + "\t");
      }
      System.out.println(']');
    }
  }

  static void swap(int[] a, int i1, int i2) {
    if (a[i1] == a[i2]) {
      return;
    }

    int temp = a[i1];
    a[i1] = a[i2];
    a[i2] = temp;
  }

  static void swap(char[] a, int i1, int i2) {
    if (a[i1] == a[i2]) {
      return;
    }

    char temp = a[i1];
    a[i1] = a[i2];
    a[i2] = temp;
  }

  static List<Character> toList(char[] a) {
    List<Character> list = new ArrayList<>();

    for (char n : a) {
      list.add(n);
    }

    return list;
  }

  static Map<Character, Integer> fillMap(String s) {
    Map<Character, Integer> m = new HashMap<>();

    for (char c : s.toCharArray()) {
      m.put(c, m.getOrDefault(c, 0) + 1);
    }

    return m;
  }

  static Map<Integer, Integer> fillMap(int[] a) {
    Map<Integer, Integer> m = new HashMap<>();

    for (int n : a) {
      m.put(n, m.getOrDefault(n, 0) + 1);
    }

    return m;
  }
}