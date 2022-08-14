package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;
	
	public Graph() {
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}
	
	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) throws IllegalArgumentException {
		if (!dataMap.containsKey(startVertexName) || !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		} else {
			adjacencyMap.get(startVertexName).put(endVertexName, cost);
		}
	}
	
	public void addVertex(String vertexName, E data) throws IllegalArgumentException {
		if (dataMap.containsKey(vertexName)) {
			throw new IllegalArgumentException();
		} else {
			dataMap.put(vertexName, data);
			adjacencyMap.put(vertexName, new HashMap<String, Integer>());
		}
	}
	
	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) throws IllegalArgumentException {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		
		Set<String> visited = new HashSet<>();
		Stack<String> discovered = new Stack<>();
		
		discovered.push(startVertexName);
		
		while (!discovered.isEmpty()) {
			String x = discovered.pop();
			if (!visited.contains(x)) {
				visited.add(x);
				callback.processVertex(x, dataMap.get(x));
				
				TreeSet<String> adjacencies = new TreeSet<String>();
				adjacencies.addAll(adjacencyMap.get(x).keySet());
				
				for (String adjacent : adjacencies) {
					if (!visited.contains(adjacent)) {
						discovered.push(adjacent);
					}
				}
			}
		}
	}
	
	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback) throws IllegalArgumentException {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		
		Set<String> visited = new HashSet<>();
		Queue<String> discovered = new LinkedList<>();
		
		discovered.add(startVertexName);
		
		while (!discovered.isEmpty()) {
			String x = discovered.poll();
			if (!visited.contains(x)) {
				visited.add(x);
				callback.processVertex(x, dataMap.get(x));
				
				TreeSet<String> adjacencies = new TreeSet<String>();
				adjacencies.addAll(adjacencyMap.get(x).keySet());
				
				for (String adjacent : adjacencies) {
					if (!visited.contains(adjacent)) {
						discovered.add(adjacent);
					}
				}
			}
		}
	}
	
	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath) throws IllegalArgumentException {
		if (!dataMap.containsKey(startVertexName) || !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		}
		
		if (startVertexName.equals(endVertexName)) {
			shortestPath.add(startVertexName);
			return 0;
		}
		
		Set<String> s = new HashSet<>();
		HashMap<String, String> p = new HashMap<>();
		HashMap<String, Integer> c = new HashMap<>();
		shortestPath.clear();
		
		for (String v : dataMap.keySet()) {
			c.put(v, Integer.MAX_VALUE);
		}
		
		c.put(startVertexName, 0);
		
		for (String key : dataMap.keySet()) {
			p.put(key, "None");
		}
		
		p.put(startVertexName, startVertexName);
		
		String vertex = startVertexName;
		
		while (true) {
			if (!s.add(vertex)) {
				break;
			}
			
			String k = "";
			int minCost = Integer.MAX_VALUE;
			for (String adjacentVertex : getAdjacentVertices(vertex).keySet()) {
				int costOfK = getCost(vertex, adjacentVertex);
				if (costOfK < minCost && !s.contains(adjacentVertex)) {
					minCost = costOfK;
					k = adjacentVertex;
				}
			}
			
			s.add(k);
			p.put(k, vertex);
			c.put(k, minCost);
			
			for (String j : getAdjacentVertices(k).keySet()) {
				if (!s.contains(j)) {
					if (c.get(k) + getCost(k, j) < c.get(j)) {
						c.put(j, c.get(k) + getCost(k, j));
						p.put(j, k);
						vertex = j;
					}
				} 
			}
			
			if (s.contains(vertex)) {
				break;
			}
		}
		
		if (c.get(endVertexName) == Integer.MAX_VALUE) {
			c.put(endVertexName, -1);
		}
		
		String predecessor = endVertexName;
		
		while (predecessor != startVertexName) {
			if (predecessor.equals("None")) {
				shortestPath.clear();
				shortestPath.add("None");
				break;
			}
			
			shortestPath.add(predecessor);
			predecessor = p.get(predecessor);
		}
		
		if (!shortestPath.contains("None")) {
			shortestPath.add(startVertexName);
			Collections.reverse(shortestPath);
		}
	
		return c.get(endVertexName);
	}
	
	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}
	
	public int getCost(String startVertexName, String endVertexName) throws IllegalArgumentException {
		if (!dataMap.containsKey(startVertexName) || !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		} else {
			if (startVertexName.equals(endVertexName)) {
				return 0;
			}
			
			return adjacencyMap.get(startVertexName).get(endVertexName);
		}
	}
	
	public E getData(String vertex) throws IllegalArgumentException {
		if (!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException();
		} else {
			return dataMap.get(vertex);
		}
	}
	
	public Set<String> getVertices() {
		return dataMap.keySet();
	}
	
	public String toString() {
		TreeMap<String, E> vertices = new TreeMap<>();
		vertices.putAll(dataMap);
		
		TreeMap<String, HashMap<String, Integer>> adjacent = new TreeMap<>();
		adjacent.putAll(adjacencyMap);
		
		String result = "Vertices: " + vertices.keySet().toString();
		
		result += "\nEdges:";
		
		for (String vertex : adjacent.keySet()) {
			result += "\n" + "Vertex(" + vertex + ")--->" + adjacent.get(vertex).toString();
		}
		
		return result;
	}
}