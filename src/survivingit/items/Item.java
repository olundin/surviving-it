package survivingit.items;

import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private final ItemType itemType;

    private final String description;
    private final Sprite sprite;
    private final String name;
    private List<AbstractEffect> effects;

    public Item(final ItemType itemType, final String name, final String description, final Sprite sprite) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.sprite = sprite;
        this.effects = new ArrayList<>();
    }

    public void addEffect(AbstractEffect abstractEffect) {
        this.effects.add(abstractEffect);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return this.itemType;
    }
}
