package Graph;

class HamiltonianPath {
	int hamPath[];
	int[][] adjMatrix;

	void hamiltonianCycle(int adjMatrix[][]) {
		hamPath = new int[adjMatrix[0].length];
		this.adjMatrix = adjMatrix;

		hamPath[0] = 0;

		if (findFeasibleSolution(1)) {
			showHamiltonianPath();
		} else {
			System.out.println("No feasible solution found...");
		}
	}

	boolean findFeasibleSolution(int position) {

		if (position == hamPath.length) {
			return adjMatrix[hamPath[position - 1]][hamPath[0]] == 1;
		}

		for (int vertexIndex = 1; vertexIndex < hamPath.length; vertexIndex++) {
			if (isFeasible(vertexIndex, position)) {
				hamPath[position] = vertexIndex;

				if (findFeasibleSolution(position + 1)) {
					return true;
				}

				// backtrack
			}
		}

		return false;
	}

	boolean isFeasible(int vertex, int actualPosition) {
		// first criterion: whether the two nodes are connected?
		if (adjMatrix[hamPath[actualPosition - 1]][vertex] == 0) {
			return false;
		}

		// second criterion: whether we have already added this given node?
		for (int i = 0; i < actualPosition; i++) {
			if (hamPath[i] == vertex) {
				return false;
			}
		}

		return true;
	}

	public void showHamiltonianPath() {
		System.out.println("Hamiltonian cycle exists: ");

		for (int vertex : hamPath) {
			System.out.print(vertex + " ");
		}

		System.out.println(hamPath[0]);
	}
}
