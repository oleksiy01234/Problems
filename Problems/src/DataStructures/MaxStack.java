package DataStructures;

import DataStructures.Node.Node;

import java.util.Stack;

public class MaxStack {
  Stack<Node> stack = new Stack<>();

  public void push(int x) {
    int maxSoFar = stack.isEmpty() ? x : Math.max(x, stack.peek().max);
    stack.push(new Node(x, maxSoFar));
  }

  public int pop() {
    return stack.pop().val;
  }

  public int top() {
    return stack.peek().val;
  }

  public int peekMax() {
    return stack.peek().max;
  }

  public int popMax() {
    Stack<Node> removed = new Stack<>();

    while (!stack.isEmpty()) {
      if (peekMax() == top()) {
        int maxValue = pop();

        while (!removed.isEmpty()) {
          push(removed.pop().val);
        }

        return maxValue;
      }

      removed.push(stack.pop());
    }

    return -1;
  }
}
