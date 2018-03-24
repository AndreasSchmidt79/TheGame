package gameMap;

public class MapTile {
	
	private String textureFilePath;
	boolean isPassable;
	private String decorationFilePath;
	Scaling decorationScaling;
	
	
	public MapTile(int type, int decorationType){
		textureFilePath = MapTileMapping.getTextureFilePath(type);
		isPassable = MapTileMapping.IsPassable(type);
		decorationFilePath = DecorationMapping.getDecorationFilePath(decorationType);
		decorationScaling = DecorationMapping.getDecorationScaling(decorationType);
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
	
}
