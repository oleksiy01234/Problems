package DataStructures;

import java.util.*;

public class InsertDeleteGetRandom {
  Map<Integer, Integer> map = new HashMap<>();
  List<Integer> list = new ArrayList<>();
  Random rand = new Random();

  public boolean insert(int val) {
    if (map.containsKey(val)) {
      return false;
    }

    map.put(val, list.size());
    list.add(val);
    return true;
  }

  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }

    // move the last element to the place idx of the element to delete
    int lastElement = list.get(list.size() - 1);
    int idx = map.get(val);

    list.set(idx, lastElement);
    map.put(lastElement, idx);
    // delete the last element
    list.remove(list.size() - 1);
    map.remove(val);
    return true;
  }

  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}
