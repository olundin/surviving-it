package survivingit.graph;

import java.util.*;

/**
 * Implementation of the A* algorithm.
 * Uses a generic node type, a weighed graph and supports different heurisitcs.
 *
 * @param <T> Node identifier
 *
 * @see Graph
 * @see AStarHeuristic
 * @see <a href="https://en.wikipedia.org/wiki/A*_search_algorithm">A* (Wikipedia</a>
 */
public class AStar<T> {

    private Graph<T> graph;
    private AStarHeuristic<T> heuristic;

    // G and H costs
    private Map<T, Double> gCosts = new HashMap<>();
    private Map<T, Double> hCosts = new HashMap<>();

    private Comparator<T> comparator = new Comparator<T>() {
        @Override
        public int compare(T n1, T n2) {
            return Double.compare(gCosts.get(n1) + hCosts.get(n1), gCosts.get(n2) + hCosts.get(n2));
        }
    };

    public AStar(Graph<T> graph, AStarHeuristic<T> heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    public Stack<T> findPath(T src, T dst) {
        // Clear H- and G costs from previous search
        gCosts.clear();
        hCosts.clear();

        // Nodes to be evaluated
        Queue<T> opened = new PriorityQueue<>(11, comparator);
        // Will eventually contain which node each evaluated node can most efficiently be reached from
        Map<T, T> cameFrom = new HashMap<>();

        // Starting node
        gCosts.put(src, Double.valueOf(0));
        hCosts.put(src, Double.valueOf(heuristic.calculate(dst,  src)));

        // Bein by evaluating src
        opened.add(src);

        while(!opened.isEmpty()) {
            // Get node with lowest f score (g + h)
            T current = opened.poll();

            // Check if destination reached
            if(heuristic.reached(current, dst)) {
                return reconstructPath(cameFrom, src, current);
            }

            // Check current node's neighbors
            for(T neighbor : this.graph.getNeighbors(current)) {
                // Calculate new g value based on current's and the weight of the edge between neighbor and current
                double newG = gCosts.get(current) + this.graph.getWeight(current, neighbor);

                if(!gCosts.containsKey(neighbor) || newG < gCosts.get(neighbor)) {
                    // Update g and h
                    gCosts.put(neighbor, Double.valueOf(newG));
                    hCosts.put(neighbor, Double.valueOf(heuristic.calculate(dst, neighbor)));

                    // Add new updated neighbor and update cameFrom
                    opened.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        // If this return is reached, no path was found
        return new Stack<>();
    }

    private Stack<T> reconstructPath(Map<T, T> cameFrom, T src, T dst) {
        Stack<T> path = new Stack<>();
        T current = dst;
        path.push(current);

        while(!current.equals(src)) {
            current = cameFrom.get(current);
            path.push(current);
        }

        return path;
    }

    public void setGraph(Graph<T> graph) {
        this.graph = graph;
    }

    public void setHeuristic(AStarHeuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

}
