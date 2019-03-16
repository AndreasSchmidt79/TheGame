package inventory;

public class EquippableItem extends Item {
	
	protected EquipSlot equipSlot;
	protected String textureFilePathCharacter;

	public EquippableItem(String name) {
		super(name);	
	}

	public String getTextureFilePathCharacter() {
		return textureFilePathCharacter;
	}
		
}
