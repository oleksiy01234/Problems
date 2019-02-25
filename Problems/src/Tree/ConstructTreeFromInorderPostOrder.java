package Tree;

import Util.TreeNode;

public class ConstructTreeFromInorderPostOrder {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
  }

  public TreeNode buildTree(int[] in, int[] post, int lo, int hi, int postIndex) {
    if (lo > hi) {
      return null;
    }

    int i;
    for (i = lo; i <= hi; i++) {
      if (post[postIndex] == in[i]) {
        break;
      }
    }

    TreeNode n = new TreeNode(post[postIndex]);
    n.left = buildTree(in, post, lo, i - 1, postIndex - (hi - i + 1));
    n.right = buildTree(in, post, i + 1, hi, postIndex - 1);
    return n;
  }
}