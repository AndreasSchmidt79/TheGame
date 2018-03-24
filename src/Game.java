import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import drawing.Drawing;
import gameMap.*;
import inventory.Weapon.DamageRange;
import inventory.Weapon.Sword;
import inventory.armour.LeatherArmour;
import inventory.armour.SteelHelmet;
import player.Player;


public class Game {
	private static int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle
	private GameMap gameMap = null;
	private MapTile[][] mapTiles = null;
	private Player player;
	private Drawing drawing;
	private long window;
	
	
	public Game(long window, float aspectRatio) {
		initGame(window, aspectRatio);
	}
	
	private void initGame(long window, float aspectRatio){
		this.window = window;
		drawing = new Drawing(MAP_SIZE_IN_TILES, aspectRatio);
		MapGenerator mapGenerator = new MapGenerator();
		gameMap = mapGenerator.getGameMapWithDecoration();				
		mapTiles = gameMap.getMapTiles();
		player = new Player(8,8);
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("ganz gutes Schwert", new DamageRange(1, 2)));
		
	}

	public void updateAll() {
		drawing.drawBackground();
		drawing.drawMap(gameMap, player);
		drawing.drawPlayer(player);			
	}
	
	public void setPlayerDirection(String direction){
		this.player.setDirection(direction);
	}
	
	public void update(String direction){
		if(direction == "left"){
			setPlayerDirection("left");
			if(mapTiles[player.getPosX()-1][player.getPosY()].isPassable()){
				player.setPosX(player.getPosX()-1);				
			}
		}
		if(direction == "right"){
			setPlayerDirection("right");
			if(mapTiles[player.getPosX()+1][player.getPosY()].isPassable()){
				player.setPosX(player.getPosX()+1);
			}
		}
		if(direction == "up"){
			if(mapTiles[player.getPosX()][player.getPosY()+1].isPassable()){
				player.setPosY(player.getPosY()+1);
			}
		}
		if(direction == "down"){
			if(mapTiles[player.getPosX()][player.getPosY()-1].isPassable()){
				player.setPosY(player.getPosY()-1);
			}
		}
		glClear(GL_COLOR_BUFFER_BIT);
		updateAll();
		glfwSwapBuffers(window);
		
	}
	
	
}
