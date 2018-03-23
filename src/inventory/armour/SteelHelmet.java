package inventory.armour;

public class SteelHelmet extends Armour{

	public SteelHelmet(String name, int armourValue) {
		super(name, armourValue);
		this.textureFilePathCharacter = "./res/Items/armour/SteelHelm.png";
		this.textureFilePathInventory = "./res/UI/icons/SteelHelmIcon.png";
	}

}
