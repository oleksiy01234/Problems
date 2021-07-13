package Tree;

import DataStructures.Node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 545. Boundary of Binary Tree
 * https://leetcode.com/problems/boundary-of-binary-tree/
 * <p>
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.
 * <p>
 * The left boundary is the set of nodes defined by the following:
 * <p>
 * The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 * If a node in the left boundary and has a left child, then the left child is in the left boundary.
 * If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 * The leftmost leaf is not in the left boundary.
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 * <p>
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 * <p>
 * Given the root of a binary tree, return the values of its boundary.
 * <p>
 * Example 1:
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * <p>
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 * 4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 * <p>
 * Example 2:
 * Input: root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
 * Output: [1,2,4,7,8,9,10,6,3]
 * <p>
 * Explanation:
 * - The left boundary follows the path starting from the root's left child 2 -> 4.
 * 4 is a leaf, so the left boundary is [2].
 * - The right boundary follows the path starting from the root's right child 3 -> 6 -> 10.
 * 10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
 * - The leaves from left to right are [4,7,8,9,10].
 * Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryBinaryTree {
  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    res.add(root.val);

    if (root.left == null && root.right == null) {
      return res;
    }

    if (root.left != null) {
      res.addAll(getLeftSide(root.left));
    }

    List<Integer> leaves = new ArrayList<>();
    getLeaves(root, leaves);
    res.addAll(leaves);

    if (root.right != null) {
      res.addAll(getRightSideReversed(root.right));
    }

    return res;
  }

  private List<Integer> getLeftSide(TreeNode n) {
    List<Integer> res = new ArrayList<>();

    while (n != null) {
      if (n.left != null || n.right != null) {
        res.add(n.val);
      }

      if (n.left != null) {
        n = n.left;
      } else {
        n = n.right;
      }
    }

    return res;
  }

  private void getLeaves(TreeNode n, List<Integer> leaves) {
    if (n == null) {
      return;
    }

    if (n.left == null && n.right == null) {
      leaves.add(n.val);
      return;
    }

    getLeaves(n.left, leaves);
    getLeaves(n.right, leaves);
  }

  private List<Integer> getRightSideReversed(TreeNode n) {
    List<Integer> res = new ArrayList<>();

    while (n != null) {
      if (n.left != null || n.right != null) {
        res.add(0, n.val);
      }

      if (n.right != null) {
        n = n.right;
      } else {
        n = n.left;
      }
    }

    return res;
  }
}
