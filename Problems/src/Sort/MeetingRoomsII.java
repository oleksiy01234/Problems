package Sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

  public class IntervalPoint {
    public final boolean start;
    public final int timestamp;

    public IntervalPoint(boolean start, int timestamp) {
      this.start = start;
      this.timestamp = timestamp;
    }

    @Override
    public String toString() {
      return (start ? "start: " : "end: ") + timestamp;
    }
  }

  // my 1-heap soln
  public int minMeetingRooms(int[][] intervals) {
    PriorityQueue<IntervalPoint> pq = new PriorityQueue<>(new Comparator<>() {
      @Override
      public int compare(IntervalPoint i1, IntervalPoint i2) {
        if (i1.timestamp == i2.timestamp) {
          return i1.start ? 1 : -1;
        }

        return i1.timestamp - i2.timestamp;
      }
    });

    for (int[] interval : intervals) {
      pq.add(new IntervalPoint(true, interval[0]));
      pq.add(new IntervalPoint(false, interval[1]));
    }

    int max = 0;
    int cur = 0;
    while (!pq.isEmpty()) {
      IntervalPoint ip = pq.poll();
      if (ip.start) {
        cur++;
        max = Math.max(cur, max);
      } else {
        cur--;
      }
    }

    return max;
  }

  // 2 heap soln
  public int minMeetingRooms2(int[][] intervals) {
    PriorityQueue<Integer> startTimes = new PriorityQueue<>();
    PriorityQueue<Integer> endTimes = new PriorityQueue<>();

    int count = 0;

    for (int[] interval : intervals) {
      startTimes.add(interval[0]);
      endTimes.add(interval[1]);
    }

    while (!startTimes.isEmpty()) {
      int start = startTimes.poll();
      if (start < endTimes.peek()) {
        count++;
      } else {
        endTimes.poll();
      }
    }

    return count;
  }
}
