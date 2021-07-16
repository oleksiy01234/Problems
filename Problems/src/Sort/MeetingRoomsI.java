package Sort;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsI {
  public boolean canAttendMeetings(int[][] intervals) {
    if (intervals.length < 2) {
      return true;
    }

    // 1. sort by start time
    Arrays.sort(intervals, new Comparator<>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });

    int endTime = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < endTime) {
        return false;
      }

      endTime = Math.max(endTime, intervals[i][1]);
    }

    return true;
  }
}