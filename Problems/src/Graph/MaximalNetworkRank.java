package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1615. Maximal Network Rank
 * https://leetcode.com/problems/maximal-network-rank/
 * <p>
 * There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 * <p>
 * The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.
 * <p>
 * The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
 * <p>
 * Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * Output: 4
 * Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * Output: 5
 * Explanation: There are 5 roads that are connected to cities 1 or 2.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * Output: 5
 * Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi <= n-1
 * ai != bi
 * Each pair of cities has at most one road connecting them.
 */
public class MaximalNetworkRank {
  public int maximalNetworkRank(int n, int[][] roads) {
    List<Set<Integer>> adjList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adjList.add(new HashSet<>());
    }

    for (int[] road : roads) {
      adjList.get(road[0]).add(road[1]);
      adjList.get(road[1]).add(road[0]);
    }

    int maxRank = 0;

    for (int i = 0; i < adjList.size(); i++) {
      Set<Integer> iSet = adjList.get(i);
      for (int k = i + 1; k < adjList.size(); k++) {
        Set<Integer> kSet = adjList.get(k);

        int rank = kSet.size() + iSet.size();
        if (kSet.contains(i)) {
          rank--;
        }

        maxRank = Math.max(maxRank, rank);
      }
    }

    return maxRank;
  }
}
