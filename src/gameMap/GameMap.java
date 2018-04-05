package gameMap;

import drawing.Position;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	private Position startPosition;
	
	
	public GameMap(int dimensions, int[][] mapTileTypes, MapTile[][] mapTiles, Position startPosition) {
		this.dimensions = dimensions;		
		this.mapTilesTypes = mapTileTypes;
		this.mapTiles = mapTiles;
		this.startPosition = startPosition;
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

	public Position getStartPosition() {
		return startPosition;
	}

}
