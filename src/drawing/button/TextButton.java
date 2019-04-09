package drawing.button;

import drawing.Position;
import drawing.SpriteElement;
import drawing.TextureFilepath;
import game.UserAction;

public class TextButton extends AbstractButton {

    private static final int SPRITEELEMENT_WIDTH = 300;
    private static final int SPRITEELEMENT_HEIGHT = 63;

    public TextButton(UserAction action, Position pos, int width, int height, String text) {
        super(action, pos, width, height);

        spriteElementDefault =  new SpriteElement(new Position(0,0), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
        spriteElementClicked = new SpriteElement(new Position(0,78), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
        spriteElementHover = new SpriteElement(new Position(0,156), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
        spriteElementInactive = new SpriteElement(new Position(0,234), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);

        setText(text);
        setSpriteFilePath(TextureFilepath.UI_TEXT_BUTTON);
        setButtonDefault();
    }

}
