package drawing.inventory;

import drawing.*;
import drawing.button.Button;
import player.Player;

import java.util.ArrayList;

public class InventoryDrawing {

	private BaseDrawing baseDrawing;
	
	public InventoryDrawing(BaseDrawing baseDrawing) {
		this.baseDrawing = baseDrawing;
	}

	
	public void drawInventory(Player player, ArrayList<Button> activeButtons, TextureCache textureCache) {
		drawInventoryFrame(textureCache);
		baseDrawing.drawRectangle(new Position(Screen.PADDING*2, Screen.PADDING*2), 480, 412, textureCache.getTexture(TextureFilepath.UI_INVENTORY.getFilepath()));
	}

	private void drawInventoryFrame(TextureCache textureCache) {
		int length = Math.round(Screen.HEIGHT-Screen.PADDING*2);
		baseDrawing.drawRectangle(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_PAPER_FILEPATH.getFilepath()));
		baseDrawing.drawRectangle(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_PANEL_FRAME.getFilepath()));
	}
	
}
