package DataStructures;

import DataStructures.Node.Node;
import java.util.Stack;

public class MinStack {
  Stack<Node> stack = new Stack<>();

  public MinStack() {}

  public void push(int val) {
    int min = stack.isEmpty() ? val : Math.min(stack.peek().min, val);
    stack.push(new Node(min, val));
  }

  public void pop() {
    stack.pop();
  }

  public int top() {
    return stack.peek().val;
  }

  public int getMin() {
    return stack.peek().min;
  }
}