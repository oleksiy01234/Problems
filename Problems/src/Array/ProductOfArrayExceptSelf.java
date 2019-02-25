package Array;

import java.util.Arrays;

/**
 * ProductOfArrayExceptSelf
 */
public class ProductOfArrayExceptSelf {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(productExceptSelf(new int[] { 1, 2, 3, 4 })));
  }

  static int[] productExceptSelf(int[] a) {
    int[] productsBefore = new int[a.length];
    productsBefore[0] = 1;
    for (int i = 1; i < a.length; i++) {
      productsBefore[i] = productsBefore[i - 1] * a[i - 1];
    }

    int[] productsAfter = new int[a.length];
    productsAfter[productsAfter.length - 1] = 1;
    for (int i = a.length - 2; i >= 0; i--) {
      productsAfter[i] = productsAfter[i + 1] * a[i + 1];
    }

    int[] results = new int[a.length];
    for (int i = 0; i < results.length; i++) {
      results[i] = productsBefore[i] * productsAfter[i];
    }
    return results;
  }

  /**
   * Optimized solution that uses a single array
   */
  static int[] productExceptSelf2(int[] a) {
    int[] res = new int[a.length];
    res[0] = 1;

    // 1. fill products before i
    for (int i = 1; i < a.length; i++) {
      res[i] = res[i - 1] * a[i - 1];
    }

    // 2. fill products after i (start with 1, adjust temp every time)
    int temp = 1;
    for (int i = a.length - 1; i >= 0; i--) {
      res[i] *= temp;
      temp *= a[i];
    }

    return res;
  }
}