package Sort;

import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {
  public enum Color {
    RED, WHITE, BLUE
  }

  public static void dutchFlagPartition(int pivotIndex, List<Color> a) {
    Color pivot = a.get(pivotIndex);

    // First pass: group elements smaller than pivot
    int smaller = 0;
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i).ordinal() < pivot.ordinal()) {
        Collections.swap(a, smaller, i);
        smaller++;
      }
    }

    // Second pass: group elements larger than pivot
    int larger = a.size() - 1;
    for (int i = a.size() - 1; i >= 0; i--) {
      if (a.get(i).ordinal() < pivot.ordinal()) {
        break;
      }

      if (a.get(i).ordinal() > pivot.ordinal()) {
        Collections.swap(a, larger, i);
        larger--;
      }
    }
  }
}
