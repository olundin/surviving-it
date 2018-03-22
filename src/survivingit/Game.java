package survivingit;

import survivingit.gameobjects.Camera;
import survivingit.graphics.Renderer;
import survivingit.input.InputHandler;
import survivingit.input.Keyboard;
import survivingit.input.Mouse;
import survivingit.scene.Scene;
import survivingit.scene.TestScene;

public class Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Renderer renderer;
    private Window window;
    private Keyboard keyboard;
    private Mouse mouse;
    private InputHandler inputHandler;

    private Scene currentScene;
    private Camera camera;

    private static final int UPDATE_LIMIT = 60; // Max updates per second

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

    	renderer.createBufferStrategy(3);

        this.camera = new Camera(0, 0, 16, 9, renderer);
        this.currentScene = new TestScene(camera);
    }

    private void start() {
        running = true;

        double t = 0.0; // Game time
        double dt = 1 / 60.0;

        double currentTime = System.currentTimeMillis() / 1000.0;

        while (running) {
            double newTime =  System.currentTimeMillis() / 1000.0;
            double frameTime = newTime - currentTime;
            currentTime = newTime;

            while(frameTime > 0.0) {
                double deltaTime = Math.min(frameTime, dt); // Make sure that deltaTime is never greater than dt
                update(deltaTime);
                frameTime -= deltaTime;
                t += deltaTime;
            }
            render();
        }
    }


    private void stop() {
        running = false;
    }

    private void update(double dt) {
        inputHandler.handleInput(currentScene.getPlayer(), camera);

        keyboard.update();
        mouse.update();
        currentScene.update(dt);
    }

    private void render() {
        renderer.prepare();
        renderer.clear();
        camera.render(currentScene);
        renderer.display();
    }

    public static void main(String[] args) {
	    Game game = new Game();
	    game.start();
    }
}