package survivingit;

import survivingit.gameobjects.Camera;
import survivingit.graphics.Renderer;
import survivingit.input.InputHandler;
import survivingit.input.Keyboard;
import survivingit.scene.Scene;
import survivingit.scene.TestScene;

public class Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Renderer renderer;
    private Window window;
    private Keyboard keyboard;
    private InputHandler inputHandler;

    private Scene currentScene;
    private Camera camera;

    private static final int UPDATE_LIMIT = 60; // Max updates per second

    private boolean running = false;


    private Game() {
        this.window = new Window(WIDTH, HEIGHT);
        this.renderer = new Renderer(WIDTH, HEIGHT);
        this.keyboard = new Keyboard();
        this.inputHandler = new InputHandler(keyboard);
    	window.add(renderer);
    	window.addKeyListener(keyboard);
	renderer.createBufferStrategy(3);

	this.currentScene = new TestScene(camera);
	this.camera = new Camera(0, 0, 16, 9);
    }

    private void start() {
        running = true;

        while (running) {
            update();
            render();
        }
    }

    private void stop() {
        running = false;
    }

    private void update() {
        inputHandler.handleInput(currentScene.getPlayer(), camera);
        currentScene.update();
    }

    private void render() {
        renderer.prepare();
        renderer.clear();
        camera.render(renderer, currentScene);
        renderer.display();
    }

    public static void main(String[] args) {
	Game game = new Game();
	game.start();
    }
}
