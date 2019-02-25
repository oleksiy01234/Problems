import Util.Util;

class Knapsack {
	static class Item {
		int weight;
		int value;

		Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public String toString() {
			return "[" + weight + "kg, $" + value + "]";
		}
	}

	static void test() {
		Item[] items = makeItems();
		for (Item i : items) {
			System.out.println(i);
		}

		System.out.println(solve(items, 6));
	}

	static Item[] makeItemsDebug() {
		Item[] items = new Item[] { new Item(3, 12), new Item(1, 6), new Item(2, 10), new Item(2, 13) };

		return items;
	}

	static Item[] makeItems() {
		int size = 10;
		Item[] items = new Item[size];

		for (int i = 0; i < size; i++) {
			items[i] = new Item(Util.constructInt(10), Util.constructInt(10));
		}

		return items;
	}

	static String solve(Item[] items, int limit) {
		int[][] m = new int[items.length + 1][limit + 1];

		for (int i = 1; i < m.length; i++) {
			for (int k = 1; k < m[i].length; k++) {
				int itemWeight = items[i - 1].weight;
				int itemValue = items[i - 1].value;
				int excl = m[i - 1][k];

				if (itemWeight > k) {
					m[i][k] = excl;
				} else {
					int incl = itemValue + m[i - 1][k - itemWeight];
					m[i][k] = Math.max(excl, incl);
				}
			}
		}

		int lastItemRow = m.length - 1;
		int lastItemCol = m[m.length - 1].length - 1;
		int maxValue = m[lastItemRow][lastItemCol];

		StringBuilder sb = new StringBuilder();
		sb.append("Maximum value under " + limit + " kg is: " + maxValue + ". Items included: ");

		while (lastItemRow > 0 && lastItemCol > 0) {
			if (m[lastItemRow][lastItemCol] != m[lastItemRow - 1][lastItemCol]) {
				sb.append("\n[" + items[lastItemRow - 1] + "]");
				lastItemCol -= items[lastItemRow - 1].weight;
			}
			lastItemRow--;
		}

		// Util.print(m);

		return sb.toString();
	}

}