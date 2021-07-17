package Conversion;

public class IntegerToEnglish {
  // convert single digit into a string
  public String convert1To9(int num) {
    switch (num) {
      case 1:
        return "One";
      case 2:
        return "Two";
      case 3:
        return "Three";
      case 4:
        return "Four";
      case 5:
        return "Five";
      case 6:
        return "Six";
      case 7:
        return "Seven";
      case 8:
        return "Eight";
      case 9:
        return "Nine";
      default:
        return "";
    }
  }

  public String convert10To19(int num) {
    switch (num) {
      case 10:
        return "Ten";
      case 11:
        return "Eleven";
      case 12:
        return "Twelve";
      case 13:
        return "Thirteen";
      case 14:
        return "Fourteen";
      case 15:
        return "Fifteen";
      case 16:
        return "Sixteen";
      case 17:
        return "Seventeen";
      case 18:
        return "Eighteen";
      case 19:
        return "Nineteen";
      default:
        return "";
    }
  }

  public String convert20To90(int num) {
    num = (num / 10) * 10; // remove remainder
    switch (num) {
      case 20:
        return "Twenty";
      case 30:
        return "Thirty";
      case 40:
        return "Forty";
      case 50:
        return "Fifty";
      case 60:
        return "Sixty";
      case 70:
        return "Seventy";
      case 80:
        return "Eighty";
      case 90:
        return "Ninety";
      default:
        return "";
    }
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
    String res;

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
