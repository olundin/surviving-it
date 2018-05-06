package survivingit;

import survivingit.graphics.Renderer;
import survivingit.hud.Hud;
import survivingit.input.InputHandler;
import survivingit.input.Keyboard;
import survivingit.input.Mouse;
import survivingit.messaging.Observer;
import survivingit.scene.*;

import java.util.Random;

/**
 * Game is the main class.
 * It contains the game loop and is responsible
 * for querying updates and renders.
 */
public final class Game implements Observer<GameWindow> {

    /**
     * Width of game window, e.t.c.
     */
    public static final int WIDTH = 1920;
    /**
     * Height of game window, e.t.c.
     */
    public static final int HEIGHT = 1080;
    /**
     * Should the window be fullscreen
     */
    public static final boolean FULLSCREEN = true;

    /**
     * Global random to ensure THE BEST random quality in the world
     */
    public static final Random RANDOM = new Random();

    private static final double CAMERA_WORLD_WIDTH = 24;
    private static final double CAMERA_WORLD_HEIGHT = 13.5;

    private Renderer renderer;
    private Keyboard keyboard;
    private Mouse mouse;
    private InputHandler inputHandler;

    private Scene currentScene;
    private Hud hud;

    private boolean running = false;

    /**
     * Creates a new Game with the necessary parts.
     */
    private Game() {
        GameWindow window = new GameWindow(WIDTH, HEIGHT);
        this.renderer = new Renderer(WIDTH, HEIGHT);
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        this.inputHandler = new InputHandler(keyboard, mouse);

        window.add(renderer);

        window.addKeyListener(keyboard);
    	renderer.addMouseListener(mouse);
    	renderer.addMouseWheelListener(mouse);
    	renderer.addMouseMotionListener(mouse);

    	window.attach(this); // Observe window (to know when it's closed)

    	renderer.createBufferStrategy(3);

        Camera camera = new Camera(0, 0, CAMERA_WORLD_WIDTH, CAMERA_WORLD_HEIGHT, 0, 0, WIDTH, HEIGHT);
        this.currentScene = new TestScene(camera);
        this.hud = new Hud(currentScene.getPlayer());
    }

    /**
     * Starts the game.
     */
    private void start() {
        running = true;

        // Min allowed delta between updates
        final double targetDelta = 1.0/60.0;
        // Max allowed delta between updates
        final double maxDelta = 0.05;
        long previousTime = System.nanoTime();
        // For conversion from nanoseconds to seconds
        final double nanosPerSec = 1_000_000_000.0;

        // Game loop
        // IGNORED INSPECTION WARNING
        // Inspection warning ignored since it is false. The loop does not spin on field since it is changed in
        // Game.stop(). This method is called from the GameWindow class.
        while (running) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime) / nanosPerSec;
            deltaTime = Math.min(deltaTime, maxDelta);   // Set a cap to deltaTime

            // Update with delta time, then render
            this.update(deltaTime);
            this.render();

            previousTime = currentTime;

            double frameTime = (System.nanoTime() - currentTime) / nanosPerSec;
            while (targetDelta - frameTime > 0) {
                // Make sure we aren't updating too often
                frameTime = (System.nanoTime() - currentTime) / nanosPerSec;
            }
        }
    }


    /**
     * Stop the game.
     */
    private void stop() {
        running = false;
        System.exit(0);
    }

    private void update(double dt) {
        inputHandler.handleInput(dt, currentScene.getPlayer(), currentScene.getCamera(), hud);

        currentScene.update(dt);

        // Update animated tile's sprites (static)
        AnimatedTile.updateAll(dt);

        keyboard.clear();
        mouse.clear();
    }

    private void render() {
        renderer.prepare();
        renderer.clear();

        currentScene.render(renderer);
        hud.render(renderer);

        renderer.display();
    }

    /**
     * Lets an observable notify this observer
     * @param object The observed object
     */
    @Override
    public void onNotify(GameWindow object) {
        if (!object.isOpen()) {
            this.stop();
        }
    }

    /**
     * Program entry point.
     * Creates a new game and starts it
     * @param args Arguments
     */
    public static void main(String[] args) {
	    Game game = new Game();
	    game.start();
    }
}