package survivingit.graphics;

import survivingit.scene.Camera;
import survivingit.gameobjects.VisibleObject;
import survivingit.scene.Tile;

/**
 * Interface for a WorldRenderer that includes methods that are necessary for a Renderer to be a WorldRenderer.
 */
public interface WorldRenderer {

    /**
     * Draws the entered Tile at the relative position to the camera and the entered x, y position.
     * @param x int value of x position of where to draw the entered Tile.
     * @param y int value of y position of where to draw the entered Tile.
     * @param tile Tile to be drawn.
     * @param camera Camera in relation to which the Tile is drawn.
     */
    public void drawTile(int x, int y, Tile tile, Camera camera);

    /**
     * Draws the entered VisibleObject object in relation to the entered Camera object.
     * @param object VisibleObject to be drawn.
     * @param camera Camera in which the VisibleObject is drawn.
     */
    public void drawObject(VisibleObject object, Camera camera);

}
