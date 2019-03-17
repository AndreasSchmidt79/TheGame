package inventory.Weapon;

import drawing.TextureFilepath;

public class Sword extends Weapon {

	public Sword(String name, DamageRange damageRange) {
		super(name, damageRange);
		this.textureFilePathEquipped = TextureFilepath.ITEM_SWORD_EQUIP;
		this.textureFilePathInventory = TextureFilepath.ITEM_SWORD_INV;
	}
	
}
