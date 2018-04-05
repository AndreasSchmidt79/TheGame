import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import drawing.Button;
import drawing.Drawing;
import drawing.Position;
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
	private MapGenerator mapGenerator;
	
	public int GAME_STATE_MAINMENU = 1; 
	public int GAME_STATE_MAP = 2;
	public int GAME_STATE_COMBAT = 3;
	public int currentGameState = GAME_STATE_MAP;
	private String infoText = "";
	
	private int fps = 0;
	private boolean newGameStarted = false;
	
	public Game(int width, int height, long window) {
		this.window = window;
		mapGenerator = new MapGenerator();
		drawing = new Drawing(width, height, MAP_SIZE_IN_TILES);
		textDrawing = new TextDrawing(width, height, MAP_SIZE_IN_TILES);
		currentGameState = GAME_STATE_MAINMENU;
	}
	
	public void startNewGame() {
		//gameMap = mapGenerator.getRandomGameMap();
		gameMap = mapGenerator.getRandomDungeonMap();
		
		mapTiles = gameMap.getMapTiles();
		mapDrawing = new MapDrawing(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, MAP_SIZE_IN_TILES, gameMap);
		player = new Player(gameMap.getStartPosition());
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("scharfes Schwert", new DamageRange(1, 2)));
		
		newGameStarted = true;
	}
	
	public void updateAll() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		drawing.drawBackground();
		
		if(currentGameState == GAME_STATE_MAP || newGameStarted) {
			mapDrawing.drawMap(player);
			mapDrawing.drawPlayer(player);
			if(!infoText.isEmpty()) {
				textDrawing.drawInfoText(infoText);
			}
		}
		if(currentGameState == GAME_STATE_MAINMENU) {
			textDrawing.drawMainMenu(newGameStarted);
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
			if(mapTiles[player.getPos().getX()-1][player.getPos().getY()].isPassable()){
				player.setPosLeft();			
			}
		}
		if(direction == "right"){
			setPlayerDirection("right");
			if(mapTiles[player.getPos().getX()+1][player.getPos().getY()].isPassable()){
				player.setPosRight();
			}
		}
		if(direction == "up"){
			if(mapTiles[player.getPos().getX()][player.getPos().getY()-1].isPassable()){
				player.setPosUp();
			}
		}
		if(direction == "down"){
			if(mapTiles[player.getPos().getX()][player.getPos().getY()+1].isPassable()){
				player.setPosDown();
			}
		}
		if(mapTiles[player.getPos().getX()][player.getPos().getY()].getDecorationType() == DecorationMapping.DECORATION_CHEST_CLOSED) {
			infoText = "You found a chest!";
		}else {
			infoText = "";
		}
	}
	
	public void  setFps(int fps) {
		this.fps = fps;
	}

	public int getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(int currentGameState) {
		clearActiveButtons();
		this.currentGameState = currentGameState;
	}
	
	public ArrayList<Button> getActiveButtons() {
		return textDrawing.getActiveButtons();
	}
	
	public void clearActiveButtons() {
		textDrawing.clearActiveButtons();
	}
	
	public void triggerMousePos(Position pos) {
		
	}
	
}
