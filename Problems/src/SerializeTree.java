import java.util.LinkedList;
import java.util.Queue;

public class SerializeTree {

  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode t = queue.poll();
      if (t == null) {
        sb.append(" ,");
      } else {
        sb.append(t.val + ",");
        queue.add(t.left);
        queue.add(t.right);
      }
    }

    return sb.toString().substring(0, sb.length() - 1);
  }

  public TreeNode deserialize(String s) {
    if (s.length() == 0 || s.charAt(0) == ' ') {
      return null;
    }

    String[] a = s.split(",");
    Queue<TreeNode> queue = new LinkedList<>();

    TreeNode root = new TreeNode(Integer.parseInt(a[0]));
    queue.add(root);

    int index = 1;
    while (!queue.isEmpty()) {
      TreeNode t = queue.poll();
      if (t == null) {
        continue;
      }

      String leftIndex = a[index], rightIndex = a[index + 1];
      t.left = getNode(leftIndex);
      t.right = getNode(rightIndex);
      queue.add(t.left);
      queue.add(t.right);

      index += 2;
    }

    return root;
  }

  TreeNode getNode(String s) {
    if (s.equals(" ")) {
      return null;
    }
    return new TreeNode(Integer.parseInt(s));
  }
  
}