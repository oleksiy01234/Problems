package Array;

public class SolveEquation {

  public static void main(String[] args) {
    System.out.println(solveEquation("x+5-3+x=6+x-2")); // "x=2"
    System.out.println(solveEquation("x=x")); // "Infinite solutions"
    System.out.println(solveEquation("2x=x")); // "x=0"
    System.out.println(solveEquation("2x+3x-6x=x+2")); // "x=-1"
    System.out.println(solveEquation("x=x+2")); // "No solution"
  }

  static class Equation {
    // cValue + xValue*x
    int xValue = 0;
    int cValue = 0;
  }

  static String solveEquation(String s) {
    String[] sides = s.split("=");
    Equation left = new Equation();
    Equation right = new Equation();
    fillTerms(left, sides[0]);
    fillTerms(right, sides[1]);

    // move xValues to left, cValue to right
    int xVal = left.xValue - right.xValue;
    int cVal = right.cValue - left.cValue;

    if (xVal == 0) {
      return cVal == 0 ? "Infinite solutions" : "No solution";
    }

    return "x=" + cVal / xVal;
  }

  static void fillTerms(Equation e, String side) {
    Integer prev = null;
    boolean adding = true;

    for (char c : side.toCharArray()) {

      if (Character.isDigit(c)) {

        if (prev == null) {
          prev = 0;
        }
        prev *= 10;
        prev += Character.getNumericValue(c);

      } else if (c == 'x') {

        if (prev == null) {
          e.xValue += adding ? 1 : -1;
        } else {
          e.xValue += adding ? prev : -prev;
          prev = null;
        }

      } else {
        if (prev != null) {
          e.cValue += adding ? prev : -prev;
          prev = null;
        }

        adding = c == '+';
      }
    }

    if (prev != null) {
      e.cValue += adding ? prev : -prev;
    }
  }

}