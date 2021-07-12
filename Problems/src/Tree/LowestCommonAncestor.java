package Tree;

import java.util.Stack;

import DataStructures.Node.TreeNode;

public class LowestCommonAncestor {
  static TreeNode lowestCommonAncestorNoStack(TreeNode root, TreeNode n1, TreeNode n2) {
    if (root == null || root == n1 || root == n2) {
      return root;
    }

    TreeNode left = lowestCommonAncestor(root.left, n1, n2);
    TreeNode right = lowestCommonAncestor(root.right, n1, n2);

    if (left == null) {
      return right;
    } else if (right == null) {
      return left;
    }

    return root;
  }

  static TreeNode lowestCommonAncestor(TreeNode tree, TreeNode n1, TreeNode n2) {
    if (n1.equals(n2)) {
      return n1;
    }

    Stack<TreeNode> pathToN1 = pathTo(tree, n1);
    Stack<TreeNode> pathToN2 = pathTo(tree, n2);
    if (pathToN1 == null || pathToN2 == null) {
      return null;
    }

    TreeNode prev = null;
    while (!pathToN1.isEmpty() && !pathToN2.isEmpty()) {
      TreeNode s = pathToN1.pop();
      TreeNode t = pathToN2.pop();
      if (!s.equals(t)) {
        break;
      }

      prev = s;
    }

    return prev;
  }

  static Stack<TreeNode> pathTo(TreeNode tree, TreeNode target) {
    if (tree == null) {
      return null;
    }

    Stack<TreeNode> stack = new Stack<>();
    if (tree.equals(target)) {
      stack.push(tree);
      return stack;
    }

    stack = pathTo(tree.left, target);
    if (stack != null) {
      stack.push(tree);
      return stack;
    }

    stack = pathTo(tree.right, target);
    if (stack != null) {
      stack.push(tree);
      return stack;
    }

    return null;
  }
}