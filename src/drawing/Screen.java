package drawing;

public class Screen {
    public static int WIDTH = 1400;
    public static int HEIGHT = WIDTH * 9 / 16;
    public static boolean IS_FULLSCREEN = false;
    public static int PADDING = (int) (WIDTH * 0.02);
    public static int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle
    public static int CHAR_PANEL_HEIGHT = (int)Math.round(Screen.HEIGHT*0.85);
    public static int INFO_PANEL_HEIGHT = (int)Math.round(Screen.HEIGHT*0.15);
}
