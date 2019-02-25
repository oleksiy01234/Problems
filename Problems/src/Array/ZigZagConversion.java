package Array;

public class ZigZagConversion {

  public static void main(String[] args) {
    System.out.println(convert("PAYPALISHIRING", 3));
    System.out.println(convert("PAYPALISHIRING", 4));
  }

  static class IndexGenerator {
    int maxIndex;
    int nextIndex = 0;
    boolean reversed = false;

    IndexGenerator(int maxIndex) {
      this.maxIndex = maxIndex;
    }

    int getNext() {
      int currentIndex = nextIndex;

      if (reversed) {
        nextIndex--;
      } else {
        nextIndex++;
      }

      if (nextIndex == 0) {
        reversed = false;
      } else if (nextIndex == maxIndex) {
        reversed = true;
      }

      return currentIndex;
    }
  }

  static String convert(String s, int n) {
    if (n == 1) {
      return s;
    }

    StringBuilder[] a = new StringBuilder[n];
    for (int i = 0; i < a.length; i++) {
      a[i] = new StringBuilder();
    }

    IndexGenerator index = new IndexGenerator(n - 1);

    for (int i = 0; i < s.length(); i++) {
      a[index.getNext()].append(s.charAt(i));
    }

    StringBuilder res = new StringBuilder();
    for (StringBuilder sb : a) {
      res.append(sb);
    }
    return res.toString();
  }
}