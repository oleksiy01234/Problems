package Tree;

import DataStructures.Node.TreeNode;

/**
 * 572. Subtree of Another Tree
 * https://leetcode.com/problems/subtree-of-another-tree/
 * <p>
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure
 * and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all
 * of this node's descendants. The tree s could also be considered as a subtree of itself.
 */
public class IsSubtree {
  // approach 1: either t == s, or t is a subtree of s.left or s.right
  // O(st) time, O(s) space
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (equal(s, t)) {
      return true;
    }

    return s != null && (isSubtree(s.left, t) || isSubtree(s.right, t));
  }

  boolean equal(TreeNode s, TreeNode t) {
    if (s == null || t == null) {
      return t == s; // true if both are null, false if only one is null
    }

    // not null, so can check values and children
    return s.val == t.val && equal(s.left, t.left) && equal(s.right, t.right);
  }

  // approach 2: compare preorder traversals. O(s + t + st) time, O(s + t) space
  public boolean isSubtree2(TreeNode s, TreeNode t) {
    return preOrder(s).contains(preOrder(t));
  }

  // nulls must be acknowledged to make sure t's and s's leaves are the same
  // append # because s:[23, 4, 5] and t:[3, 4, 5]
  String preOrder(TreeNode n) {
    if (n == null) {
      return "null";
    }
    return "#" + n.val + "," + preOrder(n.left) + "," + preOrder(n.right);
  }
}