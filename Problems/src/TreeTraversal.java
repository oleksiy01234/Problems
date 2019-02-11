import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class TreeTraversal {
  class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;
  }

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

  static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) {
      q.add(root);
    }

    while (!q.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      int size = q.size();

      for (int i = 0; i < size; i++) {
        TreeNode n = q.poll();
        level.add(n.val);

        if (n.left != null) {
          q.add(n.left);
        }

        if (n.right != null) {
          q.add(n.right);
        }
      }

      results.add(level);
    }

    return results;
  }

}