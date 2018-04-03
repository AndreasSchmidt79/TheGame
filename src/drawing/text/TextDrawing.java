package drawing.text;

import java.util.ArrayList;
import java.util.HashMap;

import drawing.Button;
import drawing.Drawing;
import drawing.Position;
import drawing.Texture;
import drawing.TextureCache;

public class TextDrawing extends Drawing {
	
	private static final String FONT_WHITE_FILEPATH = "./res/font/font.png";
	private static final String FONT_BLACK_FILEPATH = "./res/font/font_black.png";
	private static final String BUTTON_FILEPATH = "./res/UI/button.png";
	private static final String BUTTON_HOVER_FILEPATH = "./res/UI/buttonHover.png";
	private static final String BUTTON_CLICKED_FILEPATH = "./res/UI/buttonClicked.png";
	
	private GlyphDataReader glyphDataReader;
	private HashMap<String, GlyphData> glyphs; 
	
	private ArrayList<Button> activeButtons = new ArrayList<Button>();

	public TextDrawing(int screenWidth, int screenHeight, int mapSizeInTiles, float aspectRation) {
		super(screenWidth, screenHeight, mapSizeInTiles, aspectRation);
		glyphDataReader	= new GlyphDataReader("./res/font/font.fnt");
		this.glyphs = glyphDataReader.glyphs;
	}
	
	public void drawChar(Position pos, GlyphData glyph, float fontsize){
		
		float glyphWidth = (float)glyph.width/(float)screenWidth;
		float glyphHeight = (float)glyph.height/(float)screenHeight;
		
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)(pos.getY() + glyph.yoffset*fontsize)*2/(float)screenHeight;
		
		float[] texture = getGlyphTexture(glyph);
		float[] vertices = getVertices(posX, posY, glyphWidth*fontsize, glyphHeight*fontsize);

		//startTime = System.currentTimeMillis();
		render(textureCache.getTexture(FONT_BLACK_FILEPATH), vertices, texture);		
		//endTime = System.currentTimeMillis();
		//System.out.println("rendering took: " + (double)(endTime-startTime) + " miliseconds");
	}
	
	private float[] getGlyphTexture(GlyphData glyph) {
		
		float glyphPosX = (float)glyph.x/512;
		float glyphPosY = (float)glyph.y/512;
		
		float glyphWidth = (float)glyph.width/512;
		float glyphHeight = (float)glyph.height/512;
		
		return new float[] { 
				glyphPosX,					glyphPosY,
				glyphPosX + glyphWidth,		glyphPosY,
				glyphPosX + glyphWidth,		glyphPosY + glyphHeight,
				
				glyphPosX + glyphWidth,		glyphPosY + glyphHeight,
				glyphPosX,					glyphPosY + glyphHeight,
				glyphPosX,					glyphPosY 
		};	
	}
	
	public void drawString(Position pos, String string, float fontsize) {
		for (int i = 0; i < string.length(); i++){
		    char c = string.charAt(i);
		    GlyphData glyph = glyphs.get(c + "");
		    if(glyph != null) {
		    	drawChar(pos, glyph, fontsize);
		    	pos.setX(pos.getX() + Math.round(glyph.xadvance * fontsize));
		    }
		}
	}
	
	public void drawButtonWithText(Button button, String string, float fontsize) {
		drawRectangle(button.getPos(), button.getWidth(), button.getHeight(), textureCache.getTexture(BUTTON_FILEPATH));
		drawString(new Position(Math.round(button.getPos().getX() + (button.getWidth()*55/300)), Math.round(button.getPos().getY() + fontsize*50)), string, fontsize);
	}
	
	public void drawMainMenu() {
		int buttonWidth= 300;
		int buttonHeight= 94;
		int posX = screenWidth/2 - buttonWidth/2;
		int posY = 150;
		Button newGameButton = new Button(new Position(posX,posY), buttonWidth, buttonHeight);
		Button ExitButton = new Button(new Position(posX,posY + buttonHeight + 20), buttonWidth, buttonHeight);
		activeButtons.add(newGameButton);
		activeButtons.add(ExitButton);
		drawButtonWithText(newGameButton, "New Game", 0.5f);
		drawButtonWithText(ExitButton, "Exit", 0.5f);
	}

	public ArrayList<Button> getActiveButtons() {
		return activeButtons;
	}
	
	public void clearActiveButtons() {
		activeButtons = new ArrayList<Button>();
	}
	
	public void drawFPS(int fps) {
		drawString(new Position(screenWidth-150, 10), "FPS: " + fps, 0.4f);
	}

}
