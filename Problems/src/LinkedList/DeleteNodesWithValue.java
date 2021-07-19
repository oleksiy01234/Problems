package LinkedList;

import DataStructures.Node.ListNode;

/**
 * 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 * <p>
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * <p>
 * Output: [1,2,3,4,5]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [], val = 1
 * <p>
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: head = [7,7,7,7], val = 7
 * <p>
 * Output: []
 */
public class DeleteNodesWithValue {
  public ListNode removeElements(ListNode head, int val) {
    ListNode n = head;
    ListNode preN = head;

    while (n != null) {
      if (n.val == val) {
        if (n == head) {
          head = head.next;
        } else {
          preN.next = n.next;
        }
      } else {
        preN = n;
      }
      n = n.next;
    }

    return head;
  }
}
