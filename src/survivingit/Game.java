package survivingit;

import survivingit.gameobjects.Player;
import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;
import survivingit.input.InputHandler;

public class Game {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;


    private Renderer renderer;
    private Window window;
    private InputHandler inputHandler;
    private Player player;

    private static final int UPDATE_LIMIT = 60; // Max updates per second

    private boolean running = false;


    private Game() {
        this.window = new Window(WIDTH, HEIGHT);
        this.renderer = new Renderer(WIDTH, HEIGHT);
        this.player = new Player(0, 0, Sprite.FOX, 10, 1);
        this.inputHandler = new InputHandler(this.player);
    	window.add(renderer);
	renderer.createBufferStrategy(3);
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
        inputHandler.update();
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
