import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromPreorderInorder {
  private int index = 0;

  public TreeNode buildTree(int preorder[], int inorder[]) {
    Map<Integer, Integer> inorderIndices = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderIndices.put(inorder[i], i);
    }

    TreeNode result = createTree(inorderIndices, preorder, 0, inorder.length - 1);
    index = 0;
    return result;
  }

  TreeNode createTree(Map<Integer, Integer> inorder, int pre[], int lo, int hi) {
    if (lo > hi) {
      return null;
    }

    int i = inorder.get(pre[index]);
    TreeNode n = new TreeNode(pre[index]);
    index++;
    n.left = createTree(inorder, pre, lo, i - 1);
    n.right = createTree(inorder, pre, i + 1, hi);
    return n;
  }
}