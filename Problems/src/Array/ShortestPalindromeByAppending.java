package Array;

public class ShortestPalindromeByAppending {
  public static void main(String[] args) {
    System.out.println(shortestPalindrome("DABCB"));
  }

  public static String shortestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }

    int firstPalIndex = findFirstPalIndex(s);

    return s + reverse(s.substring(0, firstPalIndex));
  }

  private static String reverse(String s) {
    StringBuilder sb = new StringBuilder();

    for (char c : s.toCharArray()) {
      sb.insert(0, c);
    }

    return sb.toString();
  }

  private static int findFirstPalIndex(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (isPal(s.substring(i))) {
        return i;
      }
    }

    // couldn't find a pal that starts earlier than the last index
    return s.length() - 1;
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
