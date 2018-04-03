package drawing;

public class Button {

	private Position pos;
	private int width;
	private int height;
	
	public Button(Position pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
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
	
}
