package survivingit.graphics;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for the animated sprites which handles animation.
 *
 * Contains data and functionality for changing the flow of the animation by pausing, playing, restarting and allowing
 * oscillation. Main usage of the AnimatedSprite is the getSprite() method that returns the current Sprite for rendering.
 */
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

    /**
     * Updates the AnimatedSprite object by the entered amount of passed time.
     * @param dt double val of passed time.
     */
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

    /**
     * Starts the AnimatedSprites object's animation.
     */
    public void start() {
        this.paused = false;
    }

    /**
     * Stops the AnimatedSprites objects's animation.
     */
    public void stop() {
        this.paused = true;
    }

    /**
     * Restarts the AnimatedSprites object's animation.
     */
    public void restart() {
        this.paused = false;
        this.frameIndex = 0;
    }

    /**
     * Sets the AnimatedSprites object's animation direction.
     * @param forwards boolean if the animation direction is to be set forwards.
     */
    public void setDirection(boolean forwards) {
        this.direction = forwards ? AnimationDirection.FORWARDS : AnimationDirection.BACKWARDS;
    }

    /**
     * Returns the current Sprite of the AnimatedSprite.
     * @return the current Sprite of the AnimatedSprite.
     */
    public Sprite getSprite() {
        return frames[frameIndex];
    }

    /**
     * Sets if the AnimatedSprite is oscillating or not.
     * @param oscillating boolean to set if the AnimatedSprite is oscillating or not.
     */
    public void setOscillating(boolean oscillating) {
        this.oscillating = oscillating;
    }

    /**
     * Sets the current frame index to the entered value.
     * @param frame int value to set the frameIndex to.
     */
    public void setFrame(int frame) {
        if (frame < 0 || frame >= frames.length) {
            throw new IllegalArgumentException("Entered frame index out of bound.");
        }
        this.frameIndex = frame;
    }

    /**
     * Sets the frame length to the value.
     * @param frameLength double value to set the frame length to.
     */
    public void setFrameLength(double frameLength) {
        this.frameLength = frameLength;
    }

}
