package drawing;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "x: " + this.x + ", y: " + this.y;
	}
	
	public Position getNewPosWithOffset(int x, int y) {
		return new Position(getX() + x, getY() + y);
	}
	
}
