package inventory.armour;

public class LeatherArmour extends Armour{

	public LeatherArmour(String name, int armourValue) {
		super(name, armourValue);
		this.textureFilePathCharacter = "./res/Items/armour/LeatherArmor.png";
		this.textureFilePathInventory = "./res/Items/armour/LeatherArmor.png";
	}

}
