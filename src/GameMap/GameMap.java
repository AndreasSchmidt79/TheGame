package GameMap;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	
	public GameMap(){
		int[][] gameMapTiles = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			};
		
		dimensions = 19;
		mapTilesTypes = gameMapTiles;
		mapTiles = new MapTile[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++){
			for(int j = 0; j < dimensions; j++){
				mapTiles[i][j] = new MapTile(mapTilesTypes[i][j]);
			}
		}
	}
	
	public GameMap(int dimensions, int[][] mapTileTypes, MapTile[][] mapTiles) {
		this.dimensions = dimensions;		
		this.mapTilesTypes = mapTileTypes;
		this.mapTiles = mapTiles;
	}

	public int[][] getMapTileTypes() {
		return mapTilesTypes;
	}

	public int getDimensions() {
		return dimensions;
	}

	public MapTile[][] getMapTiles() {
		return mapTiles;
	}

}
