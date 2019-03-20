package drawing.character;

import drawing.*;
import drawing.button.Button;
import drawing.button.ButtonAction;
import player.Player;

import java.util.ArrayList;

public class CharacterDrawing {

    private BaseDrawing baseDrawing;

    public CharacterDrawing(BaseDrawing baseDrawing) {
        this.baseDrawing = baseDrawing;
    }

    public void drawCharacterPanel(Player player, ArrayList<Button> activeButtons, TextureCache textureCache) {
        Position charPanelPos = new Position(Screen.HEIGHT, Screen.PADDING);
        drawPanel(charPanelPos, textureCache);

        drawPlayerName(player, textureCache);
        drawHealthbar(player, charPanelPos, textureCache);
        //TODO Buttons zentral deklarieren, activeButtons als Hashmap mit Name -> True/False
        if(activeButtons.size()>0) {
            drawInventoryButton(charPanelPos, activeButtons.get(0), textureCache);
        }
    }

    private void drawInventoryButton(Position charPanelPos, Button inventoryButton, TextureCache textureCache) {
        Position buttonPosition = charPanelPos.getNewPosWithOffset(Screen.PADDING, 100);
        inventoryButton.setPos(buttonPosition);
        baseDrawing.drawRectangleFromSprite(
                buttonPosition,
                inventoryButton.getWidth(),
                inventoryButton.getHeight(),
                textureCache.getTexture(inventoryButton.getSpriteFilePath().getFilepath()),
                inventoryButton.getSpriteElement()
        );
    }

    private void drawPlayerName(Player player, TextureCache textureCache) {
        //TBD
    }


    private void drawHealthbar(Player player, Position charPanelPos, TextureCache textureCache) {
        Position healthBarPos = charPanelPos.getNewPosWithOffset(Screen.PADDING, Screen.PADDING);
        baseDrawing.drawRectangle(
                healthBarPos.getNewPosWithOffset(30,12),
                Math.round(158 * player.getHealth() / player.getMaxHealth()),
                10,
                textureCache.getTexture(TextureFilepath.UI_HEALTHBAR_BG.getFilepath())
        );
        baseDrawing.drawRectangle(healthBarPos, 192,32, textureCache.getTexture(TextureFilepath.UI_HEALTHBAR.getFilepath()));
    }

    private void drawPanel(Position charPanelPos, TextureCache textureCache) {
        int inventoryWidth = Screen.WIDTH-Screen.HEIGHT-Screen.PADDING;
        int inventoryHeight =  Screen.HEIGHT-200-(3*Screen.PADDING);

        baseDrawing.drawRectangle(charPanelPos, inventoryWidth, inventoryHeight, textureCache.getTexture(TextureFilepath.UI_PAPER_FILEPATH.getFilepath()));
        baseDrawing.drawRectangle(charPanelPos, inventoryWidth, inventoryHeight, textureCache.getTexture(TextureFilepath.UI_PANEL_FRAME.getFilepath()));
    }


}
