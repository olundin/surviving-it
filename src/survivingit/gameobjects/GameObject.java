package survivingit.gameobjects;

import survivingit.util.Vec2;

public abstract class GameObject implements Updateable {

    protected Vec2 pos;

    public GameObject() {
        this.pos = new Vec2(0, 0);
    }

    public GameObject(final double x, final double y) {
        this.pos = new Vec2(x, y);
    }

    public Vec2 getPos() {
        return this.pos;
    }

    public void setPos(final double x, final double y) {
	    this.pos = new Vec2(x, y);
    }

    public void setPos(final Vec2 pos) {
	    this.pos = pos;
    }

    public void update() {
        // lol
    }

}
