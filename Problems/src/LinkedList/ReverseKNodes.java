package LinkedList;

import DataStructures.Node.ListNode;

public class ReverseKNodes {
  public ListNode reverseKGroup(ListNode head, int k) {
    int length = getLength(head);

    ListNode preHead = new ListNode();
    preHead.next = head;

    ListNode prev = preHead;
    ListNode tail = head;
    // while we have at least k elements left to reverse
    while (length >= k) {
      // reverse k nodes
      for (int i = 1; i < k; i++) {
        ListNode next = tail.next.next;
        tail.next.next = prev.next;
        prev.next = tail.next;
        tail.next = next;
      }

      prev = tail;
      tail = tail.next;
      length -= k;
    }

    return preHead.next;
  }

  private int getLength(ListNode n) {
    int count = 0;
    while (n != null) {
      count++;
      n = n.next;
    }

    return count;
  }
}
