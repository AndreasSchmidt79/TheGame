package drawing.inventory;

import drawing.*;
import drawing.button.AbstractButton;
import player.Player;

import java.util.HashMap;

public class InventoryDrawing {

    private BaseDrawing baseDrawing;

    public InventoryDrawing(BaseDrawing baseDrawing) {
        this.baseDrawing = baseDrawing;
    }


    public void drawInventory(TextureCache textureCache, Player player, HashMap<String, AbstractButton> buttons) {
        drawInventoryFrame(textureCache, buttons.get("closeinv"));
        baseDrawing.drawRectangle(new Position(Screen.PADDING * 2, Screen.PADDING * 2), 480, 412, textureCache.getTexture(TextureFilepath.UI_INVENTORY.getFilepath()));
    }

    private void drawInventoryFrame(TextureCache textureCache, AbstractButton closeButton) {
        int length = Math.round(Screen.HEIGHT - Screen.PADDING * 2);
        baseDrawing.drawRectangle(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_PAPER_FILEPATH.getFilepath()));
        baseDrawing.drawRectangle(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_PANEL_FRAME.getFilepath()));

        Position buttonPos = new Position(length + Screen.PADDING - closeButton.getWidth(), Screen.PADDING);
        closeButton.setPos(buttonPos);
        baseDrawing.drawRectangleFromSprite(
                buttonPos,
                closeButton.getWidth(),
                closeButton.getHeight(),
                textureCache.getTexture(closeButton.getSpriteFilePath().getFilepath()),
                closeButton.getCurrentDisplayState()
        );
    }


}
