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

    // step 1: interleave
    RandomListNode n = head;
    while (n != null) {
      RandomListNode newNode = new RandomListNode(n.label);
      newNode.next = n.next;
      n.next = newNode;
      n = newNode.next;
    }

    // step 2: assign random pointers of new nodes
    n = head;
    while (n != null) {
      if (n.random != null) {
        n.next.random = n.random.next;
      }
      n = n.next.next;
    }

    // step 3: un-weave and reconnect
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
}