package Array;

public class PeakElement {
  // iterative binary search o(1) space
  public int findPeakElement(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
      int mid = lo + ((hi - lo) / 2); // protects against overflow

      if (nums[mid] > nums[mid + 1]) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  // recursive binary search o(log n) space
  public int findPeakElement2(int[] nums) {
    return search(nums, 0, nums.length - 1);
  }

  public int search(int[] nums, int lo, int hi) {
    if (lo == hi) {
      return lo;
    }

    int mid = lo + ((hi - lo) / 2);
    if (nums[mid] > nums[mid + 1]) {
      return search(nums, lo, mid);
    }

    return search(nums, mid + 1, hi);
  }
}
