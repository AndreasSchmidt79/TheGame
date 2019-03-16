package mob;

import drawing.Position;
import gameMap.GameMap;
import helper.Direction;
import helper.RandomHelper;

public class Mob {
	
	public static final String SPIDER_FILEPATH = "./res/mobs/spider.png"; 
	public static final String GOBLIN_FILEPATH = "./res/mobs/goblin.png";
	public static final String SKELETON_FILEPATH = "./res/mobs/skeleton.png";
	public static final String SKELETON2_FILEPATH = "./res/mobs/skeleton2.png";
	public static final String WIZARD_FILEPATH = "./res/mobs/wizard.png";
	public static final String BAT_FILEPATH = "./res/mobs/bat.png";
	
	private String name;
	private int hitPoints;
	private int level;
	private Position pos;
	private String filePath;
	
	public Mob(String name, int hitPoints, int level, Position pos, String filePath) {
		this.name = name;
		this.hitPoints = hitPoints;
		this.level = level;
		this.pos = pos;
		this.filePath = filePath;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void moveRandom(GameMap gameMap) {
		Direction direction = RandomHelper.getRandomDirection();
		Position newPos = getNewPos(pos, direction);
		if(isMovementValid(gameMap, newPos)) {
			pos = newPos;
		}
	}
	
	private boolean isMovementValid(GameMap gameMap, Position pos) {
		return gameMap.getMapTileAtPos(pos).isPassable() && gameMap.getMapTileAtPos(pos).getMob() == null;
	}
	
	private Position getNewPos(Position pos, Direction direction) {
		Position newPos = new Position(pos.getX(), pos.getY());
		switch(direction) {
			case LEFT:
				newPos.setX(pos.getX() - 1);
				break;
			case RIGHT:
				newPos.setX(pos.getX() + 1);
				break;
			case UP:
				newPos.setY(pos.getY() - 1);
				break;
			case DOWN:
				newPos.setY(pos.getY() + 1);
				break;
		}
		return newPos;
	}

}
