package LinkedList;

import java.util.Stack;

/**
 * AddTwoNumbers
 */
public class AddTwoNumbers {

  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    fillStack(l1, s1);
    fillStack(l2, s2);

    ListNode newHead = null;

    boolean carry = false;
    while (!s1.isEmpty() || !s2.isEmpty() || carry) {
      int sum = carry ? 1 : 0;

      if (s1.isEmpty()) {
        sum += s2.pop();
      } else if (s2.isEmpty()) {
        sum += s1.pop();
      } else {
        sum += s1.pop() + s2.pop();
      }

      ListNode n = new ListNode(sum % 10);
      n.next = newHead;
      newHead = n;
      carry = sum > 9;
    }

    return newHead;
  }

  void fillStack(ListNode l, Stack<Integer> s) {
    while (l != null) {
      s.add(l.val);
      l = l.next;
    }
  }
}