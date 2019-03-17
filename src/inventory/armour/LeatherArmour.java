package inventory.armour;

import drawing.TextureFilepath;

public class LeatherArmour extends Armour{

	public LeatherArmour(String name, int armourValue) {
		super(name, armourValue);
		this.textureFilePathEquipped = TextureFilepath.ITEM_ARMOUR_LEATHER_EQUIP;
		this.textureFilePathInventory = TextureFilepath.ITEM_ARMOUR_LEATHER_INV;
	}

}
