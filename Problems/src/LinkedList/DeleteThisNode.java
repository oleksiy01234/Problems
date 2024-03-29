package LinkedList;

import DataStructures.Node.ListNode;

/**
 * 237. Delete Node in a Linked List
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * <p>
 * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
 * <p>
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 */
public class DeleteThisNode {
  // both solutions assume node is in the list and it's not tail

  // o(1)
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  // o(n)
  public void deleteNode2(ListNode node) {
    while (node.next != null) {
      node.val = node.next.val;

      if (node.next.next == null) {
        node.next = null;
      } else {
        node = node.next;
      }
    }
  }

}
