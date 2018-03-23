package Player;

import java.util.ArrayList;

import inventory.Inventory;

public class Player {
	private int level = 1;
	private int posX;
	private int posY;
	private String direction = "right";
	public Inventory inventory = new Inventory();
	
	public Player(int posX, int posY) {		
		this.posX = posX;
		this.posY = posY;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
