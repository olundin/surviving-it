package survivingit.gameobjects;

import java.util.Comparator;

/**
 * Comparator for GameObject class that compares gameObjects based on their deltaY position.
 * Implements the Comparator class.
 */
public class GameObjectComparator implements Comparator<GameObject> {

    /**
     * Compares two entered GameObjects on their deltaY position and returns -1 if the first GameObject has lower deltaY position,
       0 if the GameObjects have the same deltaY position or 1 if the first GameObject has a higher deltaY position.
     * @param o1 the first GameObject to be compared.
     * @param o2 the second GameObject to be compared.
     * @return -1 if the first GameObject has lower deltaY position, 0 if the GameObjects have the same deltaY position or 1 if
     * the first GameObject has a higher deltaY position.
     */
    @Override
    public int compare(GameObject o1, GameObject o2) {
        return Double.compare(o1.getY(), o2.getY());
    }
}
