package GameMap;

public class MapTile {
	
	public static final int MAPTILE_WALL = 2;
	public static final int MAPTILE_GRASS = 1;
	public static final int MAPTILE_WATER = 0;
	protected String textureFilePath;
	boolean isPassable = false;
	
	public MapTile(int type){
		switch(type){
			case MAPTILE_WATER:
				textureFilePath = "./res/MapTile_0_water.png";	
				break;
			case MAPTILE_GRASS:
				textureFilePath = "./res/MapTile_1_grass.png";
				isPassable = true;
				break;
			case MAPTILE_WALL:
				textureFilePath = "./res/MapTile_2_wall.png";				
				break;
		}
		
	}

	public boolean isPassable() {
		return isPassable;
	}

	public String getTextureFilePath() {
		return textureFilePath;
	}
	
}
