package survivingit;

import survivingit.gameobjects.Camera;
import survivingit.graphics.Renderer;
import survivingit.hud.Hud;
import survivingit.input.InputHandler;
import survivingit.input.Keyboard;
import survivingit.input.Mouse;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;
import survivingit.scene.Scene;
import survivingit.scene.TestScene;

public class Game implements Observer<Window> {

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Renderer renderer;
    private Window window;
    private Keyboard keyboard;
    private Mouse mouse;
    private InputHandler inputHandler;

    private Scene currentScene;
    private Camera camera;
    private Hud hud;

    private boolean running = false;

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
        this.currentScene = new TestScene();
        this.currentScene.add(this.camera);
        this.hud = new Hud(currentScene.getPlayer());
    }

    private void start() {
        running = true;

        final double targetDelta = 1.0/60.0;
        final double maxDelta = 0.05;
        long previousTime = System.nanoTime();
        final double nanosPerSec = 1_000_000_000.0;

        while(running) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime) / nanosPerSec;
            deltaTime = Math.min(deltaTime, maxDelta);   // Set a cap to deltaTime

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


    private void stop() {
        running = false;
        System.exit(0);
    }

    private void update(double dt) {
        inputHandler.handleInput(currentScene.getPlayer(), camera);
        currentScene.update(dt);

        keyboard.clear();
        mouse.clear();
    }

    private void render() {
        renderer.prepare();
        renderer.clear();

        camera.render(renderer);
        hud.render(renderer);

        renderer.display();
    }

    public void onNotify(Observable<Window> object, Window data) {
        if(!data.isOpen()) {
            this.stop();
        }
    }

    public static void main(String[] args) {
	    Game game = new Game();
	    game.start();
    }
}