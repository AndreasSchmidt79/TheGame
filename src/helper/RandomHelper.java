package helper;
import java.util.Random;

public class RandomHelper {

	public static final String DIR_LEFT = "left";
	public static final String DIR_TOP = "top";
	public static final String DIR_RIGHT = "right";
	public static final String DIR_DOWN = "down";
	
	private static final Random rand = new Random();
	
	public static final String getRandomDirection() {
		int random4 = rand.nextInt(4);
		switch(random4) {
			case 0:
				return DIR_LEFT;
			case 1:
				return DIR_TOP;
			case 2:
				return DIR_RIGHT;
			case 3:
				return DIR_DOWN;
			
		}
		return null;
	}
}
