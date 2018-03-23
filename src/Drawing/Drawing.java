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
		drawQuadWithVertices(0, 0, "./res/human.png", inversX);
		
		for(EquippableItem item: player.inventory.getEquipment()){
			drawQuadWithVertices(0, 0, item.getTextureFilePathCharacter(), inversX);
		}
		
		//drawQuadWithVertices(0, 0, "./res/LeatherArmor.png", inversX);
		//drawQuadWithVertices(0, 0, "./res/ShortSword.png", inversX);
		//drawQuadWithVertices(0, 0, "./res/SteelHelm.png", inversX);
	}
	
	public void drawQuadWithVertices(int posX, int posY, String filePath){
		drawQuadWithVertices(posX, posY, filePath, new Scaling(1,1), false);
	}
	
	public void drawQuadWithVertices(int posX, int posY, String filePath, boolean textureInversX){
		drawQuadWithVertices(posX, posY, filePath, new Scaling(1,1), textureInversX);
	}
	
	public void drawQuadWithVertices(int posX, int posY, String filePath,  Scaling scaling){
		drawQuadWithVertices(posX, posY, filePath, scaling, false);
	}
	
	public void drawQuadWithVertices(int posX, int posY, String filePath, Scaling scaling, boolean textureInversX){
		
		float tileSize = 2.0f/(float)mapSizeInTiles;
		
		float[] vertices = new float[]{

				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				
				(0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				(-0.5f*tileSize*scaling.getScaleX() + posX*tileSize)/aspectRatio, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
		};
		
		float[] texture = new float[] {
			0,0,
			1,0,
			1,1,			
			1,1,
			0,1,
			0,0
		};	
		
		if(textureInversX) {
			texture = new float[] {
					1,0,
					0,0,
					0,1,			
					0,1,
					1,1,
					1,0
				};	
		}
		
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
						drawQuadWithVertices(i, j, textureFilePath);
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
						drawQuadWithVertices(i, j, decorationFilePath, decorationScaling);
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

}
