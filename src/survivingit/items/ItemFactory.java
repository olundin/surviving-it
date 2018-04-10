package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class ItemFactory {

	public final static Item NONE_ITEM = new Item(ItemType.NONE, "None", "None", Sprite.FOX, 1);

    public static Item createItem(ItemType itemType) {
		switch (itemType) {
			case KNIFE: {
				Item knife = new Item(itemType, "Knife", "Stabby stabby", Sprite.MEME_MAN, 1);
				knife.addEffect(new AttackEffect(10, 10, knife));
				return knife;
			}
			case BERRIES: {
				Item berries = new Item(itemType, "Berries", "Delicious", Sprite.FOX, 100);
				return berries;
			}
			case NONE: {
				throw new IllegalArgumentException("Cannot create item of type NONE");
			}
		}
		// TODO: Figure out how to get rid of this.
		return null;
	}


}
