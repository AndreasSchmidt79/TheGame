package inventory.Weapon;

public class Dagger extends Weapon {

	public Dagger(String name, DamageRange damageRange) {
		super(name, damageRange);
		this.textureFilePathCharacter = "./res/Items/weapons/dagger.png";
		this.textureFilePathInventory = "./res/UI/icons/DaggerIcon.png";
		
	}
	
}
