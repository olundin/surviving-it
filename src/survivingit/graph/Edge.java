package survivingit.graph;

import java.util.Objects;

/**
 * Edge class. Stores data of an edge
 * in a weighed graph.
 *
 * @param <N> Node type to be used in graph
 *
 * @see Graph
 * @see Node
 */
public class Edge<N extends Node<?>> {
    private N from;
    private N to;
    private double weight;

    public Edge(N from, N to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public N getFrom() {
        return this.from;
    }

    public N getTo() {
        return this.to;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return (this.from.equals(edge.from) && this.to.equals(edge.to));
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        //return "(" + from + "," + to + "," + weight + ")";
        return "(" + from + "," + to + ")";
    }
}