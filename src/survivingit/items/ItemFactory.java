package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class ItemFactory {

    public static Item createItem(ItemType itemType) {
        switch (itemType) {
	    case KNIFE:
		Item knife = new Item("KNIFE", "Stabby stabby", Sprite.MEME_MAN, 1);
		knife.addEffect(new DamageEffect(10, knife));
		return knife;
	    case BERRIES:
	        Item berries = new Item( "Berries", "Balls", Sprite.FOX, 100);
	        return berries;
	    case NONE:
	        throw new IllegalArgumentException("Cannot create item of type NONE");
	}
	return null;
    }

}
