package drawing.uiDrawing;

import com.sun.deploy.util.StringUtils;
import drawing.*;
import drawing.button.Button;
import drawing.text.TextDrawing;
import gameMap.Scaling;

import java.util.ArrayList;

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

    public void drawButtonWithText(Button button, float fontsize, TextureCache textureCache) {
        baseDrawing.drawRectangleFromSprite(button.getPos(), button.getWidth(), button.getHeight(), textureCache.getTexture(button.getSpriteFilePath().getFilepath()), button.getSpriteElement());
        textDrawing.drawText(new Position(button.getPos().getX(), button.getPos().getY() + button.getButtonTextOffsetY()),
                button.getText(),
                fontsize,
                button.getWidth(),
                button.getButtonTextOffsetX(),
                true,
                textureCache
        );
    }

    public void drawMainMenu(ArrayList<Button> activeButtons, TextureCache textureCache) {

        for (int i = 0; i < activeButtons.size(); i++) {
            Button button = activeButtons.get(i);
            int posX = Screen.WIDTH / 2 - Button.MAIN_MENU_BUTTON_WIDTH / 2;
            button.setPos(new Position(posX, 150 + i * (Button.MAIN_MENU_BUTTON_HEIGHT + 20)));
            drawButtonWithText(button, 0.6f, textureCache);
        }

    }

}
