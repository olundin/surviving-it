package survivingit;

import survivingit.gameobjects.Player;
import survivingit.graphics.Camera;
import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;
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
        this.inputHandler = new InputHandler();
    	window.add(renderer);
    	window.addKeyListener(keyboard);
	renderer.createBufferStrategy(3);

	this.currentScene = new TestScene();
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
        // Zoom in/out with z/x
        if(keyboard.getKey(65)) camera.move(-0.001, 0);
        if(keyboard.getKey(68)) camera.move(0.001, 0);
        if(keyboard.getKey(87)) camera.move(0, -0.001);
        if(keyboard.getKey(83)) camera.move(0, 0.001);

        // Zoom in/out with z/x
        if(keyboard.getKey(90)) camera.zoom(0.001);
        if(keyboard.getKey(88)) camera.zoom(-0.001);


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
