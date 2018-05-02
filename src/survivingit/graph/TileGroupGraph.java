package survivingit.graph;

import survivingit.scene.TileGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Used in scene generation to determine what tilegroups to put next to each other.
 *
 * Used in previous build, might be used later.
 */
@Deprecated
public class TileGroupGraph implements Graph<TileGroup> {

    private Map<TileGroup, Map<TileGroup, Double>> connections;

    public TileGroupGraph() {
        this.connections = new HashMap<>();
    }

    public boolean addNode(TileGroup node) {
        if(connections.containsKey(node)) return false;

        connections.put(node, new HashMap<>());
        return true;
    }

    public boolean addEdge(TileGroup from, TileGroup to, double weight) {
        if(!connections.containsKey(from) || !connections.containsKey(to)) return false;

        connections.get(from).put(to, Double.valueOf(weight));
        return false;
    }

    public Set<TileGroup> getNeighbors(TileGroup node) {
        return connections.get(node).keySet();
    }

    public double getWeight(TileGroup from,  TileGroup to) {
        return connections.get(from).get(to);
    }

}
