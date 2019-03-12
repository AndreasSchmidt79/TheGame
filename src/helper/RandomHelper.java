package helper;
import java.util.Random;

public class RandomHelper {

	private static final Random rand = new Random();
	
	public static final Direction getRandomDirection() {
		int random4 = rand.nextInt(4);
		switch(random4) {
			case 0:
				return Direction.LEFT;
			case 1:
				return Direction.TOP;
			case 2:
				return Direction.RIGHT;
			case 3:
				return Direction.DOWN;
			
		}
		return null;
	}
	
	public static final float getRandomLightStrength() {
		return rand.nextFloat()*0.05f;
	}
}
