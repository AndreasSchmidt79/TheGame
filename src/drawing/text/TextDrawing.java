package drawing.text;

import java.util.ArrayList;
import java.util.HashMap;

import drawing.*;

public class TextDrawing {

    private HashMap<String, GlyphData> glyphs;
    private BaseDrawing baseDrawing;

    private static final String FONT_BLACK_FILEPATH = "./res/font/sagas_black.png";
    private static final String FONT_GLYPHDATA_FILEPATH = "./res/font/sagas.fnt";

    public TextDrawing(BaseDrawing baseDrawing) {
        this.baseDrawing = baseDrawing;
        GlyphDataReader glyphDataReader = new GlyphDataReader(FONT_GLYPHDATA_FILEPATH);
        this.glyphs = glyphDataReader.glyphs;
    }

    public void drawChar(Position pos, GlyphData glyph, float fontsize, TextureCache textureCache) {

        float glyphWidth = (float) glyph.width / (float) Screen.WIDTH;
        float glyphHeight = (float) glyph.height / (float) Screen.HEIGHT;

        float posX = -1 + (float) pos.getX() * 2 / (float) Screen.WIDTH;
        float posY = 1 - (float) (pos.getY() + glyph.yoffset * fontsize) * 2 / (float) Screen.HEIGHT;

        float[] texture = getGlyphTexture(glyph);
        float[] vertices = baseDrawing.getVertices(posX, posY, glyphWidth * fontsize, glyphHeight * fontsize);

        baseDrawing.render(textureCache.getTexture(FONT_BLACK_FILEPATH), vertices, texture);
    }

    private float[] getGlyphTexture(GlyphData glyph) {

        float glyphPosX = (float) glyph.x / 512;
        float glyphPosY = (float) glyph.y / 512;

        float glyphWidth = (float) glyph.width / 512;
        float glyphHeight = (float) glyph.height / 512;

        return new float[]{
                glyphPosX, glyphPosY,
                glyphPosX + glyphWidth, glyphPosY,
                glyphPosX + glyphWidth, glyphPosY + glyphHeight,

                glyphPosX + glyphWidth, glyphPosY + glyphHeight,
                glyphPosX, glyphPosY + glyphHeight,
                glyphPosX, glyphPosY
        };
    }

    public void drawText(Position pos, String string, float fontsize, int boxWidth, int textOffsetX, boolean centered, TextureCache textureCache) {

        if (centered) {
            int length = Math.round(getTextLength(string) * fontsize);
            pos.setX(pos.getX() + Math.round((boxWidth - length) / 2));
        } else {
            pos.setX(pos.getX() + textOffsetX);
        }

        for (int i = 0; i < string.length(); i++) {
            GlyphData glyph = glyphs.get(string.charAt(i) + "");
            if (glyph != null) {
                drawChar(pos, glyph, fontsize, textureCache);
                pos.setX(pos.getX() + Math.round(glyph.xadvance * fontsize));
            }
        }
    }

    private int getTextLength(String string) {
        int textLength = 0;
        for (int i = 0; i < string.length(); i++) {
            GlyphData glyph = glyphs.get(string.charAt(i) + "");
            if (glyph != null) {
                textLength += glyph.xadvance;
            }
        }
        return textLength;
    }

    public void drawFPS(int fps, TextureCache textureCache) {
        drawText(new Position(Screen.WIDTH - 150, 10), "FPS: " + fps, 0.6f, 150, 0, false, textureCache);
    }

    public void drawInfoPanelText(String string, TextureCache textureCache) {
        drawText(new Position(Screen.WIDTH + Screen.PADDING, Screen.HEIGHT - 200), string, 0.4f, Screen.WIDTH - Screen.HEIGHT - Screen.PADDING, 20, false, textureCache);
    }

}
