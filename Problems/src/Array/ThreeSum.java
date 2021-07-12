package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

  List<List<Integer>> threeSum(int[] a) {
    return threeSum(a, 0);
  }

  /**
   * Find all unique triplets that sum up to k.
   */
  private List<List<Integer>> threeSum(int[] a, int k) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(a);

    for (int i = 0; i < a.length - 2; i++) {
      if (i > 0 && a[i] == a[i - 1]) {
        continue;
      }

      int start = i + 1;
      int end = a.length - 1;
      int target = k - a[i];

      while (start < end) {

        if (a[start] + a[end] > target) {
          end--; // sum is too high: decrement end
        } else if (a[start] + a[end] < target) {
          start++; // sum is too low: increment start
        } else {
          result.add(Arrays.asList(a[i], a[start], a[end]));

          do {
            start++; // loop forward until we encounter a different element
          } while (start < end && a[start] == a[start - 1]);

          do {
            end--; // loop backward until we encounter a different element
          } while (start < end && a[end] == a[end + 1]);

        }
      }
    }

    return result;
  }
}