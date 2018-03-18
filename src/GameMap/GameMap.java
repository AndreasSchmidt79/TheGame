package GameMap;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	
	
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
