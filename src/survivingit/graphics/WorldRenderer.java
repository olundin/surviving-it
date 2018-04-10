package survivingit.graphics;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.VisibleObject;
import survivingit.scene.Tile;

public interface WorldRenderer {

    public void drawTile(int x, int y, Tile tile, Camera camera);

    public void drawObject(VisibleObject object, Camera camera);

}
