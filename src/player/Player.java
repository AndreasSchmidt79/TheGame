package player;

import drawing.Position;
import inventory.Inventory;

public class Player {
	private int level = 1;
	private Position pos;
	private String direction = "right";
	public Inventory inventory = new Inventory();
	private int health = 100;
	private int maxHealth = 100;
	
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
	
	
	public int getLevel() {
		return level;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void setHealth(int health) {
		this.health = health;
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
