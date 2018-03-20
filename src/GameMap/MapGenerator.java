package GameMap;

import java.util.Random;

public class MapGenerator {
	
	public MapGenerator() {
	
	}
	
	public GameMap getGameMap() {
		
		
		int[][] gameMap = generateMap();
		int dimensions = 19;	
		MapTile[][] mapTiles = new MapTile[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++){
			for(int j = 0; j < dimensions; j++){
				mapTiles[i][j] = new MapTile(gameMap[i][j]);
			}
		}
		GameMap map = new GameMap(dimensions, gameMap, mapTiles);
		return map;
	}
	
	private int[][] generateMap(){
		int dimensionsx = 19;
		int dimensionsy = 19;
		
		Random rand = new Random();		
		int[][] gameMap = new int[dimensionsx][dimensionsy];
		
		for(int i = 0; i < dimensionsy; i++) {
			for(int j = 0; j < dimensionsx; j++) {
				if(i == 0 || i == dimensionsx || j == 0 || j == dimensionsx) {
					gameMap[i][j] = 0;
				}
				else if (gameMap[i-1][j] == 1 && gameMap[i][j-1] == 1 || gameMap[i-1][j] == 2 && gameMap[i][j-1] == 2) {
					int x=rand.nextInt(10);
					if (x <= 7) {
						gameMap[i][j] = gameMap[i-1][j];
					}
					else {
						setGameMapToGrassOrRandomTile(i,j,gameMap,30,rand);						
					}					
				}
				else if (gameMap[i-1][j] == 1 || gameMap[i-1][j] == 2)  {
					int x=rand.nextInt(10);
					if (x <= 3) {
						gameMap[i][j]=gameMap[i-1][j];
					}
					else {
						setGameMapToGrassOrRandomTile(i,j,gameMap,30,rand);					
					}					
				}
				else if (gameMap[i][j-1] == 1 || gameMap[i][j-1] == 2)  {
					int x=rand.nextInt(10);
					if (x<=3) {
						gameMap[i][j]=gameMap[i][j-1];
					}
					else {
						setGameMapToGrassOrRandomTile(i,j,gameMap,30,rand);
					}
				}
				else {
					gameMap[i][j]=rand.nextInt(3);
				}
			}
		}
				/* x = rand.nextInt(3);*/
		
				/*{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0},				
				{0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			};*/
		return gameMap;
	}
	
	private void setGameMapToGrassOrRandomTile(int i, int j, int[][] map, int probabilityGrass, Random rand) {
		if(rand.nextInt(100) > probabilityGrass){
			map[i][j] = 1;
		}	
		else {
			map[i][j] = rand.nextInt(2)+1;
		}
	}
	
	
}
