import Util.Util;

class GraphTest {

	static void test() {
		System.out.println(roadsAndLibraries(5, 6, 1, new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 } }));
	}

	// n: number of nodes; c_lib: cost per node; c_road: cost per edge; cities: each
	// [i] is an edge: it has 2 ints representing nodes connected return minimum
	// cost of connecting all nodes
	static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
		if (c_road >= c_lib) {
			return n * c_lib;
		}

		long min = n * c_road;
		boolean[][] adjList = new boolean[n + 1][n + 1];
		boolean[] visited = new boolean[n + 1];

		for (int[] edge : cities) {
			adjList[edge[0]][edge[1]] = true;
			adjList[edge[1]][edge[0]] = true;
		}

		for (int i = 1; i < visited.length; i++) {
			if (!visited[i]) {
				dfs(adjList, visited, i);
				min += (c_lib - c_road);
			}
		}

		return min;
	}

	private static void dfs(boolean[][] adjList, boolean[] visited, int node) {
		visited[node] = true;

		for (int con = 0; con < adjList[node].length; con++) {
			if (adjList[node][con] && !visited[con]) {
				dfs(adjList, visited, con);
			}
		}
	}

	static void findBiggestRegion() {
		// matrix filled with 1 and 0
		int[][] matrix = Util.constructArray2D(5, 2);
		int maxRegion = 0;

		// can keep track via a boolean[][]
		// but we can destroy/clone the matrix, and just update its visited cells to 0

		// go thru the matrix, see 1 = do a dps and update the max region
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (matrix[row][col] == 1) {
					int size = getRegionSize(matrix, row, col);
					maxRegion = Math.max(size, maxRegion);
				}
			}
		}

		System.out.println("Biggest region with 1s has " + maxRegion + " cells");
	}

	private static int getRegionSize(int[][] matrix, int row, int col) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[row].length || matrix[row][col] == 0) {
			return 0;
		}

		// set it to 0 so we can keep track of visited cells
		matrix[row][col] = 0;
		int size = 1;

		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {
				if (r != row || c != col) {
					size += getRegionSize(matrix, r, c);
				}
			}
		}

		return size;
	}
}