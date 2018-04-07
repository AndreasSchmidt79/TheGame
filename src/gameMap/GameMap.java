package gameMap;

import java.util.ArrayList;

import drawing.Position;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	private Position startPosition;
	private ArrayList<MapPortal> mapPortals = new ArrayList<MapPortal>();
	
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
	
	public void addMapPortal(MapPortal mapPortal) {
		this.mapPortals.add(mapPortal);
	}

	public ArrayList<MapPortal> getMapPortals() {
		return mapPortals;
	}
	
	public MapPortal getPortalAtPos(Position pos) {
		for(MapPortal mapPortal: getMapPortals()) {
			if(pos.getX() == mapPortal.getPortalPosition().getX() && pos.getY() == mapPortal.getPortalPosition().getY()) {
				return mapPortal;
			}
		}
		return null;
	}

}
