package Array;

import java.util.HashMap;
import java.util.Map;

import Util.Util;

class SlidingWindow {
  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return false;
    }

    Map<Character, Integer> initialMap = Util.fillMap(s1);
    Map<Character, Integer> slidingMap = new HashMap<>(initialMap);

    for (int i = 0; i < s1.length(); i++) {
      char c = s2.charAt(i);
      if (initialMap.containsKey(c)) {
        adjust(c, slidingMap, -1);
      }
    }

    int start = 0, end = s1.length();

    while (end < s2.length() && !slidingMap.isEmpty()) {
      char startChar = s2.charAt(start);
      char endChar = s2.charAt(end);

      if (initialMap.containsKey(startChar)) {
        adjust(startChar, slidingMap, 1);
      }

      if (initialMap.containsKey(endChar)) {
        adjust(endChar, slidingMap, -1);
      }
      start++;
      end++;
    }

    return slidingMap.isEmpty();
  }

  void adjust(char c, Map<Character, Integer> m, int adjustment) {
    m.put(c, m.getOrDefault(c, 0) + adjustment);
    m.remove(c, 0);
  }
}