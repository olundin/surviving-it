package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;
import survivingit.scene.Tile;
import survivingit.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fox extends Creature {

    private double timer; // Used when randomizing direction;
    private static Random random = new Random();

    private List<Point> path;

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOXES, random.nextInt(8) * 129, 0, 43, 40),
              20,
              2.5);

        this.setCollider(new Collider(-0.2, 0, 0.4, 0.5, false, this));
        this.timer = 0.0;

        this.path = new ArrayList<>();
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        if(this.path.isEmpty() && this.scene.hasPlayer()) {
            Player target = scene.getPlayer();
            this.path = scene.findPath(this.x, this.y, target.x, target.y);
            for(Point p : this.path) {
                if(scene.getTileAt(p.getX(), p.getY()).isPassable())
                    scene.setTileAt(p.getX(), p.getY(), Tile.TRANSPARENT);
            }
            System.out.println(this.path);
        }

        // Randomize a direction. Possibility of doing so increases over time
        timer += dt;
        if(random.nextInt((int)timer + 1) >= 5) {
            this.direction = Direction.randomDirection();
            this.timer = 0.0;
        }
    }
}
