package game;

import java.util.ArrayList;

import drawing.DrawHandler;

import drawing.Position;
import drawing.Screen;

import gameMap.*;
import inventory.Weapon.DamageRange;
import inventory.Weapon.Sword;
import inventory.armour.LeatherArmour;
import inventory.armour.SteelHelmet;
import mob.Mob;
import player.Player;
import userInteractions.UserInteractions;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class Game {

	private GameMap currentGameMap = new GameMap();
	private ArrayList<GameMap> gameMaps = new ArrayList<>();
	private Player player;

	private DrawHandler drawHandler;
	private MapGenerator mapGenerator;
	private UserInteractions userInteractions;
	public GameState currentGameState;

	private String infoText = "";
	private int fps = 0;

	public Game(long window) {
		mapGenerator = new MapGenerator();

		drawHandler = new DrawHandler(Screen.MAP_SIZE_IN_TILES, window);
		currentGameState = new GameState();
		currentGameState.setGlobalState(GlobalState.MAINMENU);
		userInteractions = new UserInteractions();
		player = new Player(new Position(0,0));
	}
	
	private void startNewGame() {
		getGameMaps();

		player = new Player(gameMaps.get(0).getStartPosition());
		
		player.inventory.addEquipment(new SteelHelmet("hammer Helm", 2));
		player.inventory.addEquipment(new LeatherArmour("Lederrüstung", 4));
		player.inventory.addEquipment(new Sword("scharfes Schwert", new DamageRange(1, 2)));
		player.setHealth(60);

		currentGameState.setGlobalState(GlobalState.GAME);
		currentGameState.setDisplayState(DisplayState.MAP);
		currentGameState.clearUserAction();
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

		userInteractions.checkUserInteractions(player, currentGameState, currentGameMap);

		if(currentGameState.isNewGameStarted()) {
			startNewGame();
		}
		if(currentGameState.isExitGame()) {
			currentGameState.clearUserAction();
			glfwTerminate();
		}
        drawHandler.drawAll(player, currentGameMap, currentGameState, fps, infoText, userInteractions.getButtons());
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
		if(currentGameState.shouldMapIntervalBeChecked()) {
			for (Mob mob : currentGameMap.getMobsToMove()) {
				currentGameMap.getMapTileAtPos(mob.getPos()).removeMob();
				mob.moveRandom(currentGameMap);
				currentGameMap.getMapTileAtPos(mob.getPos()).setMob(mob);
			}
			updateEvents();
			currentGameMap.resetMobsToMove();
		}
	}
	
	public void  setFps(int fps) {
		this.fps = fps;
	}


}
