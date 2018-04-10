package drawing;

public class SpriteElement {
	
	private Position pos;
	private int width;
	private int height;
	
	public SpriteElement(Position pos, int width, int height) {
		super();
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
