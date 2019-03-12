import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import drawing.Drawing;
import drawing.Position;
import drawing.button.Button;
import drawing.inventory.InventoryDrawing;
import drawing.map.MapDrawing;
import drawing.text.TextDrawing;
import gameMap.*;
import inventory.Weapon.DamageRange;
import inventory.Weapon.Sword;
import inventory.armour.LeatherArmour;
import inventory.armour.SteelHelmet;
import mob.Mob;
import player.Player;

public class Game {
	public static final int MAP_PADDING = 25;
	private static int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle
	private GameMap currentGameMap;
	private ArrayList<GameMap> gameMaps = new ArrayList<GameMap>();
	//private MapTile[][] mapTiles = null;
	private Player player;
	private Drawing drawing;
	private TextDrawing textDrawing;
	private InventoryDrawing inventoryDrawing;
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
		inventoryDrawing = new InventoryDrawing(width, height, MAP_SIZE_IN_TILES);
		currentGameState = GAME_STATE_MAINMENU;
	}
	
	public void startNewGame() {
		getGameMaps();
		mapDrawing = new MapDrawing(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);
		player = new Player(gameMaps.get(0).getStartPosition());
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("scharfes Schwert", new DamageRange(1, 2)));
		player.setHealth(60);
	
		newGameStarted = true;
	}
	
	private void getGameMaps() {
		GameMap gameMap1 = mapGenerator.getRandomGameMap();
		GameMap randomDungeon = mapGenerator.getRandomDungeonMap();
		gameMap1.addMapPortal(new MapPortal(new Position(13,12),1, randomDungeon.getStartPosition()));
		randomDungeon.addMapPortal(new MapPortal(new Position(10,14), 0, new Position(13,13)));
		gameMaps.add(gameMap1);
		gameMaps.add(randomDungeon);
		
		currentGameMap = gameMaps.get(0);
	}
	
	public void updateAll() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		drawing.drawBackground();
		
		if(currentGameState == GAME_STATE_MAP || newGameStarted) {
			mapDrawing.drawMap(player, currentGameMap);
			mapDrawing.drawPlayer(player);
			textDrawing.drawInfoBox();
			inventoryDrawing.drawInventory(player);
			
			if(!infoText.isEmpty()) {
				textDrawing.drawInfoBoxText(infoText);
			}
		}
		if(currentGameState == GAME_STATE_MAINMENU) {
			drawing.drawSemiTransparentPane();
			textDrawing.drawMainMenu(newGameStarted);
		}
		
		textDrawing.drawFPS(fps);
		glfwSwapBuffers(window);
	}
	
	public void setPlayerDirection(String direction){
		this.player.setDirection(direction);
	}
	
	public void updatePlayerMovement(String direction){
		MapTile[][] mapTiles = currentGameMap.getMapTiles();
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
	}
	
	public void updateEvents() {
		infoText = "";
		MapTile[][] mapTiles = currentGameMap.getMapTiles();
		updateEventMobEncounter(mapTiles);
		updateEventChest(mapTiles);
		updateEventMapPortal(mapTiles);
	}
	
	private void updateEventMobEncounter(MapTile[][] mapTiles) {
		Mob mob = mapTiles[player.getPos().getX()][player.getPos().getY()].getMob();
		if(mob != null) {			
			infoText = "You encountered a " + mob.getName();			
		}
	}
	
	private void updateEventMapPortal(MapTile[][] mapTiles) {
		MapPortal mapPortal = currentGameMap.getPortalAtPos(player.getPos());
		if(mapPortal != null) {
			currentGameMap = gameMaps.get(mapPortal.getTargetMapIndex());
			player.setPos(mapPortal.getTargetPosition());
		} 
	}
	
	private void updateEventChest(MapTile[][] mapTiles) {
		if(mapTiles[player.getPos().getX()][player.getPos().getY()].getDecorationType() == DecorationMapping.DECORATION_CHEST_CLOSED) {
			infoText = "You found a chest!";
		} 
	}
	
	public void updateIntervalSecond() {
		for(Mob mob : currentGameMap.getMobsToMove()) {
			currentGameMap.getMapTileAtPos(mob.getPos()).removeMob();
			mob.moveRandom(currentGameMap);
			currentGameMap.getMapTileAtPos(mob.getPos()).setMob(mob);
		}
		currentGameMap.resetMobsToMove();
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
	
}
