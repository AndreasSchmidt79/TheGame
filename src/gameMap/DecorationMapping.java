package gameMap;

import java.util.HashMap;

public class DecorationMapping {

	public static final int NO_DECORATION = -1;
	public static final int DECORATION_STUMP = 0;
	public static final int DECORATION_CACTUS = 1;
	public static final int DECORATION_ROCK = 2;
	public static final int DECORATION_FLOWER1 = 3;
	public static final int DECORATION_FLOWER2 = 4;
	public static final int DECORATION_PEBBLE1 = 5;
	public static final int DECORATION_PEBBLE2 = 6;
	public static final int DECORATION_LILYPAD1 = 7;
	public static final int DECORATION_LILYPAD2 = 8;
	public static final int DECORATION_BUNNY = 9;
	public static final int DECORATION_DEER = 10;
	public static final int DECORATION_TREE1 = 11;
	public static final int DECORATION_TREE2 = 12;
	public static final int DECORATION_PATH = 13;
	
	
    public static final HashMap<Integer, String> textureFilePathMap;
    public static final HashMap<Integer, Scaling> scalingMap;
    
    static {
    	textureFilePathMap = new HashMap<Integer, String>();
    	textureFilePathMap.put(DECORATION_STUMP, "./res/decoration/stump.png");
    	textureFilePathMap.put(DECORATION_CACTUS, "./res/decoration/cactus.png");
    	textureFilePathMap.put(DECORATION_ROCK, "./res/decoration/rock.png");    	
    	textureFilePathMap.put(DECORATION_FLOWER1, "./res/decoration/flower1.png");
    	textureFilePathMap.put(DECORATION_FLOWER2, "./res/decoration/flower2.png");
    	textureFilePathMap.put(DECORATION_PEBBLE1, "./res/decoration/pebble1.png");
    	textureFilePathMap.put(DECORATION_PEBBLE2, "./res/decoration/pebble2.png");
    	textureFilePathMap.put(DECORATION_LILYPAD1, "./res/decoration/lilypad1.png");
    	textureFilePathMap.put(DECORATION_LILYPAD2, "./res/decoration/lilypad2.png");
    	textureFilePathMap.put(DECORATION_BUNNY, "./res/decoration/bunny.png");
    	textureFilePathMap.put(DECORATION_DEER, "./res/decoration/deer.png");
    	textureFilePathMap.put(DECORATION_TREE1, "./res/decoration/tree1.png");
    	textureFilePathMap.put(DECORATION_TREE2, "./res/decoration/tree2.png");
    	textureFilePathMap.put(DECORATION_PATH, "./res/decoration/path.png");
    	
    	
    	scalingMap = new HashMap<Integer, Scaling>();
    	scalingMap.put(DECORATION_STUMP, new Scaling(0.9f,0.9f));
    	scalingMap.put(DECORATION_CACTUS, new Scaling(0.8f,0.8f));
    	scalingMap.put(DECORATION_ROCK, new Scaling(0.7f,0.7f));    	
    	scalingMap.put(DECORATION_FLOWER1, new Scaling(0.3f,0.3f));
    	scalingMap.put(DECORATION_FLOWER2, new Scaling(0.3f,0.3f));
    	scalingMap.put(DECORATION_PEBBLE1, new Scaling(0.25f,0.25f));
    	scalingMap.put(DECORATION_PEBBLE2, new Scaling(0.25f,0.25f));
    	scalingMap.put(DECORATION_LILYPAD1, new Scaling(0.3f,0.3f));
    	scalingMap.put(DECORATION_LILYPAD2, new Scaling(0.3f,0.3f));
    	scalingMap.put(DECORATION_TREE1, new Scaling(1.5f,1.5f));
    	scalingMap.put(DECORATION_TREE2, new Scaling(1.5f,1.5f));
    	
    }
	
	public static String getDecorationFilePath(int type) {
    	if(textureFilePathMap.containsKey(type)) {
    		return textureFilePathMap.get(type);
		} 
		return "";		
    }
    
    public static Scaling getDecorationScaling(int type) {
    	if(scalingMap.containsKey(type)) {
    		return scalingMap.get(type);
		} 
		return new Scaling(1,1);		
    }

}
