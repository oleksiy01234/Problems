package Array;

public class SumConsecutiveIntegers {

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      System.out.println(i + ": " + consecutiveNumbersSum(i));
      System.out.println(i + ": " + consecutiveNumbersSum2(i));
    }
  }

  static int consecutiveNumbersSum2(int n) {
    int sum = 1;
    int count = 1;

    int lo = 1;
    int hi = 2;

    while (hi <= n) {

      if (sum > n) {
        sum -= lo;
        lo++;
      } else if (sum < n) {
        sum += hi;
        hi++;
      } else {
        count++;
        sum += hi;
        hi++;
      }

    }

    return count;
  }

  static int consecutiveNumbersSum(int n) {
    int count = 0;

    for (int i = 1; getSumUpTo(i) <= n; i++) {
      int sum = n - getSumUpTo(i);

      if (sum > 0 && sum % i == 0) {
        count++;
      }
    }

    return count;
  }

  static int getSumUpTo(int n) {
    return n * (n - 1) / 2;
  }
}