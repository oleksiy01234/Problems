package Sort;

/**
 * SearchInRotated
 */
public class SearchInRotated {

  public static void main(String[] args) {
    System.out.println(search(new int[] { 1, 1, 3, 1 }, 3));
    System.out.println(search2(new int[] { 1, 1, 3, 1 }, 3));
  }

  /**
   * No duplicates
   */
  static int search(int[] a, int target) {
    int lo = 0, hi = a.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;

      if (a[mid] == target) {
        return mid;
      }

      boolean rotationOnLeft = a[lo] > a[mid];
      boolean rotationOnRight = a[hi] < a[mid];

      if (a[mid] > target) {
        if (a[lo] <= target || rotationOnLeft) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      } else {
        if (a[hi] >= target || rotationOnRight) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }

    }

    return -1;
  }

  /**
   * Duplicates allowed
   */
  static int search2(int a[], int key) {
    int lo = 0;
    int hi = a.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;

      if (a[mid] == key) {
        return mid;
      }

      if (a[lo] < a[mid]) { // left half is sorted
        if (a[lo] <= key && key < a[mid]) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      } else if (a[lo] > a[mid]) { // right half is sorted
        if (a[mid] < key && key <= a[hi]) {
          lo = mid + 1;
        } else
          hi = mid - 1;
      } else {
        lo++;
      }
    }

    return -1;
  }
}