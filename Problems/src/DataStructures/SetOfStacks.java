package DataStructures;

import java.util.ArrayList;

class SetOfStacks {
	ArrayList<Stack> stacks;
	int threshold;

	SetOfStacks(int threshold) {
		this.threshold = threshold;
		stacks = new ArrayList<>();
		stacks.add(new Stack());
	}

	void push(int data) {
		if (stacks.get(stacks.size() - 1).size >= threshold) {
			stacks.add(new Stack());
		}
		stacks.get(stacks.size() - 1).push(data);
	}

	int pop() {
		if (stacks.get(stacks.size() - 1).isEmpty()) {
			stacks.remove(stacks.size() - 1);
		}
		return stacks.get(stacks.size() - 1).pop();
	}

	int peek() {
		return stacks.get(stacks.size() - 1).peek();
	}
}