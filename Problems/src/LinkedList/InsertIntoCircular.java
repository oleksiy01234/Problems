package LinkedList;

import DataStructures.Node.Node;

/**
 * 708. Insert into a Sorted Circular Linked List
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 * <p>
 * Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
 * <p>
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
 * <p>
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.
 * <p>
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
 */
public class InsertIntoCircular {
  public Node insert(Node head, int insertVal) {
    if (head == null) {
      head = new Node(insertVal);
      head.next = head;
      return head;
    }

    Node minNode = findMinNode(head);
    Node t = minNode;
    while (t.next != minNode) {
      if (insertVal == t.val || insertVal == t.next.val) {
        break;
      }

      if (insertVal > t.val && insertVal < t.next.val) {
        break;
      }

      t = t.next;
    }

    // either we found a spot, so insert after T
    // or this is new max, so insert after T and before minNode
    insertAfter(t, insertVal);
    return head;
  }

  private void insertAfter(Node n, int insertVal) {
    Node next = n.next;
    Node newNode = new Node(insertVal);
    n.next = newNode;
    newNode.next = next;
  }

  private Node findMinNode(Node head) {
    Node min = head;

    while (min.next != head) {
      if (min.next.val < min.val) {
        return min.next;
      }
      min = min.next;
    }

    // at this point, we are sure that min.next = head
    // so, we never found a case where min.next < min
    // so, it kept increasing, ie the last element is greatest and head is smallest
    return head;
  }
}
