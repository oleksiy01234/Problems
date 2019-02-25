import java.util.ArrayList;
import java.util.List;

class Recursion {

	// 5*4*3*2
	static ArrayList<String> permute(String s) {
		ArrayList<String> results = new ArrayList<>();
		permute("", s, results);
		return results;
	}

	private static void permute(String pref, String suff, ArrayList<String> list) {
		if (suff.isEmpty()) {
			list.add(pref);
			return;
		}

		for (int i = 0; i < suff.length(); i++) {
			permute(pref + suff.charAt(i), removeIndex(suff, i), list);
		}
	}

	private static String removeIndex(String s, int index) {
		return s.substring(0, index) + s.substring(index + 1, s.length());
	}

	static void combinationUtil(int arr[], int data[], int start, int end, int index, int r) {
		if (index == r) {
			for (int j = 0; j < r; j++) {
				System.out.print(data[j] + " ");
			}

			System.out.println("");
			return;
		}

		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arr[i];
			combinationUtil(arr, data, i + 1, end, index + 1, r);
		}
	}

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	static void printCombination(int a[], int r) {
		// A temporary array to store all combination one by one
		int data[] = new int[r];

		// Print all combination using temprary array 'data[]'
		combinationUtil(a, data, 0, a.length - 1, 0, r);
	}

	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		generateFactors(result, new ArrayList<>(), n, 2);
		return result;
	}

	public void generateFactors(List<List<Integer>> res, List<Integer> items, int n, int start) {
		if (n <= 1) {
			if (items.size() > 1) {
				res.add(new ArrayList<Integer>(items));
			}
			return;
		}

		for (int i = start; i <= n; i++) {
			if (n % i == 0) {
				items.add(i);
				generateFactors(res, items, n / i, i);
				items.remove(items.size() - 1);
			}
		}
	}
}