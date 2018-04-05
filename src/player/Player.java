package player;

import drawing.Position;
import inventory.Inventory;

public class Player {
	private int level = 1;
	private Position pos;
	private String direction = "right";
	public Inventory inventory = new Inventory();
	
	public Player(Position pos) {		
		this.pos = pos;
	}
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setPosLeft() {
		this.pos = new Position(pos.getX()-1, pos.getY());
	}
	
	public void setPosRight() {
		this.pos = new Position(pos.getX()+1, pos.getY());
	}
	
	public void setPosUp() {
		this.pos = new Position(pos.getX(), pos.getY() - 1);
	}
	
	public void setPosDown() {
		this.pos = new Position(pos.getX(), pos.getY() + 1);
	}
}
