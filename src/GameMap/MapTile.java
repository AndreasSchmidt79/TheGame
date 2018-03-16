package GameMap;

public class MapTile {
	
	protected String textureFilePath;
	boolean isPassable = false;
	
	public MapTile(int type){
		switch(type){
			case 0:
				textureFilePath = "./res/MapTile_0_water.png";	
				break;
			case 1:
				textureFilePath = "./res/MapTile_1_grass.png";
				isPassable = true;
				break;
			case 2:
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
