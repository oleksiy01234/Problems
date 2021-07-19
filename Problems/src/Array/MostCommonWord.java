package Array;

import java.util.*;

/**
 * 819. Most Common Word
 * https://leetcode.com/problems/most-common-word/
 * <p>
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * <p>
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * <p>
 * Example:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * <p>
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * <p>
 * Words in the paragraph are not case sensitive
 * Punctuation is ignored (even if adjacent to words, such as "ball,")
 * <p>
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {

  public String mostCommonWord(String paragraph, String[] banned) {
    List<String> words = extractWords(paragraph);

    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    Map<String, Integer> wordFrequencies = new HashMap<>();
    String maxWord = null;

    for (String word : words) {
      if (bannedSet.contains(word)) {
        continue;
      }

      wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);

      if (maxWord == null || wordFrequencies.get(word) > wordFrequencies.get(maxWord)) {
        maxWord = word;
      }
    }

    return maxWord;
  }

  private List<String> extractWords(String paragraph) {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    for (char c : paragraph.toCharArray()) {
      if (Character.isLetter(c)) {
        sb.append(c);
      } else {
        if (sb.length() > 0) {
          res.add(sb.toString().toLowerCase());
          sb.setLength(0);
        }
      }
    }

    if (sb.length() > 0) {
      res.add(sb.toString().toLowerCase());
    }

    return res;
  }
}