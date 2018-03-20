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
		for(int i = 0; i<dimensionsy; i++) {
			for(int j=0;j<dimensionsx;j++) {
				if(i==0 || i==dimensionsx || j==0 || j==dimensionsx) {
					gameMap[i][j]=0;
				}
				else if (i-1==1 && j-1==1 || i-1==2 && j-1 == 2) {
					int x=rand.nextInt(10);
					if (x>=7) {
						gameMap[i][j]=gameMap[i-1][j];
					}
					else {
						x=rand.nextInt(10);
						if(x>3){
							gameMap[i][j]=1;
						}	
						else {
							gameMap[i][j]=rand.nextInt(2)+2;
						}
					}					
				}
				else if (i-1==1 || i-1==2)  {
					int x=rand.nextInt(10);
					if (x>=4) {
						gameMap[i][j]=gameMap[i-1][j];
					}
					else {
						gameMap[i][j]=rand.nextInt(3);
					}					
				}
				else if (j-1==1 || j-1==2)  {
					int x=rand.nextInt(10);
					if (x>=4) {
						gameMap[i][j]=gameMap[i][j-1];
					}
					else {
						gameMap[i][j]=rand.nextInt(3);
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
}
