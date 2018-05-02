package survivingit;

import survivingit.graphics.Renderer;
import survivingit.hud.Hud;
import survivingit.input.InputHandler;
import survivingit.input.Keyboard;
import survivingit.input.Mouse;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;
import survivingit.scene.Camera;
import survivingit.scene.Scene;
import survivingit.scene.TestScene;
import survivingit.scene.Tile;

/**
 * Game is the main class.
 * It contains the game loop and is responsible
 * for querying updates and renders.
 */
public class Game implements Observer<Window> {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final boolean FULLSCREEN = true;

    private Renderer renderer;
    private Window window;
    private Keyboard keyboard;
    private Mouse mouse;
    private InputHandler inputHandler;

    private Scene currentScene;
    private Camera camera;
    private Hud hud;

    private boolean running = false;

    /**
     * Creates a new Game with the necessary parts.
     */
    private Game() {
        this.window = new Window(WIDTH, HEIGHT);
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

        this.camera = new Camera(0, 0, 24, 13.5, 0, 0, WIDTH, HEIGHT);
        this.currentScene = new TestScene(this.camera);
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
        while(running) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime) / nanosPerSec;
            deltaTime = Math.min(deltaTime, maxDelta);   // Set a cap to deltaTime

            // Update with delta time, then render
            this.update(deltaTime);
            this.render();

            previousTime = currentTime;

            double frameTime = (System.nanoTime() - currentTime) / nanosPerSec;
            while(targetDelta - frameTime > 0) {
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
        inputHandler.handleInput(dt, currentScene.getPlayer(), camera, hud);

        currentScene.update(dt);

        // Update animated tile's sprites
        Tile.updateAnimated(dt);

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
     * @param data The data
     */
    public void onNotify(Observable<Window> object, Window data) {
        if(!data.isOpen()) {
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