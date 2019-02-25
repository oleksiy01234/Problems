package BitManipulation;

import java.util.Arrays;

class CountBits {
  public static void main(String[] args) {
    int[] bitCounts = countBits(10);
    System.out.println(Arrays.toString(bitCounts));

    int bitCount = hammingWeight(13);
    System.out.println(bitCount + ", " + Integer.toBinaryString(13));
  }

  /**
   * Count the number of bits in 0...n using DP
   */
  private static int[] countBits(int n) {
    int[] res = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      res[i] = res[i / 2] + (i % 2);
    }

    return res;
  }

  /**
   * Count the number of bits in a number
   */
  private static int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
      count += (n & 1); // n & 1 = 1 if the last bit is 1
      n >>>= 1; // shift right
    }

    return count;
  }

  /**
   * Find complement of a binary representation (5 or 101 -> 010)
   */
  static int findComplement(int n) {
    int res = 0;

    // 1. find lowest number higher than n with all 1s
    while (res < n) {
      res <<= 1;
      res |= 1;
    }

    // 2. subtract n from it. (1111 - 1010 = 0101)
    return res - n;
  }
}