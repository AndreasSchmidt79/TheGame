package drawing.button;

import drawing.Position;
import drawing.SpriteElement;
import drawing.TextureFilepath;
import game.UserAction;

public class CloseButton extends AbstractButton{

    private static final int SPRITEELEMENT_W_H = 20;

    public CloseButton(UserAction action, Position pos, int width, int height) {
        super(action, pos, width, height);

        spriteElementDefault =  new SpriteElement(new Position(0,0), SPRITEELEMENT_W_H, SPRITEELEMENT_W_H);
        spriteElementHover = new SpriteElement(new Position(0,20), SPRITEELEMENT_W_H, SPRITEELEMENT_W_H);
        spriteElementInactive = new SpriteElement(new Position(0,40), SPRITEELEMENT_W_H, SPRITEELEMENT_W_H);
        spriteElementClicked = spriteElementDefault;

        setSpriteFilePath(TextureFilepath.UI_CLOSE_BUTTON);
        setButtonDefault();
    }

}
