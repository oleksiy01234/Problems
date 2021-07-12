package Tree;

import DataStructures.Node.TreeNode;

/**
 * 1448. Count Good Nodes in Binary Tree
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * <p>
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * <p>
 * Return the number of good nodes in the binary tree.
 * <p>
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * <p>
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 */
public class GoodNodesInBinaryTree {

  public int goodNodes(TreeNode root) {
    return goodNodes(root, root.val);
  }

  private int goodNodes(TreeNode n, int max) {
    if (n == null) {
      return 0;
    }

    int goodCount = 0;

    if (n.val >= max) {
      goodCount = 1;
      max = n.val;
    }

    return goodCount + goodNodes(n.left, max) + goodNodes(n.right, max);
  }
}
