import java.util.Arrays;

class BitManipulation {
  public static void main(String[] args) {
    int sum = add(1, 10);
    String addString = Integer.toBinaryString(sum);
    System.out.println(sum + ", " + addString);

    int[] bitCounts = countBits(10);
    System.out.println(Arrays.toString(bitCounts));

    int bitCount = hammingWeight(13);
    System.out.println(bitCount + ", " + Integer.toBinaryString(13));
  }

  /**
   * Add 2 numbers without using arithmetic
   */
  private static int add(int a, int b) {
    while (b != 0) {
      int carry = a & b; // carry
      a = a ^ b; // sum
      b = carry << 1; // shift carry to 1 bit to calculate sum
    }
    return a;
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
}