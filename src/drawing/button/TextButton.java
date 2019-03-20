package drawing.button;

import drawing.Position;
import drawing.SpriteElement;
import drawing.TextureFilepath;

public class TextButton extends Button {


    private static final int SPRITEELEMENT_WIDTH = 300;
    private static final int SPRITEELEMENT_HEIGHT = 63;
    private static final int BUTTON_TEXT_OFFSET_X = 60;
    private static final int BUTTON_TEXT_OFFSET_Y = 20;

    public static final SpriteElement SPRITEELEMENT_DEFAULT = new SpriteElement(new Position(0,0), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
    public static final SpriteElement SPRITEELEMENT_CLICKED = new SpriteElement(new Position(0,78), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
    public static final SpriteElement SPRITEELEMENT_HOVER = new SpriteElement(new Position(0,156), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
    public static final SpriteElement SPRITEELEMENT_INACTIVE = new SpriteElement(new Position(0,234), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);


    public TextButton(ButtonAction action, Position pos, int width, int height, String text) {
        super(action, pos, width, height, text);
        setSpriteElement(SPRITEELEMENT_DEFAULT);
        setSpriteFilePath(TextureFilepath.UI_BUTTON_SPRITE);
    }


    public int getButtonTextOffsetX() {
        return BUTTON_TEXT_OFFSET_X;
    }

    public int getButtonTextOffsetY() {
        return BUTTON_TEXT_OFFSET_Y;
    }

    public void setButtonClicked() {
        setSpriteElement(SPRITEELEMENT_CLICKED);
    }

    public void setButtonHover() {
        setSpriteElement(SPRITEELEMENT_HOVER);
    }

    public void setButtonInactive() {
        setSpriteElement(SPRITEELEMENT_INACTIVE);
    }

    public void setButtonDefault() {
        setSpriteElement(SPRITEELEMENT_DEFAULT);
    }
}
