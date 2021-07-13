package Tree;

import DataStructures.Node.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/submissions/
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Example:
 * You may serialize the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * as "[1,2,3,null,null,4,5]"
 * <p>
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */

public class SerializeTree {
  private final static String SEPARATOR = ",";

  // dfs preorder
  public String serialize(TreeNode n) {
    if (n == null) {
      return "null,";
    }

    return n.val + SEPARATOR + serialize(n.left) + serialize(n.right);
  }

  // dfs preorder deserialize
  public TreeNode deserialize(String s) {
    return deserialize(new LinkedList<>(Arrays.asList(s.split(SEPARATOR))));
  }

  private TreeNode deserialize(LinkedList<String> treeList) {
    String val = treeList.removeFirst();
    if (val.equals("null")) {
      return null;
    }

    TreeNode n = makeNode(val);
    n.left = deserialize(treeList);
    n.right = deserialize(treeList);
    return n;
  }

  // bfs inorder
  public String serialize2(TreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      if (n == null) {
        sb.append("null,");
      } else {
        sb.append(n.val).append(SEPARATOR);
        q.add(n.left);
        q.add(n.right);
      }
    }

    return sb.toString();
  }

  public TreeNode deserialize2(String s) {
    if (s.isEmpty()) {
      return null;
    }

    String[] vals = s.split(",");
    Queue<TreeNode> q = new LinkedList<>();
    int index = 0;

    TreeNode root = makeNode(vals[index]);
    q.add(root);

    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      if (n == null) {
        continue;
      }

      n.left = makeNode(vals[++index]);
      n.right = makeNode(vals[++index]);
      q.add(n.left);
      q.add(n.right);
    }

    return root;
  }

  TreeNode makeNode(String s) {
    return s.equals("null") ? null : new TreeNode(Integer.parseInt(s));
  }
}