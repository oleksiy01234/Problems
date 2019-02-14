import java.util.Arrays;

class NextPermutation {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(nextPermutation(new int[] { 1, 5, 1 })));
  }

  static int[] nextPermutation(int[] a) {
    int swapIndex = -1;

    // 1. find 2 elements in order (left < right)
    for (int i = a.length - 2; i >= 0; i--) {
      if (a[i] < a[i + 1]) {
        swapIndex = i;
        break;
      }
    }

    // 2. if there are none, this is a descending sequence: reverse it.
    if (swapIndex == -1) {
      reverse(a, 0, a.length - 1);
      return a;
    }

    // 3. find index of next greatest item after swap (for next perm)
    int indexOfNext = -1;
    for (int i = swapIndex + 1; i < a.length; i++) {
      if (a[i] <= a[swapIndex]) {
        break;
      }
      indexOfNext = i;
    }

    // 4. swap the items
    swap(a, swapIndex, indexOfNext);

    // 5. reverse the remaining elements on the right to be in order.
    reverse(a, swapIndex + 1, a.length - 1);

    return a;
  }

  static void reverse(int[] a, int i1, int i2) {
    while (i1 < i2) {
      swap(a, i1, i2);
      i1++;
      i2--;
    }
  }

  static void swap(int[] a, int i1, int i2) {
    if (a[i1] == a[i2]) {
      return;
    }

    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
}