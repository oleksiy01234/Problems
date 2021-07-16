package DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Pair<U, V> {
  public U key;
  public V value;

  public Pair(U key, V value) {
    this.key = key;
    this.value = value;
  }
}

class Bucket {
  private final List<Pair<Integer, Integer>> bucket = new LinkedList<>();

  public Integer get(Integer key) {
    for (Pair<Integer, Integer> pair : bucket) {
      if (pair.key.equals(key)) {
        return pair.value;
      }
    }
    return -1;
  }

  public void update(Integer key, Integer value) {
    boolean found = false;
    for (Pair<Integer, Integer> pair : bucket) {
      if (pair.key.equals(key)) {
        pair.value = value;
        found = true;
      }
    }

    if (!found) {
      bucket.add(new Pair<>(key, value));
    }
  }

  public void remove(Integer key) {
    for (Pair<Integer, Integer> pair : bucket) {
      if (pair.key.equals(key)) {
        bucket.remove(pair);
        break;
      }
    }
  }
}


/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj = new MyHashMap();
 * obj.put(key,value); int param_2 = obj.get(key); obj.remove(key);
 */

class MyHashMap {
  private final int KEY_SPACE = 2069;
  private final List<Bucket> hashMap = new ArrayList<>();

  /**
   * Initialize your data structure here.
   */
  public MyHashMap() {
    for (int i = 0; i < KEY_SPACE; i++) {
      hashMap.add(new Bucket());
    }
  }

  /**
   * value will always be non-negative.
   */
  public void put(int key, int value) {
    int hashKey = key % KEY_SPACE;
    hashMap.get(hashKey).update(key, value);
  }

  /**
   * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
   * for the key
   */
  public int get(int key) {
    int hashKey = key % KEY_SPACE;
    return hashMap.get(hashKey).get(key);
  }

  /**
   * Removes the mapping of the specified value key if this map contains a mapping for the key
   */
  public void remove(int key) {
    int hashKey = key % KEY_SPACE;
    hashMap.get(hashKey).remove(key);
  }
}