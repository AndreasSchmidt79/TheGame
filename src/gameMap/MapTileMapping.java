package gameMap;

import java.util.HashMap;

public class MapTileMapping {

	public static final int MAPTILE_WATER_1 = 0;
	public static final int MAPTILE_WATER_2 = 1;
	public static final int MAPTILE_WATER_3 = 2;
	public static final int MAPTILE_GRASS_1 = 3;
	public static final int MAPTILE_GRASS_2 = 4;
	public static final int MAPTILE_DIRT_1 = 5;
	public static final int MAPTILE_SAND_1 = 6;
	public static final int MAPTILE_SOIL_1 = 7;
	public static final int MAPTILE_ROAD_1 = 8;
	public static final int MAPTILE_ROAD_2 = 9;
	
	
    public static final HashMap<Integer, String> textureFilePathMap;
    public static final HashMap<Integer, Boolean> isPassableMap;
    
    static {
    	textureFilePathMap = new HashMap<Integer, String>();
    	textureFilePathMap.put(MAPTILE_WATER_1, "./res/mapTiles/water1.png");
    	textureFilePathMap.put(MAPTILE_WATER_2, "./res/mapTiles/water2.png");
    	textureFilePathMap.put(MAPTILE_WATER_3, "./res/mapTiles/water3.png");
    	textureFilePathMap.put(MAPTILE_GRASS_1, "./res/MapTiles/grass1.png");        
    	textureFilePathMap.put(MAPTILE_GRASS_2, "./res/MapTiles/grass2.png");
    	textureFilePathMap.put(MAPTILE_DIRT_1, "./res/MapTiles/dirt1.png");
    	textureFilePathMap.put(MAPTILE_SAND_1, "./res/MapTiles/sand1.png");
    	textureFilePathMap.put(MAPTILE_SOIL_1, "./res/MapTiles/soil1.png");
    	textureFilePathMap.put(MAPTILE_ROAD_1, "./res/MapTiles/road1.png");
    	textureFilePathMap.put(MAPTILE_ROAD_2, "./res/MapTiles/bricks.png");
    	
    	
    	isPassableMap = new HashMap<Integer, Boolean>();
    	isPassableMap.put(MAPTILE_GRASS_1, true);        
    	isPassableMap.put(MAPTILE_GRASS_2, true);
    	isPassableMap.put(MAPTILE_DIRT_1, true);
    	isPassableMap.put(MAPTILE_SAND_1, true);
    	isPassableMap.put(MAPTILE_SOIL_1, true);
    	isPassableMap.put(MAPTILE_ROAD_1, true);
    	isPassableMap.put(MAPTILE_ROAD_2, true);
    }
	
    public static String getTextureFilePath(int type) {
    	if(textureFilePathMap.containsKey(type)) {
    		return textureFilePathMap.get(type);
		} 
		return "";		
    }
    
    public static Boolean IsPassable(int type) {
    	if(isPassableMap.containsKey(type)) {
			return isPassableMap.get(type);
		} 
    	return false;
    }
	

}
