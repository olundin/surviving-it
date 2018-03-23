package survivingit.gameobjects;

import java.util.Comparator;

public class GameObjectComparator implements Comparator<GameObject> {
    public int compare(GameObject o1, GameObject o2) {
        return Double.compare(o1.getY(), o2.getY());
    }
}
