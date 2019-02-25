package BitManipulation;

/**
 * Arithmetic
 */
public class Arithmetic {
  public static void main(String[] args) {
    int sum = add(1, 10);
    String addString = Integer.toBinaryString(sum);
    System.out.println(sum + ", " + addString);
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

}