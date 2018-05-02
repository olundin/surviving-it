package survivingit.graph;

import java.util.*;

/**
 * Class for our implementation of the A* algorithm.
 *
 * Uses a generic node type, a weighed graph and supports different heurisitcs.
 * The main usage of this class is the findPath(src, dst) method that finds the closest path between two T objects
 * using the A* algorithm.
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
        /**
         * Compares the two entered T objects and returns -1 if the first T object has the lower gCost + hCost,
         * 0 if the two T objects has the same gCost + hCost, or 1 if the first T object has the higher gCost + hCost.
         */
        @Override
        public int compare(T n1, T n2) {
            return Double.compare(gCosts.get(n1) + hCosts.get(n1), gCosts.get(n2) + hCosts.get(n2));
        }
    };

    /**
     * Creates a new AStar object with the entered graph and heuristic.
     * @param graph Graph of T objects that the AStar algorithm is to be used on.
     * @param heuristic AStarHeuristic for T objects that the AStar algorithm will use to calculate the heuristic
     *                  cost (hCost).
     */
    public AStar(Graph<T> graph, AStarHeuristic<T> heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    /**
     * Finds and returns the shortest path from the src T object to the dst T object on the AStar's graph.
     * @param src T object that is the start object in the path.
     * @param dst T object that is th destination object in the path.
     * @return Stack of T objects of the shortest path where the top objects of the Stack are the closest objects to the
     *         src T object.
     */
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

        // Begin by evaluating src
        opened.add(src);

        while (!opened.isEmpty()) {
            // Get node with lowest f score (g + h)
            T current = opened.poll();

            // Check if destination reached
            if (heuristic.reached(current, dst)) {
                return reconstructPath(cameFrom, src, current);
            }

            // Check current node's neighbors
            for (T neighbor : this.graph.getNeighbors(current)) {
                // Calculate new g value based on current's and the weight of the edge between neighbor and current
                double newG = gCosts.get(current) + this.graph.getWeight(current, neighbor);

                if (!gCosts.containsKey(neighbor) || newG < gCosts.get(neighbor)) {
                    // Update g and h
                    gCosts.put(neighbor, Double.valueOf(newG));
                    hCosts.put(neighbor, Double.valueOf(heuristic.calculate(dst, neighbor)));

                    // Add new updated neighbor and update cameFrom
                    opened.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        // If this statement is reached, no path was found, return an empty Stack.
        return new Stack<>();
    }

    // Reconstructs and returns a Stack of T objects that is the path from the src to the dst T objects using the cameFrom
    // map that is entered.
    private Stack<T> reconstructPath(Map<T, T> cameFrom, T src, T dst) {
        Stack<T> path = new Stack<>();
        T current = dst;
        path.push(current);

        while (!current.equals(src)) {
            current = cameFrom.get(current);
            path.push(current);
        }

        return path;
    }

    /**
     * Sets the graph of the AStar object to the entered graph.
     * @param graph Graph of T objects for the AStar to be run on.
     */
    public void setGraph(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * Sets the heuristic of the AStar object to the entered heuristic.
     * @param heuristic AStarHeuristic of T object to set the AStar heuristic to.
     */
    public void setHeuristic(AStarHeuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

}
