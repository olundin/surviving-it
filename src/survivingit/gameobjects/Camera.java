package survivingit.gameobjects;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
        this.setCenterPos(this.pos);
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
        if (hasTarget()) {
            this.setCenterPos(target.getPos());
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

        Vec2 sizeVec2 = this.getSizeVec2();
        Vec2 start = Vec2.subConst(this.pos, EDGE_PADDING);
        Vec2 end = Vec2.addConst(Vec2.add(this.pos, sizeVec2), EDGE_PADDING);
        System.out.println("start, end: " + start + ", " + end);

        // Render visible GameObjects
        List<GameObject> objectsInArea = scene.getObjectsInArea(Vec2.subConst(this.pos, EDGE_PADDING),
                                                                Vec2.addConst(Vec2.add(this.pos, sizeVec2), EDGE_PADDING));
        System.out.println(objectsInArea);

        System.out.println("Camera pos: " + this.pos);
        System.out.println("Camera centerPos: " + getCenterPos());

        for (GameObject gameObject : objectsInArea) {

            if (gameObject instanceof GameVisibleObject) {
                // Draw sprite at position relative to camera
                Vec2 drawPos = Vec2.sub(gameObject.getPos(), this.pos);
                renderer.drawSprite(drawPos, ((GameVisibleObject)gameObject).getSprite(), this.width, this.height);

                System.out.println("Object pos: " +  gameObject.pos);
                System.out.println("Drawing object at : " + drawPos);
            }
        }
    }

    private boolean hasTarget() {
        return target != null;
    }

    public void setCenterPos(Vec2 centerPos) {
        this.pos = Vec2.sub(centerPos, new Vec2(this.width/2, this.height/2));
    }

    public Vec2 getCenterPos() {
        return Vec2.add(this.pos, new Vec2(this.width/2, this.height/2));
    }

    private Vec2 getSizeVec2() {
        return new Vec2(this.width, this.height);
    }

}
