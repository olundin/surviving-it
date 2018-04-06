package survivingit.graph;

import java.util.*;

/**
 * A weighed, directed graph representation.
 * Uses an adjacency list to keep track of which
 * nodes are connected and in which way.
 *
 * Nodes are kept track of. If a connection is made
 * using one or two nodes that equal an already existing
 * node, that node will be used instead.
 *
 * @param <N> Type of node to be used in graph
 *
 * @see Node
 * @see Edge
 */
public class Graph<N extends Node<?>> {

    private List<N> nodes; // Keep track of added nodes
    private Map<N, Set<Edge<N>>> adjacencyList;

    public Graph() {
        nodes = new ArrayList<>();
        adjacencyList = new HashMap<>();
    }

    public void addEdge(Edge<N> edge) {
        // Make sure pointers to exising nodes are used
        addNode(edge.getFrom());
        addNode(edge.getTo());
        N from = getExistingNode(edge.getFrom());
        N to = getExistingNode(edge.getTo());
        adjacencyList.putIfAbsent(from, new HashSet<>());
        adjacencyList.get(from).add(new Edge<>(from, to, edge.getWeight()));
    }

    public void addEdge(N from, N to, double weight) {
        this.addEdge(new Edge<>(from, to, weight));
    }

    public Set<N> getNeighbors(N node) {
        Set<N> neighbors = new HashSet<>();
        for(Edge<N> edge : adjacencyList.get(node)) {
            neighbors.add(edge.getTo());
        }
        return neighbors;
    }

    public double getWeight(N from, N to) {
        for(Edge<N> edge : adjacencyList.get(from)) {
            if(edge.getTo().equals(to))
                return edge.getWeight();
        }
        // No matching edge found
        return 0;
    }

    private void addNode(N node) {
        if(!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    private N getExistingNode(N node) {
        if(!nodes.contains(node)) return node;
        else {
            return nodes.get(nodes.indexOf(node));
        }
    }

    @Override
    public String toString() {
        //return "Graph{" + adjacencyList + '}';
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Graph:");
        for(N node : nodes) {
            sBuilder.append(System.getProperty("line.separator"));
            sBuilder.append(node);
            sBuilder.append(": ");
            sBuilder.append(this.getNeighbors(node));
        }
        return sBuilder.toString();
    }
}
