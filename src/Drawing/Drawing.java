package Drawing;
import GameMap.GameMap;
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
	
	public void drawBackgroundMap(){
		for(int i = -4; i < 5; i++){
			for(int j = -4; j < 5; j++){
				drawQuadWithVertices(i, j, "./res/MapTile_1_grass.png");
			}
		}
	}
	
	public void drawMap(GameMap gameMap, Player player){
		int dimensions = gameMap.getDimensions();
		
		for(int i = 0; i < dimensions; i++){
			for(int j = 0; j < dimensions; j++){
				drawQuadWithVertices(i - player.getPosX(), j - player.getPosY(), gameMap.getMapTiles()[i][j].getTextureFilePath());
			}
		}
	}

	public void setMapSizeInTiles(int mapSizeInTiles) {
		this.mapSizeInTiles = mapSizeInTiles;
	}
	

}
