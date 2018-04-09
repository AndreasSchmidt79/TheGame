package gameMap;

import mob.Mob;

public class MapTile {
	
	private String textureFilePath;
	private boolean isPassable;
	private String decorationFilePath;
	private Scaling decorationScaling;
	private int mapTileType;
	private int decorationType;
	private Mob mob = null;
	
	public MapTile(int type, int decorationType){
		this.textureFilePath = MapTileMapping.getTextureFilePath(type);
		this.isPassable = MapTileMapping.IsPassable(type);
		this.decorationFilePath = DecorationMapping.getDecorationFilePath(decorationType);
		this.decorationScaling = DecorationMapping.getDecorationScaling(decorationType);
		this.mapTileType = type;
		this.decorationType = decorationType;
	}

	public boolean isPassable() {
		return isPassable;
	}

	public String getTextureFilePath() {
		return textureFilePath;
	}

	public String getDecorationFilePath() {
		return decorationFilePath;
	}

	public Scaling getDecorationScaling() {
		return decorationScaling;
	}

	public int getMapTileType() {
		return mapTileType;
	}

	public int getDecorationType() {
		return decorationType;
	}

	public Mob getMob() {
		return mob;
	}

	public void setMob(Mob mob) {
		this.mob = mob;
	}
	
	public void removeMob() {
		this.mob = null;
	}
	
}
