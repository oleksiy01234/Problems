package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleImmutableEntry;

class RomanNumerals {
  public static void main(String[] args) {
    System.out.println(romanToInt("XIX"));
    System.out.println(intToRoman(1992));
  }

  private static int romanToInt(String s) {
    Map<Character, Integer> romanNumerals = generateMap();
    int result = 0;

    for (int i = 0; i < s.length(); i++) {
      int thisValue = romanNumerals.get(s.charAt(i));
      if (i < s.length() - 1 && thisValue < romanNumerals.get(s.charAt(i + 1))) {
        result -= thisValue;
      } else {
        result += thisValue;
      }
    }

    return result;
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

  private static String intToRoman(int n) {
    List<SimpleImmutableEntry<Integer, String>> map = generateIntMap();
    StringBuilder sb = new StringBuilder();

    for (SimpleImmutableEntry<Integer, String> entry : map) {
      while (n >= entry.getKey()) {
        sb.append(entry.getValue());
        n -= entry.getKey();
      }
    }

    return sb.toString();
  }

  private static List<SimpleImmutableEntry<Integer, String>> generateIntMap() {
    List<SimpleImmutableEntry<Integer, String>> list = new ArrayList<>();

    list.add(new SimpleImmutableEntry<>(1000, "M"));
    list.add(new SimpleImmutableEntry<>(900, "CM"));
    list.add(new SimpleImmutableEntry<>(500, "D"));
    list.add(new SimpleImmutableEntry<>(400, "CD"));
    list.add(new SimpleImmutableEntry<>(100, "C"));
    list.add(new SimpleImmutableEntry<>(90, "XC"));
    list.add(new SimpleImmutableEntry<>(50, "L"));
    list.add(new SimpleImmutableEntry<>(40, "XL"));
    list.add(new SimpleImmutableEntry<>(10, "X"));
    list.add(new SimpleImmutableEntry<>(9, "IX"));
    list.add(new SimpleImmutableEntry<>(5, "V"));
    list.add(new SimpleImmutableEntry<>(4, "IV"));
    list.add(new SimpleImmutableEntry<>(1, "I"));

    return list;
  }

}