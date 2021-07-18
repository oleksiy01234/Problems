package Array;

/**
 * 344. Reverse A String (Easy): https://leetcode.com/problems/reverse-string/
 * <p>
 * Write a function that reverses a string in place
 */
public class ReverseString {
  public static void test() {
    ReverseString t = new ReverseString();
    System.out.println(t.reverseString2("abcd"));
    System.out.println(t.reverseString2("ab"));
    System.out.println(t.reverseString2("a"));
    System.out.println(t.reverseString2(""));
  }

  // iterative, single pointer. O(n) time, O(1) space
  public void reverseString(char[] s) {
    for (int i = 0; i < s.length / 2; i++) {
      swap(s, i, s.length - i - 1);
    }
  }

  // recursive, String, O(n) time and space
  public String reverseString2(String s) {
    if (s.length() < 2) {
      return s;
    }

    return reverseString2(s.substring(1)) + s.charAt(0);
  }

  // recursive, char[], O(n) time and space
  public void reverseString3(char[] s) {
    reverseString3(s, 0, s.length - 1);
  }

  void reverseString3(char[] s, int lo, int hi) {
    if (lo >= hi) {
      return;
    }

    swap(s, lo, hi);
    reverseString3(s, ++lo, --hi);
  }

  public void swap(char[] a, int i1, int i2) {
    char tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
}