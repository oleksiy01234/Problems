package Array;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
  // my set solution
  public int singleNumber(int[] a) {
    Set<Integer> set = new HashSet<>();

    for (int n : a) {
      if (set.contains(n)) {
        set.remove(n);
      } else {
        set.add(n);
      }
    }

    return set.iterator().next();
  }

  // xor
  public int singleNumber2(int[] nums) {
    int a = 0;
    for (int i : nums) {
      a ^= i;
    }
    return a;
  }

  // math?
  public int singleNumber3(int[] nums) {
    int sumOfSet = 0, sumOfNums = 0;
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
        sumOfSet += num;
      }
      sumOfNums += num;
    }
    return 2 * sumOfSet - sumOfNums;
  }
}
