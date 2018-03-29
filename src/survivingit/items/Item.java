package survivingit.items;

import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private final String description;
    private final Sprite sprite;
    private final int stackSize;
    private final String name;
    private List<AbstractEffect> abstractEffects;

    public Item(final String name, final String description, final Sprite sprite, final int stackSize) {
        this.name = name;
        this.description = description;
        this.sprite = sprite;
        this.stackSize = stackSize;
        this.abstractEffects = new ArrayList<>();
    }

    public void addEffect(AbstractEffect abstractEffect) {
        this.abstractEffects.add(abstractEffect);
    }

    public String getDescription() {
        return description;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getStackSize() {
        return stackSize;
    }

    public String getName() {
        return name;
    }
}
