package Array;

public class ShortestPalindromeByPrepending {
  public static void main(String[] args) {
    System.out.println(shortestPalindrome("babcd"));
  }

  public static String shortestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }

    int firstNonPalIndex = findFirstNonPalIndex(s);

    return reverse(s.substring(firstNonPalIndex)) + s;
  }

  private static String reverse(String s) {
    StringBuilder sb = new StringBuilder();

    for (char c : s.toCharArray()) {
      sb.insert(0, c);
    }

    return sb.toString();
  }

  private static int findFirstNonPalIndex(String s) {
    for (int i = s.length(); i > 1; i--) {
      if (isPal(s.substring(0, i))) {
        return i;
      }
    }

    // couldn't find a pal that ends later than the first index
    return 1;
  }

  private static boolean isPal(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
        return false;
      }
    }

    return true;
  }
}
