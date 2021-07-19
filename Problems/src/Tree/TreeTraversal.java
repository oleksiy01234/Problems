package Tree;

import DataStructures.Node.TreeNode;

import java.util.*;

class TreeTraversal {
  public List<Integer> preorderTraversal(TreeNode n) {
    List<Integer> list = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    while (n != null || !stack.isEmpty()) {
      if (n != null) {
        list.add(n.val);
        stack.push(n);
        n = n.left;
      } else {
        n = stack.pop().right;
      }
    }

    return list;
  }

  static List<Integer> postorderTraversal(TreeNode n) {
    LinkedList<Integer> result = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();

    while (!stack.isEmpty() || n != null) {
      if (n != null) {
        stack.push(n);
        result.addFirst(n.val); // Reverse the process of preorder
        n = n.right; // Reverse the process of preorder
      } else {
        n = stack.pop().left; // Reverse the process of preorder
      }
    }

    return result;
  }

  static List<Integer> inorderTraversal(TreeNode n) {
    List<Integer> list = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    while (n != null || !stack.isEmpty()) {
      if (n == null) {
        n = stack.pop();
        list.add(n.val);
        n = n.right;
      } else {
        stack.push(n);
        n = n.left;
      }
    }

    return list;
  }

  public List<List<Integer>> levelOrder(TreeNode root, boolean topToBottom) {
    List<List<Integer>> results = new LinkedList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    addToQueue(queue, root);

    while (!queue.isEmpty()) {
      List<Integer> thisLevel = new ArrayList<>();

      for (int size = queue.size(); size > 0; size--) {
        TreeNode n = queue.poll();
        thisLevel.add(n.val);
        addToQueue(queue, n.left);
        addToQueue(queue, n.right);
      }

      if (topToBottom) {
        results.add(thisLevel);
      } else {
        results.add(0, thisLevel);
      }
    }

    return results;
  }

  private void addToQueue(Queue<TreeNode> q, TreeNode n) {
    if (n != null) {
      q.add(n);
    }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> results = new LinkedList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    addToQueue(queue, root);
    boolean rightToLeft = false;

    while (!queue.isEmpty()) {
      List<Integer> thisLevel = new ArrayList<>();

      for (int size = queue.size(); size > 0; size--) {
        TreeNode n = queue.poll();

        if (rightToLeft) {
          thisLevel.add(0, n.val);
        } else {
          thisLevel.add(n.val);
        }
        addToQueue(queue, n.left);
        addToQueue(queue, n.right);
      }

      results.add(thisLevel);
      rightToLeft = !rightToLeft;
    }

    return results;

  }
}