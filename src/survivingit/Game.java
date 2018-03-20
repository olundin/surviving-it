package survivingit;

import survivingit.gameobjects.Player;
import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;
import survivingit.input.InputHandler;
import survivingit.scene.Scene;

public class Game {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;


    private Renderer renderer;
    private Window window;
    private InputHandler inputHandler;

    private Scene currentScene;

    private static final int UPDATE_LIMIT = 60; // Max updates per second

    private boolean running = false;


    private Game() {
        this.window = new Window(WIDTH, HEIGHT);
        this.renderer = new Renderer(WIDTH, HEIGHT);
        this.inputHandler = new InputHandler();
    	window.add(renderer);
	renderer.createBufferStrategy(3);

	this.currentScene = new Scene();
    }

    private void start() {
        running = true;

        while (running) {
            update();
            render();
	}

	currentScene.add(new Player(0, 0, Sprite.FOX, 100, 1.0f));
    }

    private void stop() {
        running = false;
    }

    private void update() {
    }

    private void render() {
        renderer.prepare();
        renderer.clear();
        renderer.display();
    }

    public static void main(String[] args) {
	Game game = new Game();
	game.start();
    }
}
