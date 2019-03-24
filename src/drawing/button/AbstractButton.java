package drawing.button;

import drawing.Position;
import drawing.SpriteElement;
import drawing.TextureFilepath;
import helper.PixelRelative;

public abstract class AbstractButton {

    public static final int MAIN_MENU_BUTTON_WIDTH = PixelRelative.getWidth(400,1400);
    public static final int MAIN_MENU_BUTTON_HEIGHT = PixelRelative.getWidth(80,1400);
    private static final int BUTTON_TEXT_OFFSET_X = 60;
    private static final int BUTTON_TEXT_OFFSET_Y = 20;

    private TextureFilepath spriteFilePath;
    private ButtonAction action;
    private Position pos;
    private int width;
    private int height;
    private String text;
    private TextureFilepath iconFilePath;
    private SpriteElement currentDisplayState;

    protected SpriteElement spriteElementDefault;
    protected SpriteElement spriteElementHover;
    protected SpriteElement spriteElementInactive;
    protected SpriteElement spriteElementClicked;

    public AbstractButton(ButtonAction action, Position pos, int width, int height) {
        this.action = action;
        this.pos = pos;
        this.width = width;
        this.height = height;
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


    public void setButtonClicked() {
        this.currentDisplayState = spriteElementClicked;
    }

    public void setButtonHover() {
        this.currentDisplayState = spriteElementHover;
    }

    public void setButtonInactive() {
        this.currentDisplayState = spriteElementInactive;
    }

    public void setButtonDefault() {
        this.currentDisplayState = spriteElementDefault;
    }

    public ButtonAction getAction() {
        return action;
    }

    public void setAction(ButtonAction action) {
        this.action = action;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextureFilepath getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(TextureFilepath iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public TextureFilepath getSpriteFilePath() {
        return spriteFilePath;
    }

    public void setSpriteFilePath(TextureFilepath spriteFilePath) {
        this.spriteFilePath = spriteFilePath;
    }


    public int getButtonTextOffsetX() {
        return BUTTON_TEXT_OFFSET_X;
    }

    public int getButtonTextOffsetY() {
        return BUTTON_TEXT_OFFSET_Y;
    }

    public SpriteElement getCurrentDisplayState() {
        return currentDisplayState;
    }
}
