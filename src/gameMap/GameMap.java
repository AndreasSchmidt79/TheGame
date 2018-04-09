package gameMap;

import java.util.ArrayList;

import drawing.Position;
import mob.Mob;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	private Position startPosition;
	private ArrayList<MapPortal> mapPortals = new ArrayList<MapPortal>();
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	
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

	public ArrayList<Mob> getMobs() {
		return mobs;
	}
	
	public void addMob(Mob mob) {
		this.mobs.add(mob);
		this.mapTiles[mob.getPos().getX()][mob.getPos().getY()].setMob(mob);
	}
	
	public void removeMob(Mob mob) {
		this.mobs.remove(mob);
		this.mapTiles[mob.getPos().getX()][mob.getPos().getY()].removeMob();
	}

}
