package game;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

import java.util.ArrayList;

import drawing.DrawHandler;

import drawing.Position;
import drawing.TextureFilepath;
import drawing.button.Button;
import drawing.button.ButtonAction;

import drawing.button.TextButton;
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
	private static int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle

	private GameMap currentGameMap;
	private ArrayList<GameMap> gameMaps = new ArrayList<>();
	private Player player;

	private DrawHandler drawHandler;

	private MapGenerator mapGenerator;

	private UserInteractions userInteractions;
	
	public GameState currentGameState;
	public DisplayState currentDisplayState;
	private String infoText = "";
	private ArrayList<Button> activeButtons = new ArrayList<>();
	
	private int fps = 0;
	private boolean newGameStarted = false;
	
	public Game(long window) {
		mapGenerator = new MapGenerator();

		drawHandler = new DrawHandler(MAP_SIZE_IN_TILES, window);

		currentGameState = GameState.MAINMENU;
		userInteractions = new UserInteractions();
		calculateActiveButtons();
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
						calculateActiveButtons();
                        break;
                    case NEW_GAME:
                        startNewGame();
                        setCurrentGameState(GameState.GAME);
                        setCurrentDisplayState(DisplayState.MAP);
						calculateActiveButtons();
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
				calculateActiveButtons();
            }
			ButtonAction buttonPressed = userInteractions.getButtonPressed(getActiveButtons());
			if(buttonPressed != null) {
				switch (buttonPressed) {
					case INVENTORY:
						setCurrentDisplayState(DisplayState.INVENTORY);
						calculateActiveButtons();
						break;
					case CLOSE_INVENTORY:
						setCurrentDisplayState(DisplayState.MAP);
						calculateActiveButtons();
						break;
					default:
						break;
				}
			}
        }

        drawHandler.drawAll(player, currentGameMap, currentGameState, currentDisplayState, fps, activeButtons, infoText, newGameStarted);
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
		if(currentDisplayState == DisplayState.MAP && currentGameState == GameState.GAME) {
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

	private void setCurrentGameState(GameState currentGameState) {
		this.currentGameState = currentGameState;
	}

	public void setCurrentDisplayState(DisplayState currentDisplayState) {
		this.currentDisplayState = currentDisplayState;
	}

	public ArrayList<Button> getActiveButtons() {
		return activeButtons;
	}

	public void clearActiveButtons() {
		activeButtons = new ArrayList<>();
	}


	private void calculateActiveButtons() {
		ArrayList<Button> buttons = new ArrayList<>();
		if (currentGameState == GameState.MAINMENU) {

			if (newGameStarted) {
				buttons.add(new TextButton(ButtonAction.CONTINUE, new Position(0, 0), Button.MAIN_MENU_BUTTON_WIDTH, Button.MAIN_MENU_BUTTON_HEIGHT, "Continue"));
			}

			buttons.add(new TextButton(ButtonAction.NEW_GAME, new Position(0, 0), Button.MAIN_MENU_BUTTON_WIDTH, Button.MAIN_MENU_BUTTON_HEIGHT, "New game"));
			buttons.add(new TextButton(ButtonAction.EXIT, new Position(0, 0), Button.MAIN_MENU_BUTTON_WIDTH, Button.MAIN_MENU_BUTTON_HEIGHT, "Exit"));
		}
		else if(currentGameState == GameState.GAME){
			if(currentDisplayState == DisplayState.MAP){
				buttons.add(new Button(ButtonAction.INVENTORY, new Position(0, 0), 50, 50, TextureFilepath.UI_ICON_EQUIPMENT));
			}
			else if(currentDisplayState == DisplayState.INVENTORY){
				//buttons.add(new Button(ButtonAction.CLOSE_INVENTORY, new Position(0, 0), 50, 50, TextureFilepath.UI_HEALTHBAR_BG));
			}
			else if (currentDisplayState == DisplayState.COMBAT){
				//TBD
			}
		}

		activeButtons = buttons;
	}


}
