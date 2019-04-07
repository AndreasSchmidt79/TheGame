package helper;

import drawing.Screen;

public class PixelRelative {

    public static int getWidth(int width, int baseWidth){
        return Math.round((float) Screen.WIDTH*width/baseWidth);
    }

    public static int getHeight(int height, int baseHeight){
        return Math.round((float) Screen.HEIGHT*height/baseHeight);
    }
}
