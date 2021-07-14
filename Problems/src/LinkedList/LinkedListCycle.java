package LinkedList;

import DataStructures.Node.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
  public boolean hasCycle(ListNode head) {
    ListNode n1 = head, n2 = head;

    while (n1 != null && n2 != null && n2.next != null) {
      n1 = n1.next;
      n2 = n2.next.next;

      if (n1 == n2) {
        return true;
      }
    }

    return false;
  }

  // O(n) using set
  public boolean hasCycle2(ListNode head) {
    Set<ListNode> nodesSeen = new HashSet<>();
    while (head != null) {
      if (nodesSeen.contains(head)) {
        return true;
      }
      nodesSeen.add(head);
      head = head.next;
    }
    return false;
  }
}
