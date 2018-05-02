package survivingit.graphics;

import survivingit.gameobjects.Direction;

/**
 * Class for CreatureSprites that allows for clean Creature animation.
 *
 * Consists of multiple AnimatedSprites that are used depending on which movement direction the Creature has, which are
 * handled and updated in this class.
 * @see AnimatedSprite
 */
public class CreatureSprite {

    private AnimatedSprite left;
    private AnimatedSprite up;
    private AnimatedSprite right;
    private AnimatedSprite down;

    private AnimatedSprite current;

    /**
     * Creates a new CreatureSprite object with the entered SpriteSheet, startX, startY, spriteWidth and spriteHeight.
     * @param spriteSheet SpriteSheet for the CreatureSprite that consists of 3 by 4 sprites each of equal size.
     * @param startX int value of the x position of where the creatureSprite starts in the spriteSheet.
     * @param startY int value of the y position of where the creatureSprite starts in the spriteSheet.
     * @param spriteWidth int value of the width of the creatureSprite in the spriteSheet.
     * @param spriteHeight int value of the height of the creatureSprite in the spriteSheet.
     */
    public CreatureSprite(SpriteSheet spriteSheet, int startX, int startY, int spriteWidth, int spriteHeight) {
        // spriteSheet is required to consist of 3*4 sprites, where each row contains 3 frames.
        // Row 1 is the animation for walking down, row 2 = left, row 3 = right and row 4 = up
        this.left = new AnimatedSprite(Sprite.sheetToArray(spriteSheet, startX, startY, spriteWidth, spriteHeight, 3, 1), 0.2);
        this.up = new AnimatedSprite(Sprite.sheetToArray(spriteSheet, startX, startY + spriteHeight, spriteWidth, spriteHeight, 3, 1), 0.2);
        this.right = new AnimatedSprite(Sprite.sheetToArray(spriteSheet, startX, startY + 2*spriteHeight, spriteWidth, spriteHeight, 3, 1), 0.2);
        this.down = new AnimatedSprite(Sprite.sheetToArray(spriteSheet, startX, startY + 3*spriteHeight, spriteWidth, spriteHeight, 3, 1), 0.2);

        // Make all animated sprites oscillate (for correct walking animation)
        this.left.setOscillating(true);
        this.up.setOscillating(true);
        this.right.setOscillating(true);
        this.down.setOscillating(true);

        this.current = this.down;
    }

    /**
     * Updates the CreatureSprite object with the entered passed time dt, based on the Direction enum value of the Creature,
     * moveSpeed of the Creature and a boolean if the Creature is moving.
     * @param dt double value of the time passed.
     * @param direction Direction enum value of in which Direction the CreatureSprite should animate.
     * @param moveSpeed double value of movement speed which the CreatureSprite should animate.
     * @param isMoving boolean if the creatureSprite should animate as moving or not.
     */
    public void update(double dt, Direction direction, double moveSpeed, boolean isMoving) {

        // Decide which animated sprite to use
        switch(direction) {
            case LEFT:
            case UP_LEFT:
            case DOWN_LEFT:
                this.current = left;
                break;
            case UP:
                this.current = up;
                break;
            case RIGHT:
            case UP_RIGHT:
            case DOWN_RIGHT:
                this.current = right;
                break;
            case DOWN:
                this.current = down;
                break;
            case NONE:
            default:
                // Keep old sprites
                break;
        }

        // Set frame time based on move speed
        current.setFrameLength(1 / moveSpeed);

        // Update it if animal is moving
        if (isMoving) {
            this.current.update(dt);
        } else {
            // If not moving, set frame to middle (idle)
            this.current.setFrame(1);
        }
    }

    /**
     * Returns the current Sprite of the CreatureSprite.
     * @return the current Sprite of the CreatureSprite.
     */
    public Sprite getSprite() {
        return current.getSprite();
    }

}
