package Array;

import java.util.HashMap;
import java.util.Map;

class SubarraySum {
  static int subarraySum(int[] a, int k) {
    int count = 0;
    int[] sums = new int[a.length + 1];
    sums[0] = 0;

    for (int i = 1; i <= a.length; i++) {
      sums[i] = sums[i - 1] + a[i - 1];
    }

    for (int i = 0; i < sums.length; i++) {
      for (int j = i + 1; j < sums.length; j++) {
        if (sums[j] - sums[i] == k) {
          count++;
        }
      }
    }

    return count;
  }

  static int subarraySum2(int[] a, int k) {
    int count = 0;

    for (int i = 0; i < a.length; i++) {
      int sum = 0;

      for (int j = i; j < a.length; j++) {
        sum += a[j];
        if (sum == k) {
          count++;
        }
      }
    }

    return count;
  }

  /**
   * O(n)
   */
  static int subarraySum3(int[] a, int k) {
    int count = 0, sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
      if (map.containsKey(sum - k)) {
        count += map.get(sum - k);
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }
}