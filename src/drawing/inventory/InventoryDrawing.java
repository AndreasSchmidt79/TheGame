package drawing.inventory;

import drawing.Drawing;
import drawing.Position;
import player.Player;

public class InventoryDrawing extends Drawing {
	
	public InventoryDrawing(int screenWidth, int screenHeight, int mapSizeInTiles) {
		super(screenWidth, screenHeight, mapSizeInTiles);		// 
	}

	
	public void drawInventory(Player player) {
		Position inventoryPos = new Position(screenHeight, MAP_PADDING);
		int inventoryWidth = screenWidth-screenHeight-MAP_PADDING;
		int inventoryHeight =  screenHeight-200-(3*MAP_PADDING);
		
		drawRectangle(inventoryPos, inventoryWidth, inventoryHeight, textureCache.getTexture(PAPER_FILEPATH));
		drawRectangle(inventoryPos, inventoryWidth, inventoryHeight, textureCache.getTexture(MAPFRAME_FILEPATH));
		
		Position healthBarPos = inventoryPos.getNewPosWithOffset(MAP_PADDING, MAP_PADDING);
		
		drawRectangle(healthBarPos.getNewPosWithOffset(30,12), Math.round(158 * player.getHealth() / player.getMaxHealth()), 10, textureCache.getTexture(HEALTHBAR_BG));
		drawRectangle(healthBarPos, 192,32, textureCache.getTexture(HEALTHBAR));
		
		drawRectangle(inventoryPos.getNewPosWithOffset(MAP_PADDING, 20 + 32 + MAP_PADDING), 480, 412, textureCache.getTexture(INVENTORY));
		
		
	}
	
}
