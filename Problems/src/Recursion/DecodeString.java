package Recursion;

/**
 * 394. Decode String
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

  public static void main(String[] args) {
    System.out.println(decodeString("3[a]2[bc]")); // return "aaabcbc"
    System.out.println(decodeString("3[a2[c]]")); // return "accaccacc"
    System.out.println(decodeString("2[abc]3[cd]ef")); // return "abcabccdcdcdef"
    System.out.println(decodeString("2[15[3[u]1[ga]abc][]3[cd]ef]")); // return "abcabccdcdcdef"
    System.out.println(decodeString("[]")); // return "abcabccdcdcdef"
    System.out.println(decodeString("a")); // return "abcabccdcdcdef"
    System.out.println(decodeString("[a]")); // return "abcabccdcdcdef"
  }

  static String decodeString(String s) {
    StringBuilder result = new StringBuilder();
    StringBuilder potentialK = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (Character.isDigit(c)) {
        potentialK.append(c);
      } else if (c == '[') {
        int closingBracketIndex = getClosingBracketIndex(s, i);
        String unprocessedBracketContent = s.substring(i + 1, closingBracketIndex);
        String bracketContent = decodeString(unprocessedBracketContent);
        for (int k = generateK(potentialK); k > 0; k--) {
          result.append(bracketContent);
        }
        i = closingBracketIndex;
        potentialK.setLength(0);
      } else {
        result.append(c);
      }
    }

    return result.toString();
  }

  static int generateK(StringBuilder potentialK) {
    return potentialK.length() > 0 ? Integer.parseInt(potentialK.toString()) : 1;
  }

  static int getClosingBracketIndex(String s, int openingBracketIndex) {
    int openCount = 0;

    for (int i = openingBracketIndex; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '[') {
        openCount++;
      } else if (c == ']') {
        openCount--;
        if (openCount == 0) {
          return i;
        }
      }
    }

    return -1; 
  }

  /* 
          Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();

  */
}