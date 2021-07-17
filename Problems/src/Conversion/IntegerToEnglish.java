package Conversion;

public class IntegerToEnglish {
  // convert single digit into a string
  public String convert1To9(int num) {
    return switch (num) {
      case 1 -> "One";
      case 2 -> "Two";
      case 3 -> "Three";
      case 4 -> "Four";
      case 5 -> "Five";
      case 6 -> "Six";
      case 7 -> "Seven";
      case 8 -> "Eight";
      case 9 -> "Nine";
      default -> "";
    };
  }

  public String convert10To19(int num) {
    return switch (num) {
      case 10 -> "Ten";
      case 11 -> "Eleven";
      case 12 -> "Twelve";
      case 13 -> "Thirteen";
      case 14 -> "Fourteen";
      case 15 -> "Fifteen";
      case 16 -> "Sixteen";
      case 17 -> "Seventeen";
      case 18 -> "Eighteen";
      case 19 -> "Nineteen";
      default -> "";
    };
  }

  public String convert20To90(int num) {
    num = (num / 10) * 10; // remove remainder
    return switch (num) {
      case 20 -> "Twenty";
      case 30 -> "Thirty";
      case 40 -> "Forty";
      case 50 -> "Fifty";
      case 60 -> "Sixty";
      case 70 -> "Seventy";
      case 80 -> "Eighty";
      case 90 -> "Ninety";
      default -> "";
    };
  }

  public String convert1To99(int num) {
    if (num == 0) {
      return "";
    } else if (num < 10) {
      return convert1To9(num);
    } else if (num < 20) {
      return convert10To19(num);
    } else {
      int rest = num % 10;
      if (rest != 0) {
        return convert20To90(num) + " " + convert1To9(rest);
      } else {
        return convert20To90(num);
      }
    }
  }

  // converts a triple digit into a string representation
  // e.g. three hundred sixty eight
  public String convert1To999(int num) {
    int hundred = num / 100;
    int rest = num % 100;
    String res = "";

    if (hundred != 0 && rest != 0) {
      res = convert1To9(hundred) + " Hundred " + convert1To99(rest);
    } else if (hundred != 0) {
      res = convert1To9(hundred) + " Hundred";
    } else {
      res = convert1To99(rest);
    }

    return res;
  }

  public String numberToWords(int num) {
    String result = "";

    int billion = num / 1000000000;
    if (billion != 0) {
      result += convert1To999(billion) + " Billion";
      num -= billion * 1000000000;
    }

    int million = num / 1000000;
    if (million != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }

      result += convert1To999(million) + " Million";
      num -= million * 1000000;
    }

    int thousand = num / 1000;
    if (thousand != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }

      result += convert1To999(thousand) + " Thousand";
      num -= thousand * 1000;
    }

    if (num != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }

      result += convert1To999(num);
    }

    return result.isEmpty() ? "Zero" : result;
  }
}
