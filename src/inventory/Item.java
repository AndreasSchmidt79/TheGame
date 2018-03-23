package inventory;

public class Item {
	
	protected String name;
	protected String textureFilePathInventory;
	
	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getTextureFilePathInventory() {
		return textureFilePathInventory;
	}
		
}
