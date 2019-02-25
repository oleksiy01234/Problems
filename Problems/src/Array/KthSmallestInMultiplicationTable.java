package Array;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * KthSmallestInMultiplicationTable
 */
public class KthSmallestInMultiplicationTable {

  public static void main(String[] args) {
    findKthNumber(10, 8, 50);
  }

  static int findKthNumberHeap(int m, int n, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        queue.add(i * j);
        if (queue.size() > k) {
          queue.poll();
        }
      }
    }

    return queue.peek();
  }

  static int findKthNumber(int m, int n, int k) {
    int left = 1 * 1;
    int right = m * n;
    
    while (left < right) {
      int mid = left + (right - left) / 2;

      if (count(mid, m, n) < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    
    return right;
  }

  static int count(int value, int m, int n) {
    int i = m;
    int j = 1;
    int count = 0;
    while (i >= 1 && j <= n) {
      if (i * j <= value) {
        count += i;
        j++;
      } else {
        i--;
      }
    }
    return count;
  }

}