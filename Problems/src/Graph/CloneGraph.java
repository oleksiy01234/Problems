package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

  class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<UndirectedGraphNode>();
    }
  };

  public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode start) {
    if (start == null) {
      return null;
    }

    Map<UndirectedGraphNode, UndirectedGraphNode> vertexMap = new HashMap<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();

    queue.add(start);
    vertexMap.put(start, new UndirectedGraphNode(start.label));

    while (!queue.isEmpty()) {
      UndirectedGraphNode currVertex = queue.poll();

      for (UndirectedGraphNode neighbor : currVertex.neighbors) {
        if (!vertexMap.containsKey(neighbor)) {
          vertexMap.put(neighbor, new UndirectedGraphNode(neighbor.label));
          queue.add(neighbor);
        }

        vertexMap.get(currVertex).neighbors.add(vertexMap.get(neighbor));
      }
    }

    return vertexMap.get(start);
  }

  public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
    return clone(node, new HashMap<>());
  }

  private UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
    if (node == null) {
      return null;
    }

    if (map.containsKey(node.label)) {
      return map.get(node.label);
    }

    UndirectedGraphNode nodeClone = new UndirectedGraphNode(node.label);
    map.put(nodeClone.label, nodeClone);
    
    for (UndirectedGraphNode neighbor : node.neighbors) {
      UndirectedGraphNode neighborClone = clone(neighbor, map);
      nodeClone.neighbors.add(neighborClone);
    }

    return nodeClone;
  }
}