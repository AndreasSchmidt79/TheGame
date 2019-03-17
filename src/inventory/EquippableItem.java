package inventory;

import drawing.TextureFilepath;

public class EquippableItem extends Item {
	
	protected EquipSlot equipSlot;
	protected TextureFilepath textureFilePathEquipped;

	public EquippableItem(String name) {
		super(name);	
	}

	public TextureFilepath getTextureFilePathEquipped() {
		return textureFilePathEquipped;
	}
		
}
