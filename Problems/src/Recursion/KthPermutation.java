package Recursion;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {

  public static void main(String[] args) {
    for (int i = 2; i <= 24; i++) {
      System.out.println(i + "th perm: " + getPermutation(4, i));
    }
  }

  static class PermutationCounter {
    int count;
    String permutation = "";
  }

  static String getPermutationNaive(int n, int k) {
    PermutationCounter pNumber = new PermutationCounter();
    pNumber.count = k;
    permute("", pNumber, new boolean[n]);
    return pNumber.permutation;
  }

  static void permute(String pref, PermutationCounter pNumber, boolean[] used) {
    if (pNumber.count == 0) {
      return;
    }

    if (pref.length() == used.length) {
      pNumber.count--;
      pNumber.permutation = pref;
      return;
    }

    for (int i = 0; i < used.length; i++) {
      if (used[i]) {
        continue;
      }

      used[i] = true;
      permute(pref + (i + 1), pNumber, used);
      used[i] = false;
    }
  }

  static String getPermutation(int n, int k) {
    StringBuilder sb = new StringBuilder();
    List<Integer> num = new ArrayList<>();
    int factorial = 1;

    for (int i = 1; i <= n; i++) {
      factorial *= i;
      num.add(i);
    }

    for (int i = 0, l = k - 1; i < n; i++) {
      factorial /= (n - i);
      int index = l / factorial;
      sb.append(num.remove(index));
      l -= index * factorial;
    }

    return sb.toString();
  }
}