package drawing.inventory;

import drawing.Drawing;
import drawing.Position;
import drawing.TextureFilepath;
import player.Player;

public class InventoryDrawing extends Drawing {
	
	public InventoryDrawing(int screenWidth, int screenHeight, int mapSizeInTiles) {
		super(screenWidth, screenHeight, mapSizeInTiles);
	}

	
	public void drawInventory(Player player) {
		drawInventoryFrame();
		drawRectangle(new Position(MAP_PADDING*2, MAP_PADDING*2), 480, 412, textureCache.getTexture(TextureFilepath.INVENTORY.getFilepath()));
	}

	private void drawInventoryFrame() {
		int length = Math.round(screenHeight-MAP_PADDING*2);
		drawRectangle(new Position(MAP_PADDING, MAP_PADDING), length, length, textureCache.getTexture(TextureFilepath.PAPER_FILEPATH.getFilepath()));
		drawRectangle(new Position(MAP_PADDING, MAP_PADDING), length, length, textureCache.getTexture(TextureFilepath.PANEL_FRAME.getFilepath()));
	}
	
}
