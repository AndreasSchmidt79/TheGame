package drawing.button;

import drawing.Position;
import drawing.SpriteElement;

public class Button {

	private static final int SPRITEELEMENT_WIDTH = 300;
	private static final int SPRITEELEMENT_HEIGHT = 63;
	private static final int BUTTON_TEXT_OFFSET_X = 60;
	private static final int BUTTON_TEXT_OFFSET_Y = 20;
	
	public static final SpriteElement SPRITEELEMENT_DEFAULT = new SpriteElement(new Position(0,0), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);
	public static final SpriteElement SPRITEELEMENT_CLICKED = new SpriteElement(new Position(0,78), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT); 
	public static final SpriteElement SPRITEELEMENT_HOVER = new SpriteElement(new Position(0,156), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);;
	public static final SpriteElement SPRITEELEMENT_INACTIVE = new SpriteElement(new Position(0,234), SPRITEELEMENT_WIDTH, SPRITEELEMENT_HEIGHT);;
	
	private ButtonAction action;
	private Position pos;
	private int width;
	private int height;
	private String text;
	private SpriteElement spriteElement;
	
	public Button(ButtonAction action, Position pos, int width, int height, String text) {
		this.action = action;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.text = text;
		this.spriteElement = SPRITEELEMENT_DEFAULT;
	}

	public ButtonAction getAction() {
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
