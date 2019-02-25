package Tree;

import java.util.ArrayDeque;
import java.util.Deque;

import Util.TreeNode;

class DoesNodeExist {
  static final int ROOT_INDEX = 1;

  static boolean doesNodeExist(TreeNode root, int target) {
    if (root == null) {
      return false;
    }

    Deque<Integer> path = getPathToRoot(target);
    return verifyPath(root, path);
  }

  static boolean verifyPath(TreeNode node, Deque<Integer> path) {
    int parentIndex = ROOT_INDEX;

    while (!path.isEmpty()) {
      int leftChild = getLeftChildIndex(parentIndex);
      int rightChild = getRightChildIndex(parentIndex);

      int index = path.pop();

      if (index == leftChild) {
        node = node.left;
        parentIndex = leftChild;
      } else if (index == rightChild) {
        node = node.right;
        parentIndex = rightChild;
      }

      if (node == null) {
        return false;
      }
    }

    return true;
  }

  static Deque<Integer> getPathToRoot(int child) {
    Deque<Integer> stack = new ArrayDeque<>();

    while (child != ROOT_INDEX) {
      stack.push(child);
      child = getParentIndex(child);
    }

    return stack;
  }

  static int getParentIndex(int childIndex) {
    return childIndex / 2;
  }

  static int getLeftChildIndex(int parentIndex) {
    return parentIndex * 2;
  }

  static int getRightChildIndex(int parentIndex) {
    return parentIndex * 2 + 1;
  }

}