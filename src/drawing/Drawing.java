package drawing;

import java.util.ArrayList;

import drawing.button.Button;
import gameMap.Scaling;

public class Drawing {
	
	protected static final int MAP_PADDING = 25;
	protected static final int MAIN_MENU_BUTTON_WIDTH = 400;
	protected static final int MAIN_MENU_BUTTON_HEIGHT = 80;
	protected int screenWidth;
	protected int screenHeight;

	protected ArrayList<Button> activeButtons = new ArrayList<>();

	protected int mapSizeInTiles; 
	protected TextureCache textureCache = new TextureCache();
	
	public Drawing(int screenWidth, int screenHeight, int mapSizeInTiles){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.mapSizeInTiles = mapSizeInTiles;
	}
	
	protected void render(String filePath, float[] vertices, float[] textureCoords) {
		render(textureCache.getTexture(filePath),  vertices,  textureCoords);
	}
	
	protected void render(Texture tex, float[] vertices, float[] textureCoords) {
		Model model = new Model(vertices, textureCoords);		
		tex.bind();
		model.render();
	}
	
	public void drawBackground() {
		drawRectangle(new Position(0,0),screenWidth, screenHeight, textureCache.getTexture(TextureFilepath.BACKGROUND.getFilepath()));
	}
	
	public void drawSemiTransparentPane() {
		drawRectangle(new Position(0, 0), screenWidth, screenHeight, textureCache.getTexture(TextureFilepath.TRANSPARENT_PANE.getFilepath()));
	}
	
	protected void drawRectangle(Position pos, int width, int height, Texture tex){
		drawRectangle(pos, width, height, tex, false, new Scaling(1,1));
	}
	
	protected void drawRectangle(Position pos, int width, int height, Texture tex, boolean inversX){
		drawRectangle(pos, width, height, tex, inversX, new Scaling(1,1));
	}
	
	protected void drawRectangle(Position pos, int width, int height, Texture tex, Scaling scaling){
		drawRectangle(pos, width, height, tex, false, scaling);
	}

	protected void drawRectangle(Position pos, int width, int height, Texture tex, boolean inversX, Scaling scaling){
		centerScaledPosition(pos, scaling, width, height);
		float[] vertices = calculateVertices(pos, width, height, scaling);
		float[] texture = inversX ? getInversXTexture() : getDefaultTexture();	
		render(tex, vertices, texture);
	}
	
	protected void drawRectangleFromSprite(Position pos, int width, int height, Texture tex, SpriteElement spriteElement){
		float[] vertices = calculateVertices(pos, width, height, new Scaling(1,1));
		float[] texture = getSpriteTextures(spriteElement, tex);
		render(tex, vertices, texture);
	}
	
	protected void drawRectangleZoomTexture(Position pos, int width, int height, Texture tex, float zoom){
		render(tex, calculateVertices(pos, width, height, new Scaling(1,1)), getZoomTexture(zoom));
	}
	
	private float[] calculateVertices(Position pos, int width, int height, Scaling scaling) {
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)pos.getY()*2/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		return getVertices(posX, posY, facX*scaling.getScaleX(), facY*scaling.getScaleY());
	}
	
	private void centerScaledPosition(Position pos, Scaling scaling, int width, int height) {
		pos.setX(pos.getX() + Math.round(((1-scaling.getScaleX())*width)/2));
		pos.setY(pos.getY() + Math.round(((1-scaling.getScaleY())*height)/2));
	}
	
	protected float[] getDefaultTexture() {
		return new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };
	}
	
	protected float[] getZoomTexture(float z) {
		return new float[] { 1-z,1-z,z,1-z,z,z,z,z,1-z,z,1-z,1-z };
	}

	protected float[] getInversXTexture() {		
		return new float[] { 1,0,0,0,0,1,0,1,1,1,1,0 };
	}
	
	protected float[] getSpriteTextures(SpriteElement sprite, Texture tex) {
		
		float spritePosX = (float)sprite.getPos().getX()/tex.getWidth();
		float spritePosY = (float)sprite.getPos().getY()/tex.getHeight();
		
		float spriteWidth = (float)sprite.getWidth()/tex.getWidth();
		float spriteHeight = (float)sprite.getHeight()/tex.getHeight();
		
		return new float[] { 
				spritePosX,					spritePosY,
				spritePosX + spriteWidth,		spritePosY,
				spritePosX + spriteWidth,		spritePosY + spriteHeight,
				
				spritePosX + spriteWidth,		spritePosY + spriteHeight,
				spritePosX,					spritePosY + spriteHeight,
				spritePosX,					spritePosY 
		};	
	}

	protected float[] getVertices(float posX, float posY, float facX, float facY) {
		float[] vertices = new float[]{ 
			    0 * facX + posX,  0 * facY + posY,
				2 * facX + posX,  0 * facY + posY,
				2 * facX + posX, -2 * facY + posY,
				
				2 * facX + posX, -2 * facY + posY,
			    0 * facX + posX, -2 * facY + posY,
			    0 * facX + posX,  0 * facY + posY };
		return vertices;
	}	

}
