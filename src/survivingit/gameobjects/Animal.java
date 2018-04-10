package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.scene.Tile;
import survivingit.util.Point;

import java.util.List;
import java.util.Stack;

public abstract class Animal extends Creature {

    private double viewDistance; // Distance animal can see other gameobjects in
    private Stack<Point> path;
    private GameObject target;

    private static final double IN_POINT_RANGE = 0.25; // Used for determining whether animal is in tile or not

    public Animal(final double x, final double y, final CreatureSprite sprites, final int maxHealth, final double moveSpeed, final int alphaLevel, final double viewDistance) {
        super(x, y, sprites, maxHealth, moveSpeed, alphaLevel);
        this.path = new Stack<>();
        this.viewDistance = viewDistance;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Try to find target
        this.target = findTarget();

        // Calculate path based on target
        if (this.target != null && this.path.size() <= 1) {
            this.path = this.scene.findPath(new Point(this.x, this.y), new Point(target.x, target.y));
        }

        // Follow path if it exists
        if (!this.path.isEmpty()) {
            this.followPath();
        } else {
            this.setDirection(Direction.NONE);
        }
    }

    private void followPath() {
        Point next = path.peek();
        Point current = new Point(this.x, this.y);
        if (Point.areClose(next, current)) {
            // Reached target
            path.pop();
        }
        // Set direction to best direction to find target
        this.direction = Direction.fromAngle(Point.getAngle(new Point(this.x, this.y), next));
    }

    public Stack<Point> getPath() {
        return this.path;
    }

    private GameObject findTarget() {
        List<GameObject> inRange = this.scene.getObjectsInArea(this.x - viewDistance/2,
                                                               this.y - viewDistance/2,
                                                               this.x + viewDistance/2,
                                                               this.y + viewDistance/2);
        for(GameObject object : inRange) {
            if(object instanceof Creature) {
                if(object.equals(this)) continue; // Ignore itself
                // Possible target. Check alpha level
                if(((Creature)object).getAlphaLevel() <= this.alphaLevel) {
                    return object;
                }
            }
        }
        return null;
    }

    public double getViewDistance() {
        return this.viewDistance;
    }

}
