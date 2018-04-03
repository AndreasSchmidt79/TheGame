import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import drawing.Drawing;
import drawing.map.MapDrawing;
import drawing.text.TextDrawing;
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
	private TextDrawing textDrawing;
	private MapDrawing mapDrawing;
	private long window;
	
	public int GAME_STATE_MAINMENU = 1; 
	public int GAME_STATE_MAP = 2;
	public int GAME_STATE_COMBAT = 3;
	
	public int currentGameState = GAME_STATE_MAP;
	private int fps = 0;
	
	
	public Game(int width, int height, long window) {
		this.window = window;
		MapGenerator mapGenerator = new MapGenerator();
		gameMap = mapGenerator.getRandomGameMap();
		drawing = new Drawing(width, height, MAP_SIZE_IN_TILES);
		textDrawing = new TextDrawing(width, height, MAP_SIZE_IN_TILES);
		mapDrawing = new MapDrawing(width, height, MAP_SIZE_IN_TILES, gameMap);
						
		mapTiles = gameMap.getMapTiles();
		player = new Player(8,8);
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("ganz gutes Schwert", new DamageRange(1, 2)));
	}
	
	public void updateAll() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		drawing.drawBackground();
		
		if(currentGameState == GAME_STATE_MAP) {
			mapDrawing.drawMap(player);
			mapDrawing.drawPlayer(player);
		}
		else if(currentGameState == GAME_STATE_MAINMENU) {
			textDrawing.drawMainMenu();
		}
		
		textDrawing.drawFPS(fps);
		
		glfwSwapBuffers(window);
	}
	
	public void setPlayerDirection(String direction){
		this.player.setDirection(direction);
	}
	
	public void updatePlayerMovement(String direction){
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
			if(mapTiles[player.getPosX()][player.getPosY()-1].isPassable()){
				player.setPosY(player.getPosY()-1);
			}
		}
		if(direction == "down"){
			if(mapTiles[player.getPosX()][player.getPosY()+1].isPassable()){
				player.setPosY(player.getPosY()+1);
			}
		}
	}
	
	public void  setFps(int fps) {
		this.fps = fps;
	}
	
	
}
