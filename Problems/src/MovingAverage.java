import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {
  int maxSize;
  double sum = 0;
  Queue<Integer> q = new LinkedList<>();

  MovingAverage(int size) {
    maxSize = size;
  }

  double next(int val) {
    if (q.size() == maxSize) {
      sum -= q.poll();
    }

    sum += val;
    q.add(val);
    return sum / Math.min(q.size(), maxSize);
  }
}