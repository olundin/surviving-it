package survivingit.scene;

import survivingit.gameobjects.Campfire;
import survivingit.gameobjects.Fox;
import survivingit.gameobjects.Pine;
import survivingit.gameobjects.Player;
import survivingit.util.Maths;
import survivingit.util.PerlinNoise;
import survivingit.util.SimplexNoise;

import java.util.Random;

public class SceneGenerator {

    private double snowRange;
    private double rockRange;
    private double iceRange;
    private double waterRange;

    private static final Random RANDOM = new Random();

    public SceneGenerator(double snow, double rock, double ice, double water) {
        double tot = snow + rock + ice + water;

        // Calculate ranges
        this.waterRange = Maths.normalize(water, 0.0, tot, -1.0, 1.0);
        this.iceRange = Maths.normalize(water + ice, 0.0, tot, -1.0, 1.0);
        this.snowRange = Maths.normalize(water + ice + snow, 0.0, tot, -1.0, 1.0);
        this.rockRange = Maths.normalize(water + ice + snow + rock, 0.0, tot, -1.0, 1.0);
    }

    public void generateScene(Scene scene, boolean generateTiles, boolean generateVegetation, boolean generateStructures) {
        if(generateTiles) generateTiles(scene);
        if(generateVegetation) generateVegetation(scene);
        if(generateStructures) generateStructures(scene);
    }

    private void generateTiles(Scene scene) {
        // Generate noise
        double[][] noise = generateNoise(scene.getWidth(), scene.getHeight());
        // Fill tile array with tiles based on noise
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getHeight(); x++) {
                // Add walls to edges
                if(x == 0 || y == 0 || x == scene.getWidth() - 1 || y == scene.getHeight() - 1) {
                    scene.setTileAt(x, y, Tile.WALL);
                } else {
                    scene.setTileAt(x, y, tileFromNoise(noise[y][x]));
                }
            }
        }
    }

    private void generateVegetation(Scene scene) {
        int tilesSincePlaced = 0;
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getWidth(); x++) {
                Tile placedOn = scene.getTileAt(x, y);
                if(placedOn.isPassable() && placedOn.isFertile()) {
                    tilesSincePlaced++;
                    if(RANDOM.nextInt(tilesSincePlaced) >= 20 && scene.tryAdd(new Pine(x + 0.5, y + 0.75))) {
                        tilesSincePlaced = 0;
                    }
                }
            }
        }
    }

    private void generateStructures(Scene scene) {
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getWidth(); x++) {
                // Generate some campfires
                if(x % 20 == 0 && y % 20 == 0) {
                    scene.tryAdd(new Campfire(x + 0.5, y + 0.5));
                }
            }
        }
    }

    private double[][] generateNoise(int width, int height) {
        double[][] noise = new double[height][width];
        double frequency = 10.0 / (double)width;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                noise[y][x] = PerlinNoise.noise(x * frequency, y * frequency, 0.0);
            }
        }

        return noise;
    }

    private Tile tileFromNoise(double noise) {
        if(noise <= waterRange) return Tile.WATER.getRandom();
        else if(noise <= iceRange) return Tile.ICE.getRandom();
        else if(noise <= snowRange) return Tile.SNOW.getRandom();
        else if(noise <= rockRange) return Tile.ROCK.getRandom();

        return Tile.VOID.getRandom();
    }

}
