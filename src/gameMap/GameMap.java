package gameMap;

import java.util.ArrayList;

import drawing.Position;
import mob.Mob;

public class GameMap {
	
	private int dimensions;
	private int[][] mapTilesTypes;
	private MapTile[][] mapTiles;
	private Position startPosition;
	private float lightStrength = 0.65f; //should be between 0.65 (strongest) and 1
	private boolean flicker = false;
	private ArrayList<MapPortal> mapPortals = new ArrayList<MapPortal>();
	private ArrayList<Mob> mobsToMove = new ArrayList<Mob>();
	
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
	
	public MapTile getMapTileAtPos(Position pos) {
		return mapTiles[pos.getX()][pos.getY()];
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

	public ArrayList<Mob> getMobsToMove() {
		return mobsToMove;
	}
	
	public void addMobToMobsToMoveList(Mob mob) {
		if(!mobsToMove.contains(mob)) {
			mobsToMove.add(mob);
		}
	}
	
	public void resetMobsToMove() {
		mobsToMove = new ArrayList<Mob>();
	}
	
	public void addMob(Mob mob) {
		this.mapTiles[mob.getPos().getX()][mob.getPos().getY()].setMob(mob);
	}
	
	public void removeMob(Mob mob) {
		this.mapTiles[mob.getPos().getX()][mob.getPos().getY()].removeMob();
	}

	public float getLightStrength() {
		return lightStrength;
	}

	public void setLightStrength(float lightStrength) {
		this.lightStrength = lightStrength;
	}

	public boolean isFlicker() {
		return flicker;
	}

	public void setFlicker(boolean flicker) {
		this.flicker = flicker;
	}

}
