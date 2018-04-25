package survivingit.scene;

import survivingit.util.Maths;
import survivingit.util.SimplexNoise;

public class SceneGenerator {

    private double snowRange;
    private double rockRange;
    private double iceRange;
    private double waterRange;

    public SceneGenerator(double snow, double rock, double ice, double water) {
        double tot = snow + rock + ice + water;

        // Calculate ranges
        this.waterRange = Maths.normalize(water, 0.0, tot, -1.0, 1.0);
        this.iceRange = Maths.normalize(water + ice, 0.0, tot, -1.0, 1.0);
        this.snowRange = Maths.normalize(water + ice + snow, 0.0, tot, -1.0, 1.0);
        this.rockRange = Maths.normalize(water + ice + snow + rock, 0.0, tot, -1.0, 1.0);
    }

    public Tile[][] generateTiles(Scene scene) {
        Tile[][] tiles = new Tile[scene.getHeight()][scene.getWidth()];
        double[][] noise = generateNoise(scene.getWidth(), scene.getHeight());
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getHeight(); x++) {
                tiles[y][x] = tileFromNoise(noise[y][x]);
            }
        }
        return tiles;
    }

    private double[][] generateNoise(int width, int height) {
        double[][] noise = new double[height][width];
        double frequency = 2.5 / (double)width;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                noise[y][x] = SimplexNoise.noise(x * frequency, y * frequency);
            }
        }

        return noise;
    }

    private Tile tileFromNoise(double noise) {
        if(noise <= waterRange) return Tile.WATER.getRandom();
        else if(noise <= iceRange) return Tile.ICE.getRandom();
        else if(noise <= snowRange) return Tile.SNOW.getRandom();
        else return Tile.ROCK.getRandom();
    }

}
