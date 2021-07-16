package Sort;

import DataStructures.Node.ListNode;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->
 */
public class MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);

    for (ListNode l : lists) {
      if (l != null) {
        pq.add(l);
      }
    }

    ListNode preHead = new ListNode();
    ListNode n = preHead;

    while (!pq.isEmpty()) {
      ListNode next = pq.poll();

      n.next = new ListNode(next.val);
      n = n.next;

      if (next.next != null) {
        pq.add(next.next);
      }
    }

    return preHead.next;
  }
}
