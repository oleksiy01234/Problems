package Array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class RomanNumerals {
  public static void main(String[] args) {
    System.out.println(romanToInt("XIX"));
    System.out.println(intToRoman(1992));
  }

  public static int romanToInt(String s) {
    Map<Character, Integer> romanNumerals = generateMap();
    int res = 0;

    for (int i = 0; i < s.length(); i++) {
      int thisValue = romanNumerals.get(s.charAt(i));
      if (i < s.length() - 1 && thisValue < romanNumerals.get(s.charAt(i + 1))) {
        res -= thisValue;
      } else {
        res += thisValue;
      }
    }

    return res;
  }

  private static Map<Character, Integer> generateMap() {
    Map<Character, Integer> romanNumerals = new HashMap<>();

    romanNumerals.put('I', 1);
    romanNumerals.put('V', 5);
    romanNumerals.put('X', 10);
    romanNumerals.put('L', 50);
    romanNumerals.put('C', 100);
    romanNumerals.put('D', 500);
    romanNumerals.put('M', 1000);

    return romanNumerals;
  }

  public static String intToRoman(int n) {
    Map<Integer, String> map = generateIntMap();
    StringBuilder sb = new StringBuilder();

    for (int key : map.keySet()) {
      while (n >= key) {
        sb.append(map.get(key));
        n -= key;
      }
    }

    return sb.toString();
  }

  private static Map<Integer, String> generateIntMap() {
    Map<Integer, String> map = new TreeMap<>((o1, o2) -> o2 - o1);

    map.put(1000, "M");
    map.put(900, "CM");
    map.put(500, "D");
    map.put(400, "CD");
    map.put(100, "C");
    map.put(90, "XC");
    map.put(50, "L");
    map.put(40, "XL");
    map.put(10, "X");
    map.put(9, "IX");
    map.put(5, "V");
    map.put(4, "IV");
    map.put(1, "I");

    return map;
  }

}