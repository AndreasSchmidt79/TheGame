package drawing.text;

import java.util.ArrayList;
import java.util.HashMap;

import drawing.Drawing;
import drawing.Position;
import drawing.button.Button;

public class TextDrawing extends Drawing {
	
	//private static final String FONT_WHITE_FILEPATH = "./res/font/sagas.png";
	private static final String FONT_BLACK_FILEPATH = "./res/font/sagas_black.png";
	private static final String FONT_GLYPHDATA_FILEPATH = "./res/font/sagas.fnt";
	
	private static final String BOX_FILEPATH = "./res/UI/box_3_1.png";
	
	private GlyphDataReader glyphDataReader;
	private HashMap<String, GlyphData> glyphs; 
	

	public TextDrawing(int screenWidth, int screenHeight, int mapSizeInTiles) {
		super(screenWidth, screenHeight, mapSizeInTiles);
		glyphDataReader	= new GlyphDataReader(FONT_GLYPHDATA_FILEPATH);
		this.glyphs = glyphDataReader.glyphs;
	}
	
	public void drawChar(Position pos, GlyphData glyph, float fontsize){
		
		float glyphWidth = (float)glyph.width/(float)screenWidth;
		float glyphHeight = (float)glyph.height/(float)screenHeight;
		
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)(pos.getY() + glyph.yoffset*fontsize)*2/(float)screenHeight;
		
		float[] texture = getGlyphTexture(glyph);
		float[] vertices = getVertices(posX, posY, glyphWidth*fontsize, glyphHeight*fontsize);

		render(textureCache.getTexture(FONT_BLACK_FILEPATH), vertices, texture);		
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
	
	public void drawString(Position pos, String string, float fontsize, int boxWidth, int textOffsetX, boolean centered) {
		
		if(centered) {
			int length = Math.round(getTextLength(string) * fontsize);
			pos.setX(pos.getX() + Math.round((boxWidth - length)/2));
		} else {
			pos.setX(pos.getX() + textOffsetX);
		}
		
		for (int i = 0; i < string.length(); i++){
		    GlyphData glyph = glyphs.get(string.charAt(i) + "");
		    if(glyph != null) {
		    	drawChar(pos, glyph, fontsize);
		    	pos.setX(pos.getX() + Math.round(glyph.xadvance * fontsize));
		    }
		}
	}
	
	private int getTextLength(String string) {
		int textLength = 0;
		for (int i = 0; i < string.length(); i++){
		    GlyphData glyph = glyphs.get(string.charAt(i) + "");
		    if(glyph != null) {
		    	textLength += glyph.xadvance;
		    }
		}
		return textLength;
	}
	
	public void drawButtonWithText(Button button, float fontsize) {
		drawRectangleFromSprite(button.getPos(), button.getWidth(), button.getHeight(), textureCache.getTexture(button.getSpriteFilePath()), button.getSpriteElement());
		drawString(new Position(button.getPos().getX(), button.getPos().getY() + button.getButtonTextOffsetY()), 
				button.getText(), 
				fontsize,
				button.getWidth(),
				button.getButtonTextOffsetX(),
				true
			);
	}
	
	public void drawMainMenu(boolean newGameStarted) {
		
		if(activeButtons.isEmpty()) {
			activeButtons = getButtons(newGameStarted);
		}
		
		for(int i = 0; i < activeButtons.size(); i++) {
			Button button = activeButtons.get(i);
			int posX = screenWidth/2 - BUTTON_WIDTH/2;
			button.setPos(new Position(posX, 150 + i*(BUTTON_HEIGHT + 20)));
			drawButtonWithText(button, 0.6f);
		}
		
	}
	
	private ArrayList<Button> getButtons(boolean newGameStarted) {
		ArrayList<Button> buttons = new ArrayList<Button>();
		
		if(newGameStarted) {
			buttons.add(new Button(Button.CONTINUE, new Position(0,0), BUTTON_WIDTH, BUTTON_HEIGHT, "Continue"));
		}
		
		buttons.add(new Button(Button.NEW_GAME, new Position(0,0), BUTTON_WIDTH, BUTTON_HEIGHT, "New Game"));
		buttons.add(new Button(Button.EXIT, new Position(0,0), BUTTON_WIDTH, BUTTON_HEIGHT, "Exit"));

		return buttons;
	}

	public ArrayList<Button> getActiveButtons() {
		return activeButtons;
	}
	
	public void clearActiveButtons() {
		activeButtons = new ArrayList<Button>();
	}
	
	public void drawFPS(int fps) {
		drawString(new Position(screenWidth-150, 10), "FPS: " + fps, 0.6f, 150, 0, false);
	}
	public void drawInfoBox() {
		drawRectangle(new Position(screenHeight, screenHeight-225), screenWidth-screenHeight-MAP_PADDING, 200, textureCache.getTexture(BOX_FILEPATH));
	}
	
	public void drawInfoBoxText(String string) {
		drawString(new Position(screenHeight+20,screenHeight-200), string, 0.4f, screenWidth-screenHeight-MAP_PADDING, 20, false);
	}

}
