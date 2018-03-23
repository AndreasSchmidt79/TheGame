package inventory.Weapon;

public class Sword extends Weapon {

	public Sword(String name, DamageRange damageRange) {
		super(name, damageRange);
		this.textureFilePathCharacter = "./res/Items/weapons/sword.png";
		this.textureFilePathInventory = "./res/UI/icons/SwordIcon.png";
	}
	
}
