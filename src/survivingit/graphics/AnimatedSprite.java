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
    private boolean oscillating; // If true, bounce back and forth between frames (e.g. 0, 1, 2, 1, 0, 1...)

    private Sprite[] frames;

    /**
     * Generates an animated sprite from a spritesheet. Starting at (subX, subY) frames will
     * be added to the animated sprite with the size (width, height). This will be done for each row nX times,
     * and repeated during nY rows for a total of nX * ny frames;
     *
     * @param sprites The sprites to use
     * @param frameLength Length in seconds of each frame
     */
    public AnimatedSprite(Sprite[] sprites, double frameLength) {
        this.frameIndex = 0;
        this.frameLength = frameLength;
        this.direction = AnimationDirection.FORWARDS;
        this.paused = false;
        this.timer = 0.0;
        this.oscillating = false;
        this.frames = sprites;
    }

    public void update(double dt) {
        timer += dt;

        if(timer >= frameLength) {
            timer = 0.0;
            frameIndex += direction.step;

            if(frameIndex > frames.length - 1) {
                if(!oscillating) {
                    // Frame reached end, set to start
                    frameIndex = 0;
                } else {
                    // If oscillating, set to previous and change direction
                    this.frameIndex = frames.length - 2;
                    this.direction = AnimationDirection.BACKWARDS;
                }
            } else if(frameIndex < 0) {
                if(!oscillating) {
                    // Frame reached start, set to end
                    frameIndex = frames.length - 1;
                } else {
                    // If oscillating, set to previous and change direction
                    this.frameIndex = 1;
                    this.direction = AnimationDirection.FORWARDS;
                }
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

    public void setOscillating(boolean oscillating) {
        this.oscillating = oscillating;
    }

    public void setFrame(int frame) {
        this.frameIndex = frame;
    }

    public void setFrameLength(double frameLength) {
        this.frameLength = frameLength;
    }

}
