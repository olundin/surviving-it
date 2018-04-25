package survivingit.scene;

import survivingit.graphics.Sprite;

import java.util.Random;
import java.util.Set;

public class TileGroup {

    private Tile[] tiles;
    private static Random random;
    private int size;

    public TileGroup(Sprite[] sprites, boolean[] passable) {
        this.size = Math.min(sprites.length, passable.length);
        this.tiles = new Tile[size];
        for(int i = 0; i < size; i++) {
            tiles[i] = new Tile(sprites[i], passable[i]);
        }
        random = new Random();
    }

    public Tile[] getTiles() {
        return tiles;
    }
    public Set<Tile> toSet() { return Set.of(tiles); }
    public Tile getPlain() { return tiles[0]; }
    public Tile getRandom() {
        int rand = random.nextInt(size*10);
        if(rand >= size) return tiles[0];
        else return tiles[rand];
    }

}
