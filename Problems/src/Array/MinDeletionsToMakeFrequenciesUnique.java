package Array;

import java.util.*;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 */
public class MinDeletionsToMakeFrequenciesUnique {

  public static void test() {
    System.out.println(new MinDeletionsToMakeFrequenciesUnique().minDeletions("aab"));
    System.out.println(new MinDeletionsToMakeFrequenciesUnique().minDeletions("aaabbbcc"));
    System.out.println(new MinDeletionsToMakeFrequenciesUnique().minDeletions("ceabaacb"));
  }

  // set solution
  public int minDeletions2(String s) {
    Map<Character, Integer> frequencies = toMap(s);
    Set<Integer> freqSet = new HashSet<>();

    int count = 0;

    for (Integer frequency : frequencies.values()) {
      while (frequency > 0 && freqSet.contains(frequency)) {
        frequency--;
        count++;
      }
      freqSet.add(frequency);
    }

    return count;
  }

  // pq solution
  public int minDeletions(String s) {
    Map<Character, Integer> frequencies = toMap(s);

    PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> frequencies.get(c2) - frequencies.get(c1));
    pq.addAll(frequencies.keySet());

    int count = 0;
    while (!pq.isEmpty()) {
      if (pq.size() == 1) {
        break;
      }

      char c = pq.poll();

      if (frequencies.get(c).equals(frequencies.get(pq.peek()))) {
        count++;
        frequencies.put(c, frequencies.get(c) - 1);
        if (frequencies.get(c) == 0) {
          frequencies.remove(c);
        } else {
          pq.add(c);
        }
      }
    }

    return count;
  }

  private Map<Character, Integer> toMap(String s) {
    Map<Character, Integer> map = new HashMap<>();

    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    return map;
  }
}
