package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicateNumber {
  // sorting: any 2 duplicate numbers will be adjacent o (n log n).
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        return nums[i];
      }
    }

    return -1;
  }

  // set: o(n) time, o(n) space
  public int findDuplicate2(int[] nums) {
    Set<Integer> seen = new HashSet<Integer>();
    for (int num : nums) {
      if (seen.contains(num)) {
        return num;
      }
      seen.add(num);
    }

    return -1;
  }

  // o(n) time, o(1) space. like linked list cycle
  public int findDuplicate3(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0], hare = nums[0];

    // find any intersection point in the cycle. at some point, hare and tortoise will intersect
    do {
      tortoise = nums[tortoise];
      hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Find the "entrance" to the cycle.
    // they will meet at the start of the cycle
    tortoise = nums[0];
    while (tortoise != hare) {
      tortoise = nums[tortoise];
      hare = nums[hare];
    }

    return hare;
  }

  // binary search o(n)
  public int findDuplicate4(int[] nums) {
    int lo = 0, hi = nums.length;

    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int count = 0;

      for (int i : nums) {
        if (i <= mid) {
          count++;
        }
      }

      if (count > mid) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

}
