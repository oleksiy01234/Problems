package LinkedList;

import DataStructures.Node.ListNode;

/**
 * Definition for singly-linked list.
 * public class DataStructures.ListNode {
 * int val;
 * DataStructures.ListNode next;
 * DataStructures.ListNode(int x) { val = x; }
 * }
 * <p>
 * 1. Find the end of the first half.
 * 2. Reverse the second half.
 * 3. Determine whether or not there is a palindrome.
 * 4. Restore the list.
 * 5. Return the result.
 * <p>
 * To do step 1, we could count the number of nodes, calculate how many nodes are in the first half, and then iterate back down the list to find the end of the first half. Or, we could do it in a single pass using the two runners pointer technique. Either is acceptable, however we'll have a look at the two runners pointer technique here.
 * <p>
 * Imagine we have 2 runners one fast and one slow, running down the nodes of the Linked List. In each second, the fast runner moves down 2 nodes, and the slow runner just 1 node. By the time the fast runner gets to the end of the list, the slow runner will be half way. By representing the runners as pointers, and moving them down the list at the corresponding speeds, we can use this trick to find the middle of the list, and then split the list into two halves.
 * <p>
 * If there is an odd-number of nodes, then the "middle" node should remain attached to the first half.
 * <p>
 * Step 3 is fairly straightforward. Remember that we have the first half, which might also contain a "middle" node at the end, and the second half, which is reversed. We can step down the lists simultaneously ensuring the node values are equal. When the node we're up to in the second list is null, we know we're done. If there was a middle value attached to the end of the first list, it is correctly ignored by the algorithm. The result should be saved, but not returned, as we still need to restore the list.
 * <p>
 * Step 4 requires using the same function you used for step 2, and then for step 5 the saved result should be returned.
 */
class LinkedListPalindrome {
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    // Find the end of first half and reverse second half.
    ListNode firstHalfEnd = endOfFirstHalf(head);

    // Reverse Linked List
    ListNode secondHalfStart = reverseList(firstHalfEnd.next);

    // Check whether or not there is a palindrome.
    ListNode p1 = head;
    ListNode p2 = secondHalfStart;
    boolean result = true;
    while (p2 != null) {
      if (p1.val != p2.val) {
        result = false;
        break;
      }
      p1 = p1.next;
      p2 = p2.next;
    }

    // Restore the list and return the result.
    firstHalfEnd.next = reverseList(secondHalfStart);
    return result;
  }

  // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextTemp;
    }
    return prev;
  }

  private ListNode endOfFirstHalf(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }
}