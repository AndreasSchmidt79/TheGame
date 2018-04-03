package drawing;

import org.lwjgl.opengl.GL11;

import drawing.text.GlyphData;
import drawing.text.GlyphDataReader;
import drawing.text.TextDrawing;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;

import java.util.HashMap;

import gameMap.GameMap;
import gameMap.MapTile;
import gameMap.Scaling;
import inventory.EquippableItem;
import player.Player;

public class Drawing {
	
	protected int screenWidth;
	protected int screenHeight;
	private static String BACKGROUND_FILEPATH = "./res/UI/bricks_bg.png";
	private static String MAPFRAME_FILEPATH = "./res/UI/frame.png";
	private static String LIGHTING_FILEPATH = "./res/lighting1.png";
	private static float MAP_PADDING = 0.95f;

	private int mapSizeInTiles; 
	private float aspectRatio;
	protected TextureCache textureCache = new TextureCache();
	
	public Drawing(int screenWidth, int screenHeight, int mapSizeInTiles, float aspectRation){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.mapSizeInTiles = mapSizeInTiles;
		this.aspectRatio = aspectRation;		
	}
	
	public void drawPlayer(Player player){
		boolean inversX = player.getDirection().equals("left"); 
		drawItemInMapTileRaster(0, 0, "./res/human.png", inversX);
		
		for(EquippableItem item: player.inventory.getEquipment()){
			drawItemInMapTileRaster(0, 0, item.getTextureFilePathCharacter(), inversX);
		}
	}
	
	public void drawItemInMapTileRaster(int posX, int posY, String filePath){
		drawItemInMapTileRaster(posX, posY, filePath, new Scaling(1,1), false);
	}
	
	public void drawItemInMapTileRaster(int posX, int posY, String filePath, boolean textureInversX){
		drawItemInMapTileRaster(posX, posY, filePath, new Scaling(1,1), textureInversX);
	}
	
	public void drawItemInMapTileRaster(int posX, int posY, String filePath,  Scaling scaling){
		drawItemInMapTileRaster(posX, posY, filePath, scaling, false);
	}
	
	public void drawItemInMapTileRaster(int posX, int posY, String filePath, Scaling scaling, boolean textureInversX){
		
		float tileSize = MAP_PADDING/(float)mapSizeInTiles;
		
		float x_fac = tileSize*scaling.getScaleX();
		float y_fac = tileSize*scaling.getScaleY();
		
		float x_offset = (2-(2/aspectRatio))/2;
		
		float x_pos = 2*posX*tileSize;
		float y_pos = 2*posY*tileSize;
		
		float[] vertices = new float[]{
				(-1*x_fac + x_pos)/aspectRatio - x_offset, y_fac + y_pos,
				(x_fac + x_pos)/aspectRatio - x_offset, y_fac + y_pos,
				(x_fac + x_pos)/aspectRatio - x_offset, -1*y_fac + y_pos,
				
				(x_fac + x_pos)/aspectRatio - x_offset, -1*y_fac + y_pos,
				(-1*x_fac + x_pos)/aspectRatio - x_offset, -1*y_fac + y_pos,
				(-1*x_fac + x_pos)/aspectRatio - x_offset, y_fac + y_pos
		};
		
		float[] texture = getDefaultTexture();	
		if(textureInversX) { texture = getInversXTexture(); }
		
		render(filePath, vertices, texture);
	}
	
	protected void render(String filePath, float[] vertices, float[] textureCoords) {
		render(textureCache.getTexture(filePath),  vertices,  textureCoords);
	}
	
	protected void render(Texture tex, float[] vertices, float[] textureCoords) {
		Model model = new Model(vertices, textureCoords);		
		tex.bind();
		model.render();
	}
	
	public void drawMap(GameMap gameMap, Player player){
		
		MapTile[][] mapTiles = gameMap.getMapTiles();
		for(int i = (int) (-1*Math.floor((double)mapSizeInTiles/2)); i < (int) Math.round((double)mapSizeInTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeInTiles/2)); j < (int) Math.round((double)mapSizeInTiles/2); j++){
				if(isValidMapTile(mapTiles, player.getPosX() + i, player.getPosY() + j)) {
					MapTile currentMapTile = mapTiles[player.getPosX() + i][player.getPosY() + j];
					String textureFilePath = currentMapTile.getTextureFilePath();
					if(!textureFilePath.isEmpty()) {
						drawItemInMapTileRaster(i, j, textureFilePath);
					}
				}
			}
		}
		
		for(int i = (int) (-1*Math.floor((double)mapSizeInTiles/2)); i < (int) Math.round((double)mapSizeInTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeInTiles/2)); j < (int) Math.round((double)mapSizeInTiles/2); j++){
				if(isValidMapTile(mapTiles, player.getPosX() + i, player.getPosY() + j)) {
					MapTile currentMapTile = mapTiles[player.getPosX() + i][player.getPosY() + j];
					String decorationFilePath = currentMapTile.getDecorationFilePath();
					Scaling decorationScaling = currentMapTile.getDecorationScaling();
					if(!decorationFilePath.isEmpty() && decorationScaling != null) {
						drawItemInMapTileRaster(i, j, decorationFilePath, decorationScaling);
					}
				}
			}
		}
		drawLightRadius();
		drawMapFrame();
		
	}
	
	private void drawMapFrame() {
		int padding = Math.round(screenHeight - Math.round(screenHeight*MAP_PADDING))/2;
		int frameWidth = Math.round(screenHeight-padding*2);
		drawRectangle(new Position(padding, padding), frameWidth, frameWidth, MAPFRAME_FILEPATH);
	}
	
	private boolean isValidMapTile(MapTile[][] mapTiles, int x, int y) {
		if(x>=0 && y>=0) {
			if(mapTiles.length > x) {
				if(mapTiles[0].length > y) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setMapSizeInTiles(int mapSizeInTiles) {
		this.mapSizeInTiles = mapSizeInTiles;
	}	
	
	public void drawBackground() {
		drawCenteredRectangle(screenWidth, screenHeight, BACKGROUND_FILEPATH);
	}

	public void drawLightRadius() {
		int padding = Math.round(screenHeight - Math.round(screenHeight*MAP_PADDING))/2;
		int frameWidth = Math.round(screenHeight-padding*2);
		drawRectangle(new Position(padding, padding), frameWidth, frameWidth, LIGHTING_FILEPATH);		
	}
	
	
	public void drawRectangle(Position pos, int width, int height, String filePath){
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)pos.getY()*2/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		
		float[] vertices = getVertices(posX, posY, facX, facY);
		float[] texture = getDefaultTexture();	
		render(filePath, vertices, texture);		
	}
	
	public void drawRectangle(Position pos, int width, int height, Texture tex){
		float posX = - 1 + (float)pos.getX()*2/(float)screenWidth;
		float posY = 1 - (float)pos.getY()*2/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		
		float[] vertices = getVertices(posX, posY, facX, facY);
		float[] texture = getDefaultTexture();	
		render(tex, vertices, texture);
	}
	
	public void drawCenteredRectangle(int width, int height, String filePath){

		float posX = -(float)width/(float)screenWidth;
		float posY = (float)height/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		
		float[] vertices = getVertices(posX, posY, facX, facY);
		float[] texture = getDefaultTexture();	
		render(filePath, vertices, texture);
	}

	private float[] getDefaultTexture() {
		return new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };
	}

	private float[] getInversXTexture() {
		return new float[] { 1,0,0,0,0,1,0,1,1,1,1,0};
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
