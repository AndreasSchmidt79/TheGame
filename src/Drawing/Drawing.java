package Drawing;
import GameMap.GameMap;
import GameMap.MapTile;
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
		
		float tileSize = 2.0f/(float)mapSizeInTiles;
		
		float[] vertices = new float[]{

				-0.5f*tileSize + posX*tileSize, 0.5f*tileSize + posY*tileSize,
				0.5f*tileSize + posX*tileSize, 0.5f*tileSize + posY*tileSize,
				0.5f*tileSize + posX*tileSize, -0.5f*tileSize + posY*tileSize,
				
				0.5f*tileSize + posX*tileSize, -0.5f*tileSize + posY*tileSize,
				-0.5f*tileSize + posX*tileSize, -0.5f*tileSize + posY*tileSize,
				-0.5f*tileSize + posX*tileSize, 0.5f*tileSize + posY*tileSize,
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
	
	public void drawBackgroundMap(int mapSizeTiles){
		for(int i = (int) (-1*Math.floor((double)mapSizeTiles/2)); i < (int) Math.round((double)mapSizeTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeTiles/2)); j < (int) Math.round((double)mapSizeTiles/2); j++){
				drawQuadWithVertices(i, j, "./res/MapTile_1_grass.png");
			}
		}
	}
	
	public void drawMap(GameMap gameMap, Player player, int mapSizeTiles){
		//mapSizeTiles = mapSizeTiles - 2;
		for(int i = (int) (-1*Math.floor((double)mapSizeTiles/2)); i < (int) Math.round((double)mapSizeTiles/2); i++){
			for(int j = (int) (-1*Math.floor((double)mapSizeTiles/2)); j < (int) Math.round((double)mapSizeTiles/2); j++){
				String textureFilePath = getTextureFilePath(gameMap, player.getPosX() + i, player.getPosY() + j);
				if(textureFilePath != null) {
					drawQuadWithVertices(i, j, textureFilePath);
				}
			}
		}
	}
	
	private String getTextureFilePath(GameMap gameMap, int x, int y) {
		MapTile[][] mapTiles = gameMap.getMapTiles();
		if(x>=0 && y>=0) {
			if(mapTiles.length > x) {
				if(mapTiles[0].length > y) {
					return mapTiles[x][y].getTextureFilePath();
				}
			}
		}
		return null;
	}

	public void setMapSizeInTiles(int mapSizeInTiles) {
		this.mapSizeInTiles = mapSizeInTiles;
	}
	

}
