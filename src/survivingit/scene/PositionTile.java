package survivingit.scene;

/**
 * PositionTile can be used when a tile's information has to be sent with it's position in a scene
 */
public class PositionTile {

    private int x;
    private int y;
    private Tile tile;

    public PositionTile(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }
}
