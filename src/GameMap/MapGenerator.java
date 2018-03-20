package GameMap;

import java.util.Random;

public class MapGenerator {
	
	private static final int PROBABILITY_GRASS_TILE = 65;
	private static final int PROBABILITY_2_NEIGHBOURS = 90;
	private static final int PROBABILITY_1_NEIGHBOUR = 35;
	private static int NUMBER_OF_TILES = 3;
	
	public MapGenerator() {
	
	}
	
	public GameMap getGameMap() {
		
		
		int[][] gameMap = generateMap();		
		int dimensions = 60;	
		MapTile[][] mapTiles = new MapTile[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++){
			for(int j = 0; j < dimensions; j++){
				System.out.print(gameMap[i][j] + " ");
				mapTiles[i][j] = new MapTile(gameMap[i][j]);
			}
			System.out.println(" ");
		}
		GameMap map = new GameMap(dimensions, gameMap, mapTiles);
		return map;
	}
	
	private int[][] generateMap(){
		int dimensionsx = 60;
		int dimensionsy = 60;
		
		Random rand = new Random();		
		int[][] gameMap = new int[dimensionsx][dimensionsy];
		
		for(int i = 0; i < dimensionsy; i++) {
			for(int j = 0; j < dimensionsx; j++) {
				if(i == 0 || i == dimensionsx - 1 || j == 0 || j == dimensionsx - 1) {
					gameMap[i][j] = MapTile.MAPTILE_WATER;
				}
				else {
					setGameMapToTileBasedOnNeighbours(i, j, gameMap, rand);
				}
			}
		}
		return gameMap;
	}
	
	private void setGameMapToGrassOrRandomTile(int i, int j, int[][] map, int probabilityGrass, Random rand) {
		if(rand.nextInt(100) < probabilityGrass){
			map[i][j] = MapTile.MAPTILE_GRASS;
		}	
		else {
			setTileToRandomTile(i, j, map, rand);
		}
	}
	
	private void setGameMapToTileBasedOnNeighbours(int i, int j, int[][] map, Random rand) {
		int leftNeighbour = getNeighbour(i - 1, j, map);
		int topNeighbour = getNeighbour(i, j - 1, map);
		if(leftNeighbour > 0) {
			setTileWithProbability(i , j, map, PROBABILITY_1_NEIGHBOUR, rand, leftNeighbour);
		} else if(topNeighbour > 0) {
			setTileWithProbability(i, j, map, PROBABILITY_1_NEIGHBOUR, rand, topNeighbour);
		} else if(topNeighbour > 0 && leftNeighbour > 0) {
			setTileWithProbability(i, j, map, PROBABILITY_2_NEIGHBOURS, rand, topNeighbour);
		} else {
			setTileToRandomTile(i, j, map, rand);
		}
	}
	
	
	private int getNeighbour(int i, int j, int[][] map) {
		if(map.length > i && map[i].length > j) {
			return map[i][j];
		}
		return -1;
	}
	
	private void setTileWithProbability(int i, int j, int[][] map, int probability, Random rand, int tileType) {
		if (rand.nextInt(100) <= probability) {
			map[i][j] = tileType;
		}
		else {
			setGameMapToGrassOrRandomTile(i, j, map, PROBABILITY_GRASS_TILE, rand);
		}
	}
	
	private void setTileToRandomTile(int i, int j, int[][] map, Random rand) {
		map[i][j] = rand.nextInt(NUMBER_OF_TILES);
	}
	
}
