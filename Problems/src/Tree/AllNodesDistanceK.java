package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import DataStructures.Node.TreeNode;

/**
 * 863. All Nodes Distance K in Binary Tree
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceK {

  static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> nodesAndParents = new HashMap<>();
    fillGraphDfs(nodesAndParents, root, null);

    Queue<TreeNode> q = new LinkedList<>();
    Set<TreeNode> visited = new HashSet<>();
    q.add(target);

    while (!q.isEmpty()) {
      if (k == 0) {
        break;
      }

      k--;
      Queue<TreeNode> temp = new LinkedList<>(q);
      visited.addAll(q);
      q.clear();

      for (TreeNode n : temp) {
        addToQ(n.left, q, visited);
        addToQ(n.right, q, visited);
        addToQ(nodesAndParents.get(n), q, visited);
      }
    }

    List<Integer> results = new LinkedList<>();
    for (TreeNode n : q) {
      results.add(n.val);
    }

    return results;
  }

  static void addToQ(TreeNode n, Queue<TreeNode> q, Set<TreeNode> visited) {
    if (n != null && !visited.contains(n)) {
      q.add(n);
    }
  }

  static void fillGraphDfs(Map<TreeNode, TreeNode> graph, TreeNode root, TreeNode parent) {
    if (root == null) {
      return;
    }

    graph.put(root, parent);
    fillGraphDfs(graph, root.left, root);
    fillGraphDfs(graph, root.right, root);
  }

}