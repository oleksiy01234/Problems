package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;

import Math.Primes;

class HashTable {
	String[] array;

	HashTable(int size) {
		reset(size);
	}

	void reset(int size) {
		array = new String[size];
		Arrays.fill(array, "-1");
	}

	void increaseSize(int min) {
		int primeSize = getNextPrime(min);
		moveOldArray(primeSize);
	}

	void moveOldArray(int size) {
		String[] newArray = removeEmptySpaces();
		reset(size);
		hashFunction(newArray);
	}

	String[] removeEmptySpaces() {
		ArrayList<String> strings = new ArrayList<>();
		for (String i : strings) {
			if (!i.equals("-1")) {
				strings.add(i);
			}
		}
		return strings.toArray(new String[strings.size()]);
	}

	void hashFunction(String[] strings) {
		int collisionCount = 0;
		for (String s : strings) {
			int index = Integer.parseInt(s) % array.length;
			System.out.println("Modulus index = " + index + " for value " + s);

			while (!array[index].equals("-1")) {
				index++;
				System.out.println("Collision count: " + ++collisionCount);
				index %= array.length;
			}

			array[index] = s;
		}
	}

	void doubleHash(String[] stringsForArray) {
		int collisionCount = 0;
		for (String s : stringsForArray) {
			int arrayIndex = Integer.parseInt(s) % array.length;
			int stepDistance = 7 - (Integer.parseInt(s) % 7);

			System.out.println("step distance: " + stepDistance);

			int count = 0;
			while (array[arrayIndex] != "-1" && count < array.length) {
				arrayIndex += stepDistance;
				arrayIndex %= array.length;
				System.out.println("Collision count: " + ++collisionCount);
				++count;
			}

			array[arrayIndex] = s;
		}
	}

	String findKey(String key) {
		int arrayIndexHash = Integer.parseInt(key) % array.length;

		int count = 0;
		while (array[arrayIndexHash] != "-1" && count < array.length) {
			if (array[arrayIndexHash] == key) {
				System.out.println(key + " was found in index " + arrayIndexHash);
				return array[arrayIndexHash];
			}

			arrayIndexHash++;
			arrayIndexHash %= array.length;
			count++;
		}

		System.out.println(key + " was not found");
		return null;
	}

	void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(i + ": " + array[i]);
		}
	}

	int getNextPrime(int min) {
		while (!Primes.isPrime(min)) {
			min++;
		}
		return min;
	}
}