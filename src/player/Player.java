package player;

import drawing.Position;
import inventory.Inventory;

public class Player {
	private int level = 1;
	private int experiencePoints = 0;
	private String name = "Player 1";
	public Inventory inventory = new Inventory();
	private int health = 100;
	private int maxHealth = 100;
	private Position pos;
	private String faceDirection = "right";
	private int gold = 5;
	private int strength = 10;
	private int agility = 10;
	private int intelligence = 10;
	private int wisdom = 10;
	private int stamina = 10;

	public Player(Position pos) {		
		this.pos = pos;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public String getFaceDirection() {
		return faceDirection;
	}
	
	public void setFaceDirection(String faceDirection) {
		this.faceDirection = faceDirection;
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

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getGold() {
		return gold;
	}

	public int getStrength() {
		return strength;
	}

	public int getAgility() {
		return agility;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public int getStamina() {
		return stamina;
	}
}
