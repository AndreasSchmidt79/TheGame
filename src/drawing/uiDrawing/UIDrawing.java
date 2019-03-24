package drawing.uiDrawing;

import drawing.*;
import drawing.button.AbstractButton;
import drawing.text.TextDrawing;
import game.GameState;
import helper.PixelRelative;

import java.util.ArrayList;
import java.util.HashMap;

public class UIDrawing {

    private BaseDrawing baseDrawing;
    private TextDrawing textDrawing;

    public UIDrawing(BaseDrawing baseDrawing, TextDrawing textDrawing) {
        this.baseDrawing = baseDrawing;
        this.textDrawing = textDrawing;
    }

    public void drawBackground(TextureCache textureCache) {
        baseDrawing.drawRectangle(new Position(0, 0), Screen.WIDTH, Screen.HEIGHT, textureCache.getTexture(TextureFilepath.UI_BACKGROUND.getFilepath()));
    }

    public void drawSemiTransparentPane(TextureCache textureCache) {
        baseDrawing.drawRectangle(new Position(0, 0), Screen.WIDTH, Screen.HEIGHT, textureCache.getTexture(TextureFilepath.UI_TRANSPARENT_PANE.getFilepath()));
    }

    public void drawInfoPanel(TextureCache textureCache) {
        int infoPanelHeight = 200;
        baseDrawing.drawRectangle(
                new Position(Screen.HEIGHT, Screen.HEIGHT - infoPanelHeight - Screen.PADDING),
                Screen.WIDTH - Screen.HEIGHT - Screen.PADDING,
                infoPanelHeight,
                textureCache.getTexture(TextureFilepath.UI_TEXT_PANEL.getFilepath())
        );
    }

    public void drawButtonWithText(AbstractButton button, float fontsize, TextureCache textureCache) {
        baseDrawing.drawRectangleFromSprite(button.getPos(), button.getWidth(), button.getHeight(), textureCache.getTexture(button.getSpriteFilePath().getFilepath()), button.getCurrentDisplayState());
        textDrawing.drawText(new Position(button.getPos().getX(), button.getPos().getY() + button.getButtonTextOffsetY()),
                button.getText(),
                fontsize,
                button.getWidth(),
                button.getButtonTextOffsetX(),
                true,
                textureCache
        );
    }

    public void drawMainMenu(TextureCache textureCache, GameState currentGameState, HashMap<String, AbstractButton> buttons) {

        int posX = Screen.WIDTH / 2 - AbstractButton.MAIN_MENU_BUTTON_WIDTH / 2;
        int buttonDistance = AbstractButton.MAIN_MENU_BUTTON_HEIGHT + Screen.PADDING;


        ArrayList<AbstractButton> mainMenuButtons = new ArrayList<>();

        if (currentGameState.getDisplayState() != null) {
            mainMenuButtons.add(buttons.get("continue"));
        }
        mainMenuButtons.add(buttons.get("newgame"));
        mainMenuButtons.add(buttons.get("exit"));


        for (int i = 0; i < mainMenuButtons.size(); i++) {
            AbstractButton button = mainMenuButtons.get(i);
            int posY = PixelRelative.getWidth(150, 1400) + i * buttonDistance;
            button.setPos(new Position(posX, posY));
            drawButtonWithText(button, 0.6f, textureCache);
        }
    }

}
