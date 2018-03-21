package Drawing;

import GameMap.GameMap;
import GameMap.MapTile;
import GameMap.Scaling;
import Player.Player;


public class Drawing {
	
	private int mapSizeInTiles;
	
	public Drawing(){
		
	}
	
	public void drawPlayer(Player player){
		String image_suffix = player.getDirection().equals("left") ? "_l" : ""; 
		drawQuadWithVertices(0, 0, "./res/human" + image_suffix + ".png");
		drawQuadWithVertices(0, 0, "./res/LeatherArmor" + image_suffix + ".png");
		drawQuadWithVertices(0, 0, "./res/ShortSword" + image_suffix + ".png");
		drawQuadWithVertices(0, 0, "./res/SteelHelm" + image_suffix + ".png");
	}
	
	public void drawQuadWithVertices(int posX, int posY, String filePath){
		drawQuadWithVertices(posX, posY, filePath, new Scaling(1,1));
	}
	
	
	public void drawQuadWithVertices(int posX, int posY, String filePath, Scaling scaling){
		
		float tileSize = 2.0f/(float)mapSizeInTiles;
		
		float[] vertices = new float[]{

				-0.5f*tileSize*scaling.getScaleX() + posX*tileSize, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				0.5f*tileSize*scaling.getScaleX() + posX*tileSize, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				0.5f*tileSize*scaling.getScaleX() + posX*tileSize, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				
				0.5f*tileSize*scaling.getScaleX() + posX*tileSize, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				-0.5f*tileSize*scaling.getScaleX() + posX*tileSize, -0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
				-0.5f*tileSize*scaling.getScaleX() + posX*tileSize, 0.5f*tileSize*scaling.getScaleY() + posY*tileSize,
		};
		
		float[] texture = new float[] {
			0,0,
			1,0,
			1,1,			
			1,1,
			0,1,
			0,0
		};		
		
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
