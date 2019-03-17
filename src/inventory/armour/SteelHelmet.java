package inventory.armour;

import drawing.TextureFilepath;

public class SteelHelmet extends Armour{

	public SteelHelmet(String name, int armourValue) {
		super(name, armourValue);
		this.textureFilePathEquipped = TextureFilepath.ITEM_STEEL_HELMET_EQUIP;
		this.textureFilePathInventory = TextureFilepath.ITEM_STEEL_HELMET_INV;
	}

}
