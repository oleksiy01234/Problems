package Array;

import java.util.List;

/**
 * AdvanceByOffsets
 */
public class AdvanceByOffsets {

  static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int furthestReachSoFar = 0;
    int lastIndex = maxAdvanceSteps.size() - 1;

    for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; i++) {
      furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
    }

    return furthestReachSoFar >= lastIndex;
  }

}