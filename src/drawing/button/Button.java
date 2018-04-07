package drawing.button;

import drawing.Position;
import drawing.SpriteElement;

public class Button {

	public static final int NEW_GAME = 0;
	public static final int CONTINUE = 1;
	public static final int EXIT = 2;
	
	public static final String BUTTON_SPRITE = "./res/UI/button_sprite.png";
	public static final String BUTTON_FILEPATH = "./res/UI/button_300_63.png";
	public static final String BUTTON_HOVER_FILEPATH = "./res/UI/button_hover_300_63.png";
	public static final String BUTTON_CLICKED_FILEPATH = "./res/UI/button_clicked_300_63.png";
	private static final int SPRITEELEMENT_WIDTH = 300;
	private static final int SPRITEELEMENT_HEIGHT = 63;
	private static final int BUTTON_TEXT_OFFSET_X = 60;
	private static final int BUTTON_TEXT_OFFSET_Y = 20;
	
	public static final SpriteElement SPRITEELEMENT_DEFAULT = new SpriteElement(new Position(0,0), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
	public static final SpriteElement SPRITEELEMENT_CLICKED = new SpriteElement(new Position(0,78), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT); 
	public static final SpriteElement SPRITEELEMENT_HOVER = new SpriteElement(new Position(0,156), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);;
	public static final SpriteElement SPRITEELEMENT_INACTIVE = new SpriteElement(new Position(0,234), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);;
	
	private int action;
	private Position pos;
	private int width;
	private int height;
	private String text;
	private SpriteElement spriteElement;
	
	public Button(int action, Position pos, int width, int height, String text) {
		this.action = action;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.text = text;
		this.spriteElement = SPRITEELEMENT_DEFAULT;
	}

	public int getAction() {
		return action;
	}

	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean positionIsInButtonRange(Position clickedPos) {
		if(clickedPos != null && pos != null && width >= 0  && height >= 0) {
			if(clickedPos.getX() > pos.getX() && clickedPos.getX() < pos.getX() + width &&
			   clickedPos.getY() > pos.getY() && clickedPos.getY() < pos.getY() + height) {
				return true;
			}
		}
		return false;
	}

	public String getSpriteFilePath() {
		return BUTTON_SPRITE;
	}

	public String getText() {
		return text;
	}
	
	public void setButtonClicked() {
		this.spriteElement = SPRITEELEMENT_CLICKED;
	}
	
	public void setButtonHover() {
		this.spriteElement = SPRITEELEMENT_HOVER;
	}
	
	public void setButtonInactive() {
		this.spriteElement = SPRITEELEMENT_INACTIVE;
	}
	
	public void setButtonDefault() {
		this.spriteElement = SPRITEELEMENT_DEFAULT;
	}

	public SpriteElement getSpriteElement() {
		return spriteElement;
	}

	public int getButtonTextOffsetX() {
		return BUTTON_TEXT_OFFSET_X;
	}

	public int getButtonTextOffsetY() {
		return BUTTON_TEXT_OFFSET_Y;
	}
	
}
