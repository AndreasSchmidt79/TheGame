package helper;

import drawing.Screen;

public class PixelRelative {

    public static int getWidth(int width, int baseWidth){
        return Math.round((float) Screen.WIDTH*width/baseWidth);
    }
}
