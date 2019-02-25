package Tree;

import Util.TreeNode;

public class DeleteBstNode {
  /**
   * Recursive solution
   */
  static TreeNode deleteNodeRec(TreeNode root, int data) {
    if (root == null) {
      return root;
    }

    if (data < root.val) {
      root.left = deleteNode(root.left, data);
    } else if (data > root.val) {
      root.right = deleteNode(root.right, data);
    } else {
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }

      root.val = findLeftMost(root.right).val;
      root.right = deleteNode(root.right, root.val);
    }

    return root;
  }

  /**
   * Helper function
   */
  static TreeNode findLeftMost(TreeNode root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  /**
   * Iterative solution
   */
  static TreeNode deleteNode(TreeNode root, int key) {
    TreeNode target = root;
    TreeNode parentOfTarget = null;
    while (target != null && target.val != key) {
      parentOfTarget = target;
      target = key < target.val ? target.left : target.right;
    }

    if (parentOfTarget == null) {
      return deleteRootNode(target);
    }

    if (parentOfTarget.left == target) {
      parentOfTarget.left = deleteRootNode(target);
    } else {
      parentOfTarget.right = deleteRootNode(target);
    }
    return root;
  }

  static TreeNode deleteRootNode(TreeNode root) {
    if (root == null) {
      return null;
    } else if (root.left == null) {
      return root.right;
    } else if (root.right == null) {
      return root.left;
    }

    TreeNode next = findLeftMost(root.right);
    next.left = root.left;
    return root.right;
  }

}