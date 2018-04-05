package drawing;

import gameMap.Scaling;

public class Drawing {
	
	protected int screenWidth;
	protected int screenHeight;
	private static final String BACKGROUND_FILEPATH = "./res/UI/bricks_bg.png";
	public static final String MAPFRAME_FILEPATH = "./res/UI/frame.png";

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
		drawRectangle(new Position(0,0),screenWidth, screenHeight, textureCache.getTexture(BACKGROUND_FILEPATH));
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
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)pos.getY()*2/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		
		float[] vertices = getVertices(posX, posY, facX*scaling.getScaleX(), facY*scaling.getScaleY());
		float[] texture = inversX ? getInversXTexture() : getDefaultTexture();	
		render(tex, vertices, texture);
	}
	
	protected float[] getDefaultTexture() {
		return new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };
	}

	protected float[] getInversXTexture() {		
		return new float[] { 1,0,0,0,0,1,0,1,1,1,1,0 };
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
