package Drawing;

import GameMap.GameMap;
import GameMap.MapTile;
import GameMap.Scaling;
import Player.Player;
import inventory.EquippableItem;


public class Drawing {
	
	private int mapSizeInTiles;
	private float aspectRatio;
	
	public Drawing(int mapSizeInTiles, float aspectRation){
		this.mapSizeInTiles = mapSizeInTiles;
		this.aspectRatio = aspectRation;
	}
	
	public void drawPlayer(Player player){
		boolean inversX = player.getDirection().equals("left"); 
		drawIteminMapTileRaster(0, 0, "./res/human.png", inversX);
		
		for(EquippableItem item: player.inventory.getEquipment()){
			drawIteminMapTileRaster(0, 0, item.getTextureFilePathCharacter(), inversX);
		}
	}
	
	public void drawIteminMapTileRaster(int posX, int posY, String filePath){
		drawIteminMapTileRaster(posX, posY, filePath, new Scaling(1,1), false);
	}
	
	public void drawIteminMapTileRaster(int posX, int posY, String filePath, boolean textureInversX){
		drawIteminMapTileRaster(posX, posY, filePath, new Scaling(1,1), textureInversX);
	}
	
	public void drawIteminMapTileRaster(int posX, int posY, String filePath,  Scaling scaling){
		drawIteminMapTileRaster(posX, posY, filePath, scaling, false);
	}
	
	public void drawIteminMapTileRaster(int posX, int posY, String filePath, Scaling scaling, boolean textureInversX){
		
		float tileSize = 2.0f/(float)mapSizeInTiles;
		
		float[] vertices = new float[]{

				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize
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
	
	public void drawMap(GameMap gameMap, Player player, int mapSizeTiles){
		//mapSizeTiles = mapSizeTiles - 2;
		MapTile[][] mapTiles = gameMap.getMapTiles();
		for(int i = (int) (-1*Math.floor((double)mapSizeTiles/2)); i < (int) Math.round((double)mapSizeTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeTiles/2)); j < (int) Math.round((double)mapSizeTiles/2); j++){
				if(isValidMapTile(mapTiles, player.getPosX() + i, player.getPosY() + j)) {
					MapTile currentMapTile = mapTiles[player.getPosX() + i][player.getPosY() + j];
					String textureFilePath = currentMapTile.getTextureFilePath();
					if(!textureFilePath.isEmpty()) {
						drawIteminMapTileRaster(i, j, textureFilePath);
					}
					
				}
			}
		}
		
		for(int i = (int) (-1*Math.floor((double)mapSizeTiles/2)); i < (int) Math.round((double)mapSizeTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeTiles/2)); j < (int) Math.round((double)mapSizeTiles/2); j++){
				if(isValidMapTile(mapTiles, player.getPosX() + i, player.getPosY() + j)) {
					MapTile currentMapTile = mapTiles[player.getPosX() + i][player.getPosY() + j];
					String decorationFilePath = currentMapTile.getDecorationFilePath();
					Scaling decorationScaling = currentMapTile.getDecorationScaling();
					if(!decorationFilePath.isEmpty() && decorationScaling != null) {
						drawIteminMapTileRaster(i, j, decorationFilePath, decorationScaling);
					}
				}
			}
		}
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
	
	public void drawBackground(String filePath) {
		int duplicateBackgroundCount = 6;
		for(int i = (int) (-1*Math.floor((double)duplicateBackgroundCount)); i < (int) Math.round((double)duplicateBackgroundCount); i++){
			for(int j = (int) (-1*Math.floor((double)duplicateBackgroundCount)); j < (int) Math.round((double)duplicateBackgroundCount); j++){		
				drawBackgroundPart(i,j,filePath, duplicateBackgroundCount);						
			}			
		}
	}
	
	public void drawBackgroundPart(int posX, int posY, String filePath, int duplicateBackgroundCount){
		float tileSize = 2.0f/(float)duplicateBackgroundCount;
		float[] vertices = new float[]{
				(-0.5f*tileSize + posX*tileSize)/aspectRatio, 0.5f*tileSize + posY*tileSize,
				(0.5f*tileSize + posX*tileSize)/aspectRatio, 0.5f*tileSize + posY*tileSize,
				(0.5f*tileSize + posX*tileSize)/aspectRatio, -0.5f*tileSize + posY*tileSize,
				
				(0.5f*tileSize + posX*tileSize)/aspectRatio, -0.5f*tileSize + posY*tileSize,
				(-0.5f*tileSize + posX*tileSize)/aspectRatio, -0.5f*tileSize + posY*tileSize,
				(-0.5f*tileSize + posX*tileSize)/aspectRatio, 0.5f*tileSize + posY*tileSize
		};
		float[] texture = new float[] { 0,0,1,0,1,1,1,1,0,1,0,0 };	
		renderVertices(filePath, vertices, texture);
	}

}
