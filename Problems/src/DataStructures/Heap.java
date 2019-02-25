package DataStructures;

import java.util.Arrays;

import Util.Util;

public class Heap {
	protected int size = 0;
	protected int[] items = new int[8];
	protected boolean minHeap;

	public Heap(boolean min) {
		minHeap = min;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	public int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	public int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	public int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	public boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	public boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	public boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	public int getLeftChild(int index) {
		return items[getLeftChildIndex(index)];
	}

	public int getRightChild(int index) {
		return items[getRightChildIndex(index)];
	}

	public int getParent(int index) {
		return items[getParentIndex(index)];
	}

	public void ensureCapacity() {
		if (size == items.length) {
			items = Arrays.copyOf(items, items.length * 2);
		}
	}

	public int peek() {
		if (size == 0) {
			System.out.println("Heap is empty");
			throw new IllegalStateException();
		}
		return items[0];
	}

	public int getSmallerChildIndex(int index) {
		if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
			return getRightChild(index);
		} else {
			return getLeftChild(index);
		}
	}

	public int getLargerChildIndex(int index) {
		if (hasRightChild(index) && getRightChild(index) > getLeftChild(index)) {
			return getRightChild(index);
		} else {
			return getLeftChild(index);
		}
	}

	// remove top, replace with lowest, bubble down
	public int poll() {
		if (size == 0) {
			System.out.println("Heap is empty");
			throw new IllegalStateException();
		}

		int item = items[0];
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return item;
	}

	// insert low, bubble up
	public void add(int item) {
		ensureCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}

	public void heapifyUp() {
		int index = size - 1;
		int parentIndex;
		while (shouldSwapWithParent(index)) {
			parentIndex = getParentIndex(index);
			Util.swap(items, index, parentIndex);
			index = parentIndex;
		}
	}

	boolean shouldSwapWithParent(int index) {
		return hasParent(index) && minHeap ? getParent(index) > items[index] : getParent(index) < items[index];
	}

	boolean shouldSwapWithChild(int index, int childIndex) {
		return minHeap ? items[index] > items[childIndex] : items[index] < items[childIndex];
	}

	void heapifyDown() {
		int index = 0;
		while (hasLeftChild(index)) {
			int childIndex = minHeap ? getSmallerChildIndex(index) : getLargerChildIndex(index);
			if (shouldSwapWithChild(index, childIndex)) {
				Util.swap(items, index, childIndex);
				index = childIndex;
			} else {
				return;
			}
		}
	}
}