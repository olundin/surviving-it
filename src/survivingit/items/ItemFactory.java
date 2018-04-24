package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class ItemFactory {

	public final static Item NONE_ITEM = new Item(ItemType.NONE, "None", "None", Sprite.CAMPFIRE);

    public static Item createItem(ItemType itemType) {
		switch (itemType) {
			case KNIFE: {
				Item knife = new Item(itemType, "Knife", "Stabby stabby", Sprite.CAMPFIRE);
				knife.addEffect(new AttackEffect(10, 10, knife));
				return knife;
			}
			case BERRIES: {
				Item berries = new Item(itemType, "Berries", "Delicious", Sprite.ICON_HEART);
				return berries;
			}
			case BOOTS: {
				Item boots = new Item(itemType, "Gyllene skor", "Diggiloo diggiley", Sprite.GYLLENE_SKOR);
				return boots;
			}
			case NONE: {
				throw new IllegalArgumentException("Cannot create item of type NONE");
			}
		}
		// TODO: Figure out how to get rid of this.
		return null;
	}


}
