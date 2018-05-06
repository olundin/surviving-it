package survivingit.items;

import survivingit.graphics.Sprite;

/**
 * Factory class for Items which creates ItemObjects entered ItemTypes (item IDs), adds the right components to that
 * specific ItemType and then returns it.
 *
 * @see Item
 */
public final class ItemFactory {

	/**
	 * Flyweight pointer for NONE_ITEM used for empty item slots. Is stored as a public field to save memory usage.
	 */
	public final static Item NONE_ITEM = new Item(ItemType.NONE, Sprite.CAMPFIRE);

	private ItemFactory() {}

	/**
	 * Creates and returns a new Item object of the entered ItemType.
	 * @param itemType ItemType enum value of which type of item to be created.
	 * @return a new Item object of the entered ItemType.
	 */
    public static Item createItem(ItemType itemType) {
		switch (itemType) {
			case KNIFE:
				Item knife = new Item(itemType, Sprite.KNIFE);
				knife.addEffect(new MeleeAttackEffect(10, 1));
				return knife;
			case SPEAR:
				Item spear = new Item(itemType, Sprite.SPEAR);
				spear.addEffect(new MeleeAttackEffect(4, 5));
				return spear;
			case BERRY:
				Item berries = new Item(itemType, Sprite.BERRIES);
				berries.addEffect(new HealEffect(10, 100));
				return berries;

			case BOOTS:
				Item boots = new Item(itemType, Sprite.GYLLENE_SKOR);
				boots.addEffect(new MoveSpeedChangeEffect(5, 1));
				return boots;

			case FLINT_AND_STEEL:
				Item flintAndSteel = new Item(itemType, Sprite.FLINT_AND_STEEL);
				flintAndSteel.addEffect(new IgniteEffect(100, 10, 4, 4));
				return flintAndSteel;

			case NONE:
				throw new IllegalArgumentException("Cannot create item of type NONE");
		}
		// Some unknown enum type.
		return null;
	}


}