package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.scene.Scene;
import survivingit.scene.Tile;
import survivingit.util.Vec2;

import java.util.List;

public class Camera extends GameObject {

    private double width;
    private double height;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private GameObject target;

    public Camera(final double x, final double y, final double width, final double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void zoom(double delta) {
        double relation = this.height / this.width;
        this.width -= delta;
        this.height -= (delta * relation);
        this.pos.x += delta / 2;
        this.pos.y += (delta * relation) / 2;
    }

    public void render(Renderer renderer, Scene scene) {
        // Make sure target is being followed if it exists
        if(target != null) {
            this.pos.x = target.getPos().x - this.width / 2;
            this.pos.y = target.getPos().y - this.height / 2;
        }

        // Render visible Tiles
        for (int tileY = (int)Math.floor(this.pos.y); tileY < this.pos.y + this.height; tileY++) {
            for (int tileX = (int)Math.floor(this.pos.x); tileX < this.pos.x + this.width; tileX++) {
                Tile tile = scene.getTileAt(tileX, tileY);
                if (tile != null) {
                    // Draw sprite at position relative to camera
                    renderer.drawSprite(Vec2.sub(new Vec2(tileX, tileY), this.pos), tile.getSprite(),
                                        this.width, this.height);
                }
            }
        }

        // Render visible GameObjects
        List<GameObject> objectsInArea = scene.getObjectsInArea(Vec2.sub(this.pos, EDGE_PADDING),
                                                                Vec2.add(this.pos, EDGE_PADDING));

        for (GameObject gameObject : objectsInArea) {
            if (gameObject instanceof GameVisibleObject) {
                // Draw sprite at position relative to camera
                renderer.drawSprite(Vec2.sub(this.pos, gameObject.getPos()),
                                    ((GameVisibleObject)gameObject).getSprite(), this.width, this.height);
            }

            /*
            // Make sure to target player if it is found. TODO: Remove this
            if (gameObject instanceof Player && this.target == null) {
                this.target = gameObject;
            }
            */
        }
    }
}
