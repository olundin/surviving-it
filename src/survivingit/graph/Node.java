package survivingit.graph;

import java.util.Objects;

/**
 * Node class, which in turn is used in Graph.
 * Each node has an identifier which serves as
 * the thing making every node unique.
 *
 * @param <T> The identifying object of the node.
 *
 * @see Graph
 * @see Edge
 */
public class Node<T> {

    private T id; // Nodes with the same id should be treated as the same in graph.

    public Node(T id) {
        this.id = id;
    }

    public T getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}