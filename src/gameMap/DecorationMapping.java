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
	public static final int DECORATION_CAVE_ENTRANCE = 13;
	public static final int DECORATION_MOUNTAIN_B_L = 14;
	public static final int DECORATION_MOUNTAIN_B_R = 15;
	public static final int DECORATION_MOUNTAIN_B = 16;
	public static final int DECORATION_MOUNTAIN_L = 17;
	public static final int DECORATION_MOUNTAIN_R = 18;
	public static final int DECORATION_MOUNTAIN_C = 19;
	public static final int DECORATION_MOUNTAIN_T = 20;
	public static final int DECORATION_MOUNTAIN_T_L = 21;
	public static final int DECORATION_MOUNTAIN_T_R = 22;
	public static final int DECORATION_CHEST_OPEN = 23;
	public static final int DECORATION_CHEST_CLOSED = 24;
	
	
	
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
    	textureFilePathMap.put(DECORATION_CAVE_ENTRANCE, "./res/decoration/cave_entrance.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_B_L, "./res/decoration/mountain_b_l.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_B_R, "./res/decoration/mountain_b_r.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_B, "./res/decoration/mountain_b.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_L, "./res/decoration/mountain_l.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_R, "./res/decoration/mountain_r.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_C, "./res/decoration/mountain_c.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_T, "./res/decoration/mountain_t.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_T_L, "./res/decoration/mountain_t_l.png");
    	textureFilePathMap.put(DECORATION_MOUNTAIN_T_R, "./res/decoration/mountain_t_r.png");
    	textureFilePathMap.put(DECORATION_CHEST_OPEN, "./res/decoration/chest_opened.png");
    	textureFilePathMap.put(DECORATION_CHEST_CLOSED, "./res/decoration/chest_closed.png");
    	
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
    	scalingMap.put(DECORATION_TREE1, new Scaling(1.0f,1.5f));
    	scalingMap.put(DECORATION_TREE2, new Scaling(1.0f,1.5f));
    	scalingMap.put(DECORATION_CHEST_OPEN, new Scaling(0.6f,0.6f));
    	scalingMap.put(DECORATION_CHEST_CLOSED, new Scaling(0.6f,0.6f));
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
