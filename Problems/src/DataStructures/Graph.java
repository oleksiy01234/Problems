package DataStructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
	class GraphNode {
		int data;
		LinkedList<GraphNode> adjacencyList;

		GraphNode(int id) {
			data = id;
			adjacencyList = new LinkedList<GraphNode>();
		}

		void addConnection(GraphNode g) {
			adjacencyList.add(g);
		}

		String getListString() {
			StringBuilder sb = new StringBuilder().append(data + ": ");

			if (!adjacencyList.isEmpty()) {
				sb.append("[");
				for (GraphNode g : adjacencyList) {
					sb.append(g.data + " ");
				}
				sb.replace(sb.length() - 1, sb.length(), "]");
			}

			return sb.toString();
		}
	}

	HashMap<Integer, GraphNode> nodeMap = new HashMap<>();

	boolean directed;

	Graph(boolean directed) {
		this.directed = directed;
	}

	GraphNode getNode(int id) {
		if (!nodeMap.containsKey(id)) {
			nodeMap.put(id, new GraphNode(id));
		}
		return nodeMap.get(id);
	}

	void print() {
		for (HashMap.Entry<Integer, GraphNode> gn : nodeMap.entrySet()) {
			System.out.println(gn.getValue().toString());
		}
	}

	void addEdge(int src, int dst) {
		GraphNode s = getNode(src);
		s.addConnection(getNode(dst));
		nodeMap.put(src, s);
		if (!directed) {
			addEdge(dst, src);
		}
	}

	boolean hasPathDfs(int s, int d) {
		return hasPathDfs(getNode(s), getNode(d), new HashSet<>());
	}

	private boolean hasPathDfs(GraphNode src, GraphNode dst, HashSet<Integer> visited) {
		if (visited.contains(src.data)) {
			return false;
		}

		visited.add(src.data);
		if (src == dst) {
			return true;
		}

		for (GraphNode child : src.adjacencyList) {
			if (hasPathDfs(child, dst, visited)) {
				return true;
			}
		}

		return false;
	}

	boolean hasPathBfs(int src, int dst) {
		return hasPathBfs(getNode(src), getNode(dst));
	}

	private boolean hasPathBfs(GraphNode src, GraphNode dst) {
		Queue<GraphNode> nextToVisit = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();

		nextToVisit.add(src);

		while (!nextToVisit.isEmpty()) {
			GraphNode node = nextToVisit.remove();
			if (node == dst) {
				return true;
			}

			if (!visited.contains(node.data)) {
				visited.add(node.data);
				for (GraphNode child : node.adjacencyList) {
					nextToVisit.add(child);
				}
			}
		}

		return false;
	}

	HashMap<Integer, Integer> findShortestPathBfs(int startId, int edgeDistance) {
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(nodeMap.get(startId));

		HashMap<Integer, Integer> distances = new HashMap<>();
		distances.put(startId, 0);

		while (!queue.isEmpty()) {
			GraphNode node = queue.poll();
			for (GraphNode neighbor : node.adjacencyList) {
				if (!distances.containsKey(neighbor.data)) {
					distances.put(neighbor.data, distances.get(node.data) + edgeDistance);
					queue.add(neighbor);
				}
			}
		}

		return distances;
	}
}