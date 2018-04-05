package drawing;

public class Button {

	public static final int NEW_GAME = 0;
	public static final int CONTINUE = 1;
	public static final int EXIT = 2;
	private int action;
	private Position pos;
	private int width;
	private int height;
	
	public Button(int action, Position pos, int width, int height) {
		this.action = action;
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public int getAction() {
		return action;
	}

	public Position getPos() {
		return pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean positionIsInButtonRange(Position clickedPos) {
		if(clickedPos.getX() > pos.getX() && clickedPos.getX() < pos.getX() + width &&
		   clickedPos.getY() > pos.getY() && clickedPos.getY() < pos.getY() + height) {
			return true;
		}
		return false;
	}
	
}
