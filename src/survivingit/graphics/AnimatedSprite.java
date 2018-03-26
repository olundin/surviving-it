package survivingit.graphics;

public class AnimatedSprite {

    private enum AnimationDirection {
        FORWARDS(1),
        BACKWARDS(-1);

        public final int step;

        AnimationDirection(int step) {
            this.step = step;
        }

    }

    private int frameIndex; // Current frame
    private double frameLength; // Length in seconds of each frame
    private AnimationDirection direction; // Direction to play in, forwards = 1, backwards = -1
    private boolean paused; // If true, pause animation at current frame
    private double timer; // Keep track of time, in seconds

    private Sprite[] frames;

    public AnimatedSprite(final Sprite[] frames, final double frameLength) {
        this.frameIndex = 0;
        this.frameLength = frameLength;
        this.direction = AnimationDirection.FORWARDS;
        this.paused = false;
        this.timer = 0.0;

        this.frames = frames;
    }

    public AnimatedSprite(final SpriteSheet spriteSheet, final int width, final int height, final double frameLength) {
        this.frameIndex = 0;
        this.frameLength = frameLength;
        this.direction = AnimationDirection.FORWARDS;
        this.paused = false;
        this.timer = 0.0;

        Sprite[] frames = new Sprite[(spriteSheet.getWidth() / width) * (spriteSheet.getHeight() / height)];
        // Add all sprites in SpriteSheet to frames
        for(int y = 0; y < (spriteSheet.getHeight() / height); y++) {
            for(int x = 0; x < (spriteSheet.getWidth() / width); x++) {
                frames[x + y * width] = new Sprite(x * width, y * width, width, height, spriteSheet);
            }

        }
        this.frames = frames;
    }

    public void update(double dt) {
        timer += dt;

        if(timer >= frameLength) {
            timer = 0.0;
            frameIndex += direction.step;

            if(frameIndex > frames.length - 1) {
                frameIndex = 0;
            } else if(frameIndex < 0) {
                frameIndex = frames.length - 1;
            }
        }
    }

    public void start() {
        this.paused = false;
    }

    public void stop() {
        this.paused = true;
    }

    public void restart() {
        this.paused = false;
        this.frameIndex = 0;
    }

    public void setDirection(boolean forwards) {
        this.direction = forwards ? AnimationDirection.FORWARDS : AnimationDirection.BACKWARDS;
    }

    public Sprite getSprite() {
        return frames[frameIndex];
    }

}
