package Tree;

import DataStructures.Node.TreeNode;

import java.util.*;

/**
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/submissions/
 * <p>
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * <p>
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 */
public class SideViewBinaryTree {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new LinkedList<>();

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int levelSize = q.size();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = q.poll();

        if (node == null) {
          continue;
        }

        // right to left, first element in this level is the rightmost
        if (i == 0) {
          res.add(node.val);
        }

        if (node.right != null) {
          q.add(node.right);
        }

        if (node.left != null) {
          q.add(node.left);
        }

      }
    }
    return res;
  }

  public void dfs(TreeNode node, int level, List<Integer> res) {
    if (node == null) {
      return;
    }

    // first time we are at level of size, print it.
    // next time we will print the next lower node anywhere on the tree
    if (level == res.size()) {
      res.add(node.val);
    }

    dfs(node.right, level + 1, res);
    dfs(node.left, level + 1, res);
  }

  // dfs solution
  public List<Integer> rightSideView2(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(root, 0, res);
    return res;
  }

  // (my) bfs solution with extra map
  public List<Integer> rightSideView3(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    Map<TreeNode, Integer> orders = new HashMap<>();
    int currentOrder = 0;

    q.add(root);
    orders.put(root, 1);

    while (!q.isEmpty()) {
      TreeNode n = q.poll();

      if (n == null) {
        continue;
      }

      if (orders.get(n) != currentOrder) {
        currentOrder = orders.get(n);
        res.add(n.val);
      }

      q.add(n.right);
      q.add(n.left);

      orders.put(n.right, orders.get(n) + 1);
      orders.put(n.left, orders.get(n) + 1);
    }

    return res;
  }

}
