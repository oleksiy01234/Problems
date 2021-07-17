package Sort;

import java.util.*;

/**
 * You have developed a maps application, which shows roads with traffic. You want to find out avergae speed on each road segment. You are given <start, end, speed> of cars. Find the average speed on each segment.
 * <p>
 * Example Input: [[0, 14, 90], [3, 15, 80]]
 * <p>
 * Output: [[0,3, 90], [3, 14, 85], [14, 15, 80]]
 */
public class AverageSegmentSpeed {
  public static void main(String[] args) {
    System.out.println(Arrays.deepToString(computeAverageSpeeds(new int[][]{{0, 14, 90}, {3, 15, 80}})));
    System.out.println(Arrays.deepToString(computeAverageSpeeds(new int[][]{{0, 14, 90}, {3, 14, 80}})));
    System.out.println(Arrays.deepToString(computeAverageSpeeds(new int[][]{{0, 14, 90}, {0, 15, 80}})));
    System.out.println(Arrays.deepToString(computeAverageSpeeds(new int[][]{{0, 14, 90}, {0, 14, 80}})));
    System.out.println(Arrays.deepToString(computeAverageSpeeds(new int[][]{{0, 14, 90}, {4, 10, 80}})));
  }

  static int[][] computeAverageSpeeds(int[][] carSpeeds) {
    PriorityQueue<SegmentPoint> pq = new PriorityQueue<>(new Comparator<SegmentPoint>() {
      @Override
      public int compare(SegmentPoint o1, SegmentPoint o2) {
        if (o1.location == o2.location) {
          return o1.isStart ? 1 : -1;
        }

        return o1.location - o2.location;
      }
    });

    for (int[] carSpeed : carSpeeds) {
      pq.add(new SegmentPoint(true, carSpeed[0], carSpeed[2]));
      pq.add(new SegmentPoint(false, carSpeed[1], carSpeed[2]));
    }

    int speedsCount = 0;
    int totalSpeed = 0;
    int lastLocation = 0;

    List<int[]> resList = new ArrayList<>();

    while (!pq.isEmpty()) {
      SegmentPoint sp = pq.poll();

      if (lastLocation != sp.location) {
        resList.add(new int[]{lastLocation, sp.location, totalSpeed / speedsCount});
        lastLocation = sp.location;
      }

      if (sp.isStart) {
        speedsCount++;
        totalSpeed += sp.speed;
      } else {
        speedsCount--;
        totalSpeed -= sp.speed;
      }
    }

    return toList(resList);
  }

  private static int[][] toList(List<int[]> list) {
    int[][] res = new int[list.size()][list.get(0).length];
    for (int i = 0; i < list.size(); i++) {
      res[i] = list.get(i);
    }
    return res;
  }

  static class SegmentPoint {
    boolean isStart; // whether it's a start or an end
    int location;
    int speed; // speed starting/ending at this location

    public SegmentPoint(boolean isStart, int location, int speed) {
      this.isStart = isStart;
      this.location = location;
      this.speed = speed;
    }
  }
}
