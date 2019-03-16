package game;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import drawing.DrawHandler;
import drawing.Drawing;
import drawing.Position;
import drawing.button.Button;
import drawing.button.ButtonAction;
import drawing.character.CharacterDrawing;
import drawing.inventory.InventoryDrawing;
import drawing.map.MapDrawing;
import drawing.text.TextDrawing;
import gameMap.*;
import helper.Direction;
import inventory.Weapon.DamageRange;
import inventory.Weapon.Sword;
import inventory.armour.LeatherArmour;
import inventory.armour.SteelHelmet;
import mob.Mob;
import player.Player;
import userInteractions.UserInteractions;

public class Game {
	public static final int MAP_PADDING = 25;
	private static int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle
	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private GameMap currentGameMap;
	private ArrayList<GameMap> gameMaps = new ArrayList<>();
	//private MapTile[][] mapTiles = null;
	private Player player;


	//TODO Replace all drawings with DrawHandler
	private DrawHandler drawHandler;
	private Drawing drawing;
	private TextDrawing textDrawing;
	private InventoryDrawing inventoryDrawing;
	private MapDrawing mapDrawing;
	private CharacterDrawing characterDrawing;

	private long window;
	private MapGenerator mapGenerator;

	private UserInteractions userInteractions;
	
	public GameState currentGameState;
	public DisplayState currentDisplayState;
	private String infoText = "";
	
	private int fps = 0;
	private boolean newGameStarted = false;
	
	public Game(int width, int height, long window) {
		this.window = window;
		this.SCREEN_WIDTH = width;
		this.SCREEN_HEIGHT = height;
		mapGenerator = new MapGenerator();

		drawHandler = new DrawHandler(width, height, MAP_SIZE_IN_TILES);

		drawing = new Drawing(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);
		mapDrawing = new MapDrawing(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);
		textDrawing = new TextDrawing(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);
		inventoryDrawing = new InventoryDrawing(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);
		characterDrawing = new CharacterDrawing(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, MAP_SIZE_IN_TILES);

		currentGameState = GameState.MAINMENU;
		userInteractions = new UserInteractions();
	}
	
	public void startNewGame() {
		getGameMaps();

		player = new Player(gameMaps.get(0).getStartPosition());
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("scharfes Schwert", new DamageRange(1, 2)));
		player.setHealth(60);
	
		newGameStarted = true;
		currentDisplayState = DisplayState.MAP;
	}
	
	private void getGameMaps() {
		gameMaps = new ArrayList<>();
		GameMap gameMap1 = mapGenerator.getRandomGameMap();
		GameMap randomDungeon = mapGenerator.getRandomDungeonMap();
		gameMap1.addMapPortal(new MapPortal(new Position(13,12),1, randomDungeon.getStartPosition()));
		randomDungeon.addMapPortal(new MapPortal(new Position(10,14), 0, new Position(13,13)));
		gameMaps.add(gameMap1);
		gameMaps.add(randomDungeon);
		
		currentGameMap = gameMaps.get(0);
	}
	
	public void updateAll() {

        if(currentGameState == GameState.MAINMENU) {
            ButtonAction buttonPressed = userInteractions.getButtonPressed(getActiveButtons());
            if(buttonPressed != null) {
                switch (buttonPressed) {
                    case CONTINUE:
                        setCurrentGameState(GameState.GAME);
                        break;
                    case NEW_GAME:
                        startNewGame();
                        setCurrentGameState(GameState.GAME);
                        break;
                    case EXIT:
                        glfwTerminate();
                        break;
                    default:
                        break;
                }
            }
        }
        else if(currentGameState == GameState.GAME ) {
            Direction playerMovement = userInteractions.getPlayerMovement();
            if(playerMovement != null) {
                updatePlayerMovement(playerMovement);
                updateEvents();
            }
            if(userInteractions.isEscapeKeyPressed()){
                setCurrentGameState(GameState.MAINMENU);
            }
        }


        //TODO START drawHandler.drawAll()
		glClear(GL_COLOR_BUFFER_BIT);
		
		drawing.drawBackground();

		if(newGameStarted){
			if(currentDisplayState == DisplayState.MAP) {
				mapDrawing.drawMapPanel(player, currentGameMap);
				mapDrawing.drawPlayer(player);
			}
			if(currentDisplayState == DisplayState.INVENTORY) {
				inventoryDrawing.drawInventory(player);
			}

			characterDrawing.drawCharacterPanel(player);
			textDrawing.drawInfoPanel();

			if(!infoText.isEmpty()) {
				textDrawing.drawInfoPanelText(infoText);
			}
		}
		if(currentGameState == GameState.MAINMENU) {
			drawing.drawSemiTransparentPane();
			textDrawing.drawMainMenu(newGameStarted);
		}
		
		textDrawing.drawFPS(fps);
		glfwSwapBuffers(window);
        //TODO END drawHandler.getButtonPressed()
	}
	
	private void setPlayerDirection(String direction){
		player.setFaceDirection(direction);
	}
	
	private void updatePlayerMovement(Direction direction){
		MapTile[][] mapTiles = currentGameMap.getMapTiles();
		if(direction == Direction.LEFT){
			setPlayerDirection("left");
			if(mapTiles[player.getPos().getX()-1][player.getPos().getY()].isPassable()){
				player.setPosLeft();			
			}
		}
		if(direction == Direction.RIGHT){
			setPlayerDirection("right");
			if(mapTiles[player.getPos().getX()+1][player.getPos().getY()].isPassable()){
				player.setPosRight();
			}
		}
		if(direction == Direction.UP){
			if(mapTiles[player.getPos().getX()][player.getPos().getY()-1].isPassable()){
				player.setPosUp();
			}
		}
		if(direction == Direction.DOWN){
			if(mapTiles[player.getPos().getX()][player.getPos().getY()+1].isPassable()){
				player.setPosDown();
			}
		}
	}
	
	private void updateEvents() {
		infoText = "";
		MapTile[][] mapTiles = currentGameMap.getMapTiles();
		updateEventMobEncounter(mapTiles);
		updateEventChest(mapTiles);
		updateEventMapPortal();
	}
	
	private void updateEventMobEncounter(MapTile[][] mapTiles) {
		Mob mob = mapTiles[player.getPos().getX()][player.getPos().getY()].getMob();
		if(mob != null) {			
			infoText = "You encountered a " + mob.getName();
			player.setExperiencePoints(player.getExperiencePoints()+1);
		}
	}
	
	private void updateEventMapPortal() {
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
		if(currentDisplayState == DisplayState.MAP) {
			for (Mob mob : currentGameMap.getMobsToMove()) {
				currentGameMap.getMapTileAtPos(mob.getPos()).removeMob();
				mob.moveRandom(currentGameMap);
				currentGameMap.getMapTileAtPos(mob.getPos()).setMob(mob);
			}
			currentGameMap.resetMobsToMove();
		}
	}
	
	public void  setFps(int fps) {
		this.fps = fps;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	private void setCurrentGameState(GameState currentGameState) {
		clearActiveButtons();
		this.currentGameState = currentGameState;
	}
	
	private ArrayList<Button> getActiveButtons() {
		return textDrawing.getActiveButtons();
	}
	
	private void clearActiveButtons() {
		textDrawing.clearActiveButtons();
	}
	
}
