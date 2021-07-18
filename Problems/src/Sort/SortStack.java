package Sort;

import java.util.Stack;

public class SortStack {
  public static void stackSorting(Stack<Integer> stack) {
    Stack<Integer> helperStack = new Stack<>();

    while (!stack.isEmpty()) {
      int item = stack.pop();

      // put the item into correct position in the helper stack
      while (!helperStack.isEmpty() && helperStack.peek() > item) {
        stack.push(helperStack.pop());
      }
      helperStack.push(item);
    }

    // t is now in decreasing order. push it back to stack
    while (!helperStack.isEmpty()) {
      stack.push(helperStack.pop());
    }
  }

}
