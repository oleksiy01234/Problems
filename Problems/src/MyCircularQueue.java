class MyCircularQueue {
  int[] queue;
  int headIndex, tailIndex, length;

  public MyCircularQueue(int size) {
    queue = new int[size];
    headIndex = 0;
    tailIndex = -1;
    length = 0;
  }

  public boolean enQueue(int n) {
    if (isFull()) {
      return false;
    }

    tailIndex = (tailIndex + 1) % queue.length;
    queue[tailIndex] = n;
    length++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }
    
    headIndex = (headIndex + 1) % queue.length;
    length--;
    return true;
  }

  public int Front() {
    return isEmpty() ? -1 : queue[headIndex];
  }

  public int Rear() {
    return isEmpty() ? -1 : queue[tailIndex];
  }

  public boolean isEmpty() {
    return length == 0;
  }

  public boolean isFull() {
    return length == queue.length;
  }
}