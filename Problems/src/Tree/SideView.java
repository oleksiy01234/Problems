package Tree;

import java.util.ArrayList;
import java.util.List;

import Util.TreeNode;

/**
 * SideView
 */
public class SideView {

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    rightView(root, result, 0);
    return result;
  }

  public void rightView(TreeNode curr, List<Integer> result, int depth) {
    if (curr == null) {
      return;
    }

    if (depth == result.size()) {
      result.add(curr.val);
    }

    rightView(curr.right, result, depth + 1);
    rightView(curr.left, result, depth + 1);
  }
}