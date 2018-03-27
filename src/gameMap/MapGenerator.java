package gameMap;

import java.util.Random;

public class MapGenerator {
	
	private static final int RANDOM_MAP_SIZE = 60;

	public MapGenerator() {
	
	}
	
	public GameMap getRandomGameMap() {
		int[][] gameMap = generateSimpleMap(RANDOM_MAP_SIZE);
		int[][] decorationMap = generateDecorations(RANDOM_MAP_SIZE);
			
		MapTile[][] mapTiles = new MapTile[RANDOM_MAP_SIZE][RANDOM_MAP_SIZE];
		for(int i = 0; i < RANDOM_MAP_SIZE; i++){
			for(int j = 0; j < RANDOM_MAP_SIZE; j++){
				mapTiles[i][j] = new MapTile(gameMap[i][j], decorationMap[i][j]);
			}
			System.out.println(" ");
		}
		return new GameMap(RANDOM_MAP_SIZE, gameMap, mapTiles);
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
						if(decorationTile != DecorationMapping.DECORATION_PATH) {
							decorations[i][j] = decorationTile;
						}
					}
					else {
						decorations[i][j] = DecorationMapping.NO_DECORATION;
					}
				}
			}
		}
		return decorations;
	}		
	
}
