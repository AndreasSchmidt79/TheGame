package GameMap;

public class MapTile {
	
	private String textureFilePath;
	boolean isPassable;
	private String decorationFilePath;
	MapTileMapping mapTileMapping = new MapTileMapping();
	DecorationMapping decorationMapping = new DecorationMapping();
	Scaling decorationScaling;
	
	
	public MapTile(int type, int decorationType){
		textureFilePath = mapTileMapping.getTextureFilePath(type);
		isPassable = mapTileMapping.IsPassable(type);
		decorationFilePath = decorationMapping.getDecorationFilePath(decorationType);
		decorationScaling = decorationMapping.getDecorationScaling(decorationType);
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
