package survivingit.graph;

import java.util.*;

/**
 * Implementation of the A* algorithm.
 * Uses a generic node type, a weighed graph and supports different heurisitcs.
 *
 * @param <T> Node identifier
 *
 * @see Graph
 * @see Node
 * @see AStarHeuristic
 * @see <a href="https://en.wikipedia.org/wiki/A*_search_algorithm">A* (Wikipedia</a>
 */
public class AStar<T> {

    private Graph<AStarNode<T>> graph;
    private AStarHeuristic<T> heuristic;

    public AStar(Graph<AStarNode<T>> graph, AStarHeuristic<T> heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }


    public class AStarNodeComparator implements Comparator<AStarNode<T>> {
        public int compare(AStarNode<T> n1, AStarNode<T> n2) {
            return Double.compare(n1.getF(), n2.getF());
        }
    }

    public List<T> findPath(T src, T dst) {
        // Nodes to be evaluated
        Queue<AStarNode<T>> opened = new PriorityQueue<>(11, new AStarNodeComparator());

        // Will eventually contain which node each evaluated node can most efficiently be reached from
        Map<T, T> cameFrom = new HashMap<>();

        // Starting node
        AStarNode<T> start = new AStarNode<>(src);
        start.setG(0); // The cost of going from start to start is 0
        start.setH(this.heuristic.calculate(dst, start.getId()));
        // Begin by evaluating start node
        opened.add(start);

        while(!opened.isEmpty()) {
            // Get node with lowest f score (g + h)
            AStarNode<T> current = opened.poll();

            // Check if destination reached
            if(current.getId().equals(dst)) {
                return reconstructPath(cameFrom, src, dst);
            }

            // Check current node's neighbors
            for(AStarNode<T> neighbor : this.graph.getNeighbors(current)) {

                // Calculate new g value based on current's and the weight of the edge between neighbor and current
                double newG = current.getG() + this.graph.getWeight(current, neighbor);
                // Update old g if necessary
                if(newG < neighbor.getG()) {
                    // Update g and h
                    neighbor.setG(newG);
                    neighbor.setH(this.heuristic.calculate(dst, neighbor.getId()));

                    // Add new updated neighbor and update cameFrom
                    opened.add(neighbor);
                    cameFrom.put(neighbor.getId(), current.getId());
                }
            }
        }

        // If this return is reached, no path was found
        return new ArrayList<>();
    }

    private List<T> reconstructPath(Map<T, T> cameFrom, T src, T dst) {
        List<T> path = new ArrayList<>();
        T current = dst;
        path.add(current);

        while(!current.equals(src)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    public void setGraph(Graph<AStarNode<T>> graph) {
        this.graph = graph;
    }

    public void setHeuristic(AStarHeuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

}
