package Array;

/**
 * RangeSumQuery
 */
public class RangeSumQuery {
  private int[] sums;
  private int[] a;

  public RangeSumQuery(int[] a) {
    sums = new int[a.length + 1];
    this.a = a;
    for (int i = 0; i < a.length; i++) {
      sums[i + 1] = sums[i] + a[i];
    }
  }

  public int sumRange(int from, int to) {
    return sums[to + 1] - sums[from];
  }

  public void update(int index, int val) {
    a[index] = val;
    for (int i = index; i < a.length; i++) {
      sums[i + 1] = sums[i] + a[i];
    }
  }
}