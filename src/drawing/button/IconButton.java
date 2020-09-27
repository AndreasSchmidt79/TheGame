package drawing.button;

import drawing.Position;
import drawing.SpriteElement;
import drawing.TextureFilepath;
import game.UserAction;

public class IconButton extends AbstractButton{

	private static final int SPRITEELEMENT_W_H = 80;

	public IconButton(UserAction action, Position pos, int width, int height, TextureFilepath iconFilepath) {
		super(action, pos, width, height);

		spriteElementDefault =  new SpriteElement(new Position(0,0), SPRITEELEMENT_W_H, SPRITEELEMENT_W_H);
		spriteElementHover = spriteElementDefault;
		spriteElementInactive = spriteElementDefault;
		spriteElementClicked = spriteElementDefault;
		setIconFilePath(iconFilepath);
		setSpriteFilePath(TextureFilepath.UI_SIMPLE_BUTTON);
		setButtonDefault();
	}

}
