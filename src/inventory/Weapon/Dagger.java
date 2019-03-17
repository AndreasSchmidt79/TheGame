package inventory.Weapon;

import drawing.TextureFilepath;

public class Dagger extends Weapon {

	public Dagger(String name, DamageRange damageRange) {
		super(name, damageRange);
		this.textureFilePathEquipped = TextureFilepath.ITEM_DAGGER_EQUIP;
		this.textureFilePathInventory = TextureFilepath.ITEM_DAGGER_INV;
		
	}
	
}
