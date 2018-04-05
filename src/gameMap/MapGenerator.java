package gameMap;

import java.util.Random;

import drawing.Position;

public class MapGenerator {
	
	private static final int RANDOM_MAP_SIZE = 60;

	public MapGenerator() {
	
	}
	
	public GameMap getRandomGameMap() {
		int[][] gameMap = generateSimpleMap(RANDOM_MAP_SIZE);
		int[][] decorationMap = generateDecorations(RANDOM_MAP_SIZE);
			
		MapTile[][] mapTiles = getMapTilesFromMapTypes(gameMap, decorationMap, RANDOM_MAP_SIZE);
		return new GameMap(RANDOM_MAP_SIZE, gameMap, mapTiles, new Position(8, 8));
	}

	private MapTile[][] getMapTilesFromMapTypes(int[][] gameMap, int[][] decorationMap, int dimensions) {
		MapTile[][] mapTiles = new MapTile[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++){
			for(int j = 0; j < dimensions; j++){
				mapTiles[i][j] = new MapTile(gameMap[i][j], decorationMap[i][j]);
			}
		}
		return mapTiles;
	}
	
	public GameMap getRandomDungeonMap() {
		int[][] gameMap = generateDungeon(20);
		int[][] decorationMap = generateDungeonDecorations(20);
			
		MapTile[][] mapTiles = getMapTilesFromMapTypes(gameMap, decorationMap, 20);
		return new GameMap(RANDOM_MAP_SIZE, gameMap, mapTiles, new Position(10,14));
	}
	
	private int[][] generateDungeon(int dimensions){
		int[][] gameMap = new int[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				if(i < 6 || i > dimensions - 7 || j < 6 || j > dimensions - 7) {
					gameMap[i][j] = MapTileMapping.MAPTILE_DUNGEON_WALL;
				}
				else {
					gameMap[i][j] = MapTileMapping.MAPTILE_DUNGEON_FLOOR;
				}
			}
		}
		gameMap[10][14] = MapTileMapping.MAPTILE_DUNGEON_DOOR_O;
		return gameMap;
	}
	
	private int[][] generateDungeonDecorations(int dimensions){
		int[][] decorations = new int[dimensions][dimensions];
		Random rand = new Random();		
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				if(i < 6 || i > dimensions - 7 || j < 6 || j > dimensions - 7) {
					decorations[i][j] = DecorationMapping.NO_DECORATION;
				}
				else {
					int setDecoProbability = rand.nextInt(100);
					if(setDecoProbability < 2) {		
						decorations[i][j] = DecorationMapping.DECORATION_CHEST_CLOSED;
					}
					else {
						decorations[i][j] = DecorationMapping.NO_DECORATION;
					}
				}
			}
		}
		return decorations;
	}
	
	private int[][] generateSimpleMap(int dimensions){
		int[][] gameMap = new int[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				if(i < 6 || i > dimensions - 7 || j < 6 || j > dimensions - 7) {
					if(i==0) {
						gameMap[i][j] = MapTileMapping.MAPTILE_WATER_1;
					}
					else if( i==1) {
						gameMap[i][j] = MapTileMapping.MAPTILE_WATER_3;
					}
					else {
						gameMap[i][j] = MapTileMapping.MAPTILE_WATER_2;
					}
					
				}
				else if(i==(int)dimensions/2) {
					gameMap[i][j] = MapTileMapping.MAPTILE_ROAD_2;
				}
				else {
					gameMap[i][j] = MapTileMapping.MAPTILE_GRASS_1;
				}
			}
		}
		return gameMap;
	}
	
	private int[][] generateDecorations(int dimensions){
		int[][] decorations = new int[dimensions][dimensions];
		Random rand = new Random();		
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				if(i < 6 || i > dimensions - 7 || j < 6 || j > dimensions - 7) {
					decorations[i][j] = DecorationMapping.NO_DECORATION;
				}
				else {
					int setDecoProbability = rand.nextInt(100);
					if(setDecoProbability < 5) {		
						int decorationTile = rand.nextInt(DecorationMapping.textureFilePathMap.size());
						if(decorationTile <= DecorationMapping.DECORATION_PATH) {
							decorations[i][j] = decorationTile;
						}
					}
					else {
						decorations[i][j] = DecorationMapping.NO_DECORATION;
					}
				}
			}
		}
		decorations[10][10] = DecorationMapping.DECORATION_MOUNTAIN_T_L;
		decorations[11][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[12][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[13][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[14][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[15][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[16][10] = DecorationMapping.DECORATION_MOUNTAIN_T;
		decorations[17][10] = DecorationMapping.DECORATION_MOUNTAIN_T_R;
		
		decorations[10][11] = DecorationMapping.DECORATION_MOUNTAIN_L;
		decorations[11][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[12][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[13][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[14][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[15][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[16][11] = DecorationMapping.DECORATION_MOUNTAIN_C;
		decorations[17][11] = DecorationMapping.DECORATION_MOUNTAIN_R;

		decorations[10][12] = DecorationMapping.DECORATION_MOUNTAIN_B_L;
		decorations[11][12] = DecorationMapping.DECORATION_MOUNTAIN_B;
		decorations[12][12] = DecorationMapping.DECORATION_MOUNTAIN_B;
		decorations[13][12] = DecorationMapping.DECORATION_CAVE_ENTRANCE;
		decorations[14][12] = DecorationMapping.DECORATION_MOUNTAIN_B;
		decorations[15][12] = DecorationMapping.DECORATION_MOUNTAIN_B;
		decorations[16][12] = DecorationMapping.DECORATION_MOUNTAIN_B;
		decorations[17][12] = DecorationMapping.DECORATION_MOUNTAIN_B_R;
		
		return decorations;
	}		
	
}
