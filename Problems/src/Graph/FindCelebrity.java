package Graph;

public class FindCelebrity {
  // https://leetcode.com/problems/find-the-celebrity/submissions/

  private boolean knows(int i, int k) {
    return true; // this would return result from the graph
  }

  // the efficient solution
  public int findCelebrity(int n) {

    int potentialCelebrity = 0;
    for (int i = 0; i < n; i++) {
      if (knows(potentialCelebrity, i)) {
        potentialCelebrity = i;
      }
    }

    if (isCelebrity(potentialCelebrity, n)) {
      return potentialCelebrity;
    }
    return -1;
  }

  // naive n^2 approach
  public int findCelebrity3(int n) {
    for (int i = 0; i < n; i++) {
      if (isCelebrity(i, n)) {
        return i;
      }
    }

    return -1;
  }

  private boolean isCelebrity(int i, int n) {
    for (int k = 0; k < n; k++) {
      if (i == k) {
        continue;
      }

      if (knows(i, k) || !knows(k, i)) {
        return false;
      }
    }
    return true;
  }

  // Another naive approach
  public int findCelebrity2(int n) {
    int[] edgesFrom = new int[n];
    int[] edgesTo = new int[n];

    for (int i = 0; i < n; i++) {
      for (int k = i + 1; k < n; k++) {
        if (knows(i, k)) {
          edgesFrom[i]++;
          edgesTo[k]++;
        }

        if (knows(k, i)) {
          edgesFrom[k]++;
          edgesTo[i]++;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (edgesFrom[i] == 0 && edgesTo[i] == n - 1) {
        return i;
      }
    }

    return -1;
  }

}
