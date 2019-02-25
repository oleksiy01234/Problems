package LinkedList;

import java.util.HashMap;
import java.util.Map;

class RandomLinkedListCopy {
  static class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }
  };

  static RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }

    RandomListNode n = head;
    while (n != null) {
      RandomListNode newNode = new RandomListNode(n.label);
      newNode.next = n.next;
      n.next = newNode;
      n = newNode.next;
    }

    n = head;
    while (n != null) {
      if (n.random != null) {
        n.next.random = n.random.next;
      }
      n = n.next.next;
    }

    RandomListNode oldN = head;
    RandomListNode newN = head.next;
    RandomListNode newHead = head.next;

    while (oldN != null) {
      oldN.next = oldN.next.next;
      newN.next = (newN.next != null) ? newN.next.next : null;
      oldN = oldN.next;
      newN = newN.next;
    }

    return newHead;
  }

  public RandomListNode copyRandomList2(RandomListNode head) {
    if (head == null) {
      return null;
    }

    Map<RandomListNode, RandomListNode> cloneMap = new HashMap<>();
    RandomListNode curr = head;
    while (curr != null) {
      cloneMap.put(curr, new RandomListNode(curr.label));
      curr = curr.next;
    }

    curr = head;
    while (curr != null) {
      cloneMap.get(curr).next = cloneMap.get(curr.next);
      cloneMap.get(curr).random = cloneMap.get(curr.random);
      curr = curr.next;
    }

    return cloneMap.get(head);
  }
}