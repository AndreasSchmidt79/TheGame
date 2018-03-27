package drawing;

import gameMap.GameMap;
import gameMap.MapTile;
import gameMap.Scaling;
import inventory.EquippableItem;
import player.Player;

public class Drawing {
	
	private int screenWidth;
	private int screenHeight;
	private static String BACKGROUND_FILEPATH = "./res/UI/bricks_bg.png";
	private static String MAPFRAME_FILEPATH = "./res/UI/frame.png";
	private static float MAP_PADDING = 0.95f;
	
	private int mapSizeInTiles; 
	private float aspectRatio;
	
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
		//float tileSize = 1/(float)mapSizeInTiles;
		
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
		
		float[] texture = new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };	
		if(textureInversX) { texture = new float[] { 1,0,0,0,0,1,0,1,1,1,1,0}; }
		
		renderVertices(filePath, vertices, texture);
	}
	
	public void drawQuadWithVertices(String filePath){
		float[] vertices = new float[]{ -1,1,1,1,1,-1,1,-1,-1,-1,-1,1 };
		float[] texture = new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };	
		renderVertices(filePath, vertices, texture);
	}

	private void renderVertices(String filePath, float[] vertices, float[] texture) {
		Model model = new Model(vertices, texture);		
		Texture tex = new Texture(filePath);
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
		drawMapFrame();
	}
	
	private void drawMapFrame() {
		
		float x = MAP_PADDING/aspectRatio;
		float y = MAP_PADDING;
		
		float x_offset = (2-(2/aspectRatio))/2;
		
		float[] vertices = new float[]{ -1*x-x_offset,1*y,1*x-x_offset,1*y,1*x-x_offset,-1*y,1*x-x_offset,-1*y,-1*x-x_offset,-1*y,-1*x-x_offset,1*y };
		float[] texture = new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };	
		renderVertices(MAPFRAME_FILEPATH, vertices, texture);
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
		drawQuadWithVertices(BACKGROUND_FILEPATH);
	}
	
	public void drawRectangle(Position pos, int width, int height, String filePath){
		
		float posX = -1 + pos.getX()/(float)screenWidth;
		float posY = 1 - pos.getY()/(float)screenHeight;
		float facX = (float)width/(float)screenWidth;
		float facY = (float)height/(float)screenHeight;
		
		float[] vertices = new float[]{ 
				0 + posX, 		 -1 * facY + posY,
				1 * facX + posX, -1 * facY + posY,
				1 * facX + posX, 0 + posY,
				
				1 * facX + posX, 0 + posY,
				0 + posX, 		 0 + posY,
				0 + posX, 		 -1 * facY + posY };
		
		float[] texture = new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };	
	
		renderVertices(filePath, vertices, texture);
	}	

}
