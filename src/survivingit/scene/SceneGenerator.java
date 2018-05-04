package survivingit.scene;

import survivingit.Game;
import survivingit.gameobjects.*;
import survivingit.util.Maths;
import survivingit.util.PerlinNoise;

/**
 * Class used to generate scenes.
 * This means filling them with tiles, animals, structures, e.t.c.
 *
 * @see Scene
 */
public class SceneGenerator {

    private double snowRange;
    private double rockRange;
    private double iceRange;
    private double waterRange;

    private static final int MIN_PINE_DISTANCE = 50;
    private static final int CAMPFIRE_GRID_SIZE = 20;
    private static final int ANIMAL_GRID_SIZE = 10;

    /**
     * Creates a new scene generator with the given tile group occurrences.
     * The parameters can be any value above zero.
     * @param snow Snow occurrence
     * @param rock Rock occurrence
     * @param ice Ice occurrence
     * @param water Water occurrence
     */
    public SceneGenerator(double snow, double rock, double ice, double water) {
        double tot = snow + rock + ice + water;

        // Calculate ranges
        this.waterRange = Maths.affineTransformation(water, 0.0, tot, -1.0, 1.0);
        this.iceRange = Maths.affineTransformation(water + ice, 0.0, tot, -1.0, 1.0);
        this.snowRange = Maths.affineTransformation(water + ice + snow, 0.0, tot, -1.0, 1.0);
        this.rockRange = Maths.affineTransformation(water + ice + snow + rock, 0.0, tot, -1.0, 1.0);
    }

    /**
     * Generates given scene.
     * @param scene The scene to generate (fill with things)
     * @param generateTiles Should tiles be generated?
     * @param generateVegetation Should vegetation be generated?
     * @param generateStructures Should structures be generated?
     */
    public void generateScene(Scene scene, boolean generateTiles, boolean generateVegetation, boolean generateStructures, boolean generateCreatures) {
        if(generateTiles) generateTiles(scene);
        if(generateVegetation) generateVegetation(scene);
        if(generateStructures) generateStructures(scene);
        if(generateCreatures) generateCreatures(scene);
    }

    /**
     * Generates tiles in scene using Perlin Noise.
     * @param scene The scene to set tiles in
     *
     * @see PerlinNoise
     */
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

    /**
     * Generates vegetation in given scene.
     * Only puts vegetation on some of the fertile tiles.
     * @param scene The scene to fill with vegetation
     */
    private void generateVegetation(Scene scene) {
        int tilesSincePlaced = 0;
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getWidth(); x++) {
                Tile placedOn = scene.getTileAt(x, y);
                if(placedOn.isPassable() && placedOn.isFertile()) {
                    tilesSincePlaced++;
                    if(Game.RANDOM.nextInt(tilesSincePlaced) >= MIN_PINE_DISTANCE && scene.tryAdd(new Pine(x+0.5, y+0.5, scene))) {
                        tilesSincePlaced = 0;
                    }
                }
            }
        }
    }

    /**
     * Generates structures in given scene.
     * @param scene The scene to fill with structures
     */
    private void generateStructures(Scene scene) {
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getWidth(); x++) {
                // Generate some campfires
                if(x % CAMPFIRE_GRID_SIZE == 0 && y % CAMPFIRE_GRID_SIZE == 0) {
                    scene.tryAdd(new Campfire(x+0.5, y+0.5, scene));
                }
            }
        }
    }

    /**
     * Generates some animals in the scene
     * @param scene The scene to fill with animals
     */
    private void generateCreatures(Scene scene) {
        for(int y = 0; y < scene.getHeight(); y++) {
            for(int x = 0; x < scene.getWidth(); x++) {
                // Generate some campfires
                if(x % ANIMAL_GRID_SIZE == 0 && y % ANIMAL_GRID_SIZE == 0) {
                    int animal = Game.RANDOM.nextInt(3);
                    switch(animal) {
                        case 0:
                            scene.tryAdd(new Fox(x, y, scene));
                            break;
                        case 1:
                            scene.tryAdd(new Penguin(x, y, scene));
                            break;
                        case 2:
                            scene.tryAdd(new Boar(x, y, scene));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        double yetiX = Game.RANDOM.nextDouble()*scene.getWidth();
        double yetiY = Game.RANDOM.nextDouble()*scene.getHeight();
        scene.add(new Yeti(yetiX, yetiY, scene));
    }

    /**
     * Generates noise map using the perlin noise algorithm.
     * @param width The width of the noise map
     * @param height The height of the noise maps
     * @return The noise map
     *
     * @see PerlinNoise
     */
    private double[][] generateNoise(int width, int height) {
        double[][] noise = new double[height][width];
        double frequency = 5 / (double)width;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                noise[y][x] = PerlinNoise.noise(x * frequency, y * frequency, 0.0);
            }
        }

        return noise;
    }

    /**
     * Returns a tile based on given noise and the scene generator's
     * tile group ranges.
     * @param noise The noise value. Should come from the Perlin Noise class. Has to be in the range (-1, 1)
     * @return A tile based on the noise
     *
     * @see PerlinNoise
     */
    private Tile tileFromNoise(double noise) {
        if(noise <= waterRange) return AnimatedTile.WATER;
        else if(noise <= iceRange) return Tile.ICE.getRandom();
        else if(noise <= snowRange) return Tile.SNOW.getRandom();
        else if(noise <= rockRange) return Tile.ROCK.getRandom();

        return AnimatedTile.VOID;
    }

}
