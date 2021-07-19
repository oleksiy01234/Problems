package Array;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
  public List<String> removeComments(String[] source) {
    List<String> result = new ArrayList<>();

    boolean multiLineCommentStarted = false;
    StringBuilder sb = new StringBuilder();

    for (String line : source) {
      for (int i = 0; i < line.length(); i++) {
        // Single line Comment Scenario: we don't want to add any more characters, so break
        if (!multiLineCommentStarted && isStartOfSingleComment(i, line)) {
          break;
        }

        // Start of Multi line Comment. We might want to add some IF it closes
        if (!multiLineCommentStarted && isStartOfMultiComment(i, line)) {
          multiLineCommentStarted = true;
          i++;  // increment i to go past the other part of the comment (*)
        }

        //End of multi line comment
        else if (multiLineCommentStarted && isEndOfMultiComment(i, line)) {
          multiLineCommentStarted = false;
          i++;
        }

        // append only if multiLineComment is false
        else if (!multiLineCommentStarted) {
          sb.append(line.charAt(i));
        }
      }

      // At the end of each line of code, check if multi line comment is false, we can add
      if (!multiLineCommentStarted && sb.length() != 0) {
        result.add(sb.toString());
        sb.setLength(0);   // reset for next line
      }
    }

    return result;
  }

  private boolean isStartOfSingleComment(int i, String s) {
    return i < s.length() - 1 && s.charAt(i) == '/' && s.charAt(i + 1) == '/';
  }

  private boolean isStartOfMultiComment(int i, String s) {
    return i < s.length() - 1 && s.charAt(i) == '/' && s.charAt(i + 1) == '*';
  }

  private boolean isEndOfMultiComment(int i, String s) {
    return i < s.length() - 1 && s.charAt(i) == '*' && s.charAt(i + 1) == '/';
  }
}
