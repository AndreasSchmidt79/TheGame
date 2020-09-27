package drawing;

public class Screen {
    public static final int WIDTH = 2400;
    public static final int HEIGHT = WIDTH * 9 / 16;
    public static final boolean IS_FULLSCREEN = false;
    public static final int PADDING = (int) (WIDTH * 0.02);
    public static final int MAP_SIZE_IN_TILES = 13; // needs to be odd, so player can be in the middle
    public static final int CHAR_PANEL_HEIGHT = (int)Math.round(Screen.HEIGHT*0.85);
    public static final int INFO_PANEL_HEIGHT = (int)Math.round(Screen.HEIGHT*0.15);
}
