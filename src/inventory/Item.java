package inventory;

import drawing.TextureFilepath;

public class Item {
	
	protected String name;
	protected TextureFilepath textureFilePathInventory;
	
	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public TextureFilepath getTextureFilePathInventory() {
		return textureFilePathInventory;
	}
		
}
