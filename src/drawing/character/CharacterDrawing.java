package drawing.character;

import drawing.*;
import drawing.button.AbstractButton;
import drawing.text.TextDrawing;
import helper.PixelRelative;
import player.Player;

import java.util.HashMap;

public class CharacterDrawing {

    private BaseDrawing baseDrawing;
    private TextDrawing textDrawing;

    private int characterPanelWidth;
    private int characterPanelHeight;
    private Position charPanelPos;

    public CharacterDrawing(BaseDrawing baseDrawing, TextDrawing textDrawing) {
        this.baseDrawing = baseDrawing;
        this.textDrawing = textDrawing;
        characterPanelWidth = Screen.WIDTH - Screen.HEIGHT - Screen.PADDING;
        characterPanelHeight = Screen.CHAR_PANEL_HEIGHT - (3 * Screen.PADDING);
        charPanelPos = new Position(Screen.HEIGHT, Screen.PADDING);
    }

    public void drawCharacterPanel(TextureCache textureCache, Player player, HashMap<String, AbstractButton> buttons) {
        drawPanel(textureCache);

        drawPlayerName(player, textureCache);
        drawHealthbar(player, textureCache);

        drawInventoryButton(buttons.get("inventory"), textureCache);
    }

    private void drawInventoryButton(AbstractButton inventoryButton, TextureCache textureCache) {
        Position buttonPosition = charPanelPos.getNewPosWithOffset(Screen.PADDING, 100);
        inventoryButton.setPos(buttonPosition);
        baseDrawing.drawRectangleFromSprite(
                buttonPosition,
                inventoryButton.getWidth(),
                inventoryButton.getHeight(),
                textureCache.getTexture(inventoryButton.getSpriteFilePath().getFilepath()),
                inventoryButton.getCurrentDisplayState()
        );
        baseDrawing.drawRectangle(buttonPosition.getNewPosWithOffset(10, 10), 32, 32, textureCache.getTexture(inventoryButton.getIconFilePath().getFilepath()));
    }

    private void drawPlayerName(Player player, TextureCache textureCache) {
        Position playerNamePos = charPanelPos.getNewPosWithOffset(Screen.PADDING , Screen.PADDING);
        textDrawing.drawText(playerNamePos, player.getName(), 0.6f, characterPanelWidth/2 - Screen.PADDING, 10, false, textureCache);
    }


    private void drawHealthbar(Player player, TextureCache textureCache) {
        Position healthBarPos = charPanelPos.getNewPosWithOffset(Screen.PADDING + characterPanelWidth/2, Screen.PADDING);
        baseDrawing.drawRectangle(
                healthBarPos.getNewPosWithOffset(PixelRelative.getWidth(30,1400), PixelRelative.getHeight(12, 1400*9/16)),
                Math.round(PixelRelative.getWidth(158, 1400) * player.getHealth() / player.getMaxHealth()),
                PixelRelative.getHeight(10,1400*9/16),
                textureCache.getTexture(TextureFilepath.UI_HEALTHBAR_BG.getFilepath())
        );
        baseDrawing.drawRectangle(healthBarPos, PixelRelative.getWidth(192,1400), PixelRelative.getHeight(32, 1400*9/16), textureCache.getTexture(TextureFilepath.UI_HEALTHBAR.getFilepath()));
    }

    private void drawPanel(TextureCache textureCache) {
        baseDrawing.drawRectangle(charPanelPos, characterPanelWidth, characterPanelHeight, textureCache.getTexture(TextureFilepath.UI_PAPER_FILEPATH.getFilepath()));
        baseDrawing.drawRectangle(charPanelPos, characterPanelWidth, characterPanelHeight, textureCache.getTexture(TextureFilepath.UI_PANEL_FRAME.getFilepath()));
    }


}
