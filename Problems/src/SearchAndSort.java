import Util.Util;

import java.util.*;

class SearchAndSort {

  static void bubbleSort(int[] array) {
    Util.print(array);

    for (int i = array.length - 1; i > 0; i--) {
      for (int k = 0; k < i; k++) {
        if (array[k + 1] < array[k]) {
          Util.swap(array, k, k + 1);
        }
      }
    }

    Util.print(array);
  }

  static void selectionSort(int[] array) {
    Util.print(array);

    for (int i = 0; i < array.length; i++) {
      int minIndex = i;
      boolean swap = false;

      for (int k = i + 1; k < array.length; k++) {
        if (array[k] < array[minIndex]) {
          minIndex = k;
          swap = true;
        }
      }

      if (swap) {
        Util.swap(array, minIndex, i);
      }
    }

    Util.print(array);
  }

  static void insertionSort(int[] array) {
    Util.print(array);

    for (int i = 1; i < array.length; i++) {
      for (int k = i - 1; k >= 0 && array[k] > array[k + 1]; k--) {
        Util.swap(array, k, k + 1);
      }
    }

    Util.print(array);
  }

  static void mergeSort(int[] a) {
    mergeSort(a, 0, a.length);
  }

  // hi exclusive
  private static void mergeSort(int[] a, int lo, int hi) {
    if (hi - lo < 2) {
      return;
    }

    int mid = (lo + hi) / 2;
    mergeSort(a, lo, mid);
    mergeSort(a, mid, hi);
    merge(a, lo, mid, hi);
  }

  private static void merge(int[] a, int lo, int mid, int hi) {
    if (a[mid] <= a[mid - 1]) {
      return;
    }

    int[] temp = new int[hi - lo];
    int p1 = lo;
    int p2 = mid;
    for (int tempIndex = 0; tempIndex < temp.length; tempIndex++) {

      if (p1 == mid) {
        temp[tempIndex] = a[p2++];
      } else if (p2 == hi) {
        temp[tempIndex] = a[p1++];
      } else {
        temp[tempIndex] = a[p1] >= a[p2] ? a[p1++] : a[p2++];
      }

    }

    System.arraycopy(temp, 0, a, lo, temp.length);
  }

  // for primitivesX; uses the fact that they have a finite number of bits.
  // Runtime O(kn). n = elements; k = passes.
  static int[] radixSort(int[] a) {
    int m = Util.getMax(a);

    for (int exp = 1; m / exp > 0; exp *= 10) {
      System.out.println("exp: " + exp);
      countSort(a, exp);
    }

    return a;
  }

  // assumes all numbers are positive
  private static void countSort(int[] a, int exp) {
    int output[] = new int[a.length];
    int count[] = new int[10];

    // Store count of occurrences in count[]
    for (int n : a) {
      count[(n / exp) % 10]++;
    }

    // Change count[i] so that count[i] now contains
    // actual position of this digit in output[]
    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build the output array
    for (int i = a.length - 1; i >= 0; i--) {
      output[count[(a[i] / exp) % 10] - 1] = a[i];
      count[(a[i] / exp) % 10]--;
    }

    System.arraycopy(output, 0, a, 0, output.length);
  }

  static int[] countingSort(int[] unorderedScores, int highestPossibleScore) {
    int[] a = new int[highestPossibleScore + 1];

    for (int n : unorderedScores) {
      a[n]++;
    }

    int[] orderedScores = new int[unorderedScores.length];
    int orderedIndex = 0;
    for (int i = a.length - 1; i >= 0; i--) {
      while (a[i] > 0) {
        orderedScores[orderedIndex] = i;
        orderedIndex++;
        a[i]--;
      }
    }

    return orderedScores;
  }

  // k = 2, a.length = 2
  static void flip(int[] a, int k) {
    for (int i = 0; i < k / 2; i++) {
      int tmp = a[i];
      a[i] = a[k - i - 1];
      a[k - i - 1] = tmp;
    }
  }

  static int[] bucketSort(int[] a) {
    int[] bucket = new int[Util.getMax(a) + 1];

    for (int n : a) {
      bucket[n]++;
    }

    int outPos = 0;
    for (int i = 0; i < bucket.length; i++) {
      for (int j = 0; j < bucket[i]; j++) {
        a[outPos] = i;
        outPos++;
      }
    }

    return a;
  }

  static int[] shellSort(int[] a) {
    int h = 1;
    while (h <= a.length / 3) {
      h = h * 3 + 1;
    }

    while (h > 0) {
      for (int outer = h; outer < a.length; outer++) {
        int temp = a[outer];
        int inner = outer;

        while (inner > h - 1 && a[inner - h] >= temp) {
          a[inner] = a[inner - h];
          inner -= h;
        }
        a[inner] = temp;
      }
      h = (h - 1) / 3;
    }

    return a;
  }

  // sort an array of integers as strings
  static String[] comparatorSort(String[] unsorted) {
    Arrays.sort(unsorted, new Comparator<String>() {
      @Override
      // x.compareTo(y): negative return iff x < y; zero iff x = y; positive iff x > y
      public int compare(String o1, String o2) {
        if (o1.length() != o2.length()) {
          return o1.length() - o2.length();
        }

        for (int i = 0; i < o1.length(); i++) {
          if (o1.charAt(i) != o2.charAt(i)) {
            return o1.charAt(i) > o2.charAt(i) ? 1 : -1;
          }
        }
        return 0;
      }
    });
    return unsorted;
  }

  static void groupAnagrams(String[] array) {
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    /* Group words by anagram */
    for (String s : array) {
      String key = sortChars(s);
      if (!map.containsKey(key)) {
        map.put(key, new ArrayList<>());
      }
      map.get(key).add(s);
    }

    /* Convert hash table to array */
    int index = 0;
    for (String key : map.keySet()) {
      ArrayList<String> list = map.get(key);
      for (String t : list) {
        array[index] = t;
        index++;
      }
    }
  }

  public static String sortChars(String s) {
    char[] content = s.toCharArray();
    Arrays.sort(content);
    return new String(content);
  }

  static int binarySearch(int[] a, int lo, int hi, int n) {
    if (lo > hi) {
      return -1;
    }

    int mid = (lo + hi) / 2;

    if (a[mid] < n) {
      return binarySearch(a, mid + 1, hi, n);
    } else if (a[mid] > n) {
      return binarySearch(a, lo, mid - 1, n);
    } else {
      return mid;
    }
  }

  private static int searchWithoutLength(List<Integer> list, int value, int lo, int hi) {
    int mid;

    while (lo <= hi) {
      mid = (lo + hi) / 2;
      int midValue = list.get(mid);
      if (midValue > value || midValue == -1) {
        hi = mid - 1;
      } else if (midValue < value) {
        lo = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  // pretend the list doesn't have a .size() method. Do a binary search for value.
  static int searchWithoutLength(List<Integer> list, int value) {
    int index = 1;
    while (list.get(index) != -1 && list.get(index) < value) {
      index *= 2;
    }
    return searchWithoutLength(list, value, index / 2, index);
  }

  // rearrange array in alternating order: a[0] <= a[1] >= a[2] <= a[3] etc.
  static void sortPeaksAndValleys(int[] array) {
    for (int i = 1; i < array.length; i += 2) {
      if (array[i - 1] < array[i]) {
        Util.swap(array, i - 1, i);
      }
      if (i + 1 < array.length && array[i + 1] < array[i]) {
        Util.swap(array, i + 1, i);
      }
    }
  }

  // find index i where i = a[i]
  static void magicIndex(int[] a) {
    int lo = 0;
    int hi = a.length - 1;

    while (lo <= hi) {
      int mid = (lo + hi) / 2;

      if (a[mid] > mid) {
        hi = mid - 1;
      } else if (a[mid] < mid) {
        lo = mid + 1;
      } else {
        System.out.println("Magic index: " + mid);
        return;
      }
    }

    System.out.println("No magic index found.");
  }

  public double findMedianSortedArrays(int[] a, int[] b) {
    int len = a.length + b.length;
    if (len % 2 == 0) {
      double left = (double) findKthHelper(a, 0, b, 0, len / 2);
      double right = (double) findKthHelper(a, 0, b, 0, len / 2 + 1);
      return (left + right) / 2;
    } else {
      return findKthHelper(a, 0, b, 0, len / 2 + 1);
    }
  }

  private int findKthHelper(int[] a, int aStart, int[] b, int bStart, int k) {
    if (aStart >= a.length) {
      return b[bStart + k - 1];
    }

    if (bStart >= b.length) {
      return a[aStart + k - 1];
    }

    if (k == 1) {
      return Math.min(a[aStart], b[bStart]);
    }

    int aMid = aStart + k / 2 - 1;
    int bMid = bStart + k / 2 - 1;
    int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
    int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];

    if (aVal <= bVal) {
      return findKthHelper(a, aMid + 1, b, bStart, k - k / 2);
    } else {
      return findKthHelper(a, aStart, b, bMid + 1, k - k / 2);
    }
  }

  int subarraySum(int[] a, int k) {
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    List<Integer> initial = new ArrayList<Integer>();
    initial.add(-1);
    map.put(0, initial);
    int preSum = 0;
    int count = 0;

    for (int i = 0; i < a.length; i++) {
      preSum += a[i];

      if (map.containsKey(preSum - k)) {
        count += map.get(preSum - k).size();
      }

      List<Integer> newStart = map.getOrDefault(preSum, new ArrayList<Integer>());
      newStart.add(i);
      map.put(preSum, newStart);
    }

    return count;
  }

  int subarraySum2(int[] a, int k) {
    int count = 0;
    int sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    for (int n : a) {
      sum += n;
      count += map.getOrDefault(sum - k, 0);
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return count;
  }

  List<List<Integer>> fourSum(int[] a, int k) {
    Arrays.sort(a);
    List<List<Integer>> result = new ArrayList<>();

    for (int j = 0; j < a.length - 3; j++) {
      if (j > 0 && a[j] == a[j - 1]) {
        continue;
      }

      for (int i = j + 1; i < a.length - 2; i++) {
        if (i > j + 1 && a[i] == a[i - 1]) {
          continue;
        }

        int startIndex = i + 1;
        int endIndex = a.length - 1;
        int targetSum = k - a[j] - a[i];

        while (startIndex < endIndex) {
          if (a[startIndex] + a[endIndex] == targetSum) {
            result.add(Arrays.asList(a[j], a[i], a[startIndex], a[endIndex]));

            while (startIndex < endIndex && a[startIndex] == a[startIndex + 1]) {
              startIndex++;
            }

            while (startIndex < endIndex && a[endIndex] == a[endIndex - 1]) {
              endIndex--;
            }

            startIndex++;
            endIndex--;
          } else if (a[startIndex] + a[endIndex] < targetSum) {
            startIndex++;
          } else {
            endIndex--;
          }

        }
      }

    }
    return result;
  }

  static int binarySearchRange(int[] a, int n, boolean earliest) {
    int lo = 0;
    int hi = a.length - 1;

    while (lo <= hi) {
      if (lo == hi) {
        return n == a[lo] ? lo : -1;
      }

      int mid;
      if (earliest) {
        mid = (lo + hi) / 2;
      } else {
        mid = (lo + hi + 1) / 2;
      }

      if (n < a[mid]) {
        hi = mid - 1;
      } else if (a[mid] < n) {
        lo = mid + 1;
      } else {
        if (earliest) {
          hi = mid;
        } else {
          lo = mid;
        }
      }
    }

    return -1;
  }

  private static class Meeting {
    private int startTime, endTime;
  }

  public static List<Meeting> mergeRanges(List<Meeting> a) {
    Collections.sort(a, new Comparator<Meeting>() {
      @Override
      public int compare(Meeting o1, Meeting o2) {
        return o1.startTime - o2.startTime;
      }
    });

    List<Meeting> res = new ArrayList<>();
    Meeting m = a.get(0);

    for (int i = 1; i < a.size(); i++) {
      if (a.get(i).startTime <= m.endTime) {
        m.endTime = Math.max(m.endTime, a.get(i).endTime);
      } else {
        res.add(m);
        m = a.get(i);
      }
    }

    res.add(m);
    return res;
  }

  public static void inPlaceShuffle(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      int randomIndex = Util.constructInt(i, a.length - 1);
      Util.swap(a, randomIndex, i);
    }
  }
}