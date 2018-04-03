package drawing.map;

import drawing.Drawing;
import drawing.Position;
import drawing.Texture;
import gameMap.GameMap;
import gameMap.MapTile;
import gameMap.Scaling;
import inventory.EquippableItem;
import player.Player;

public class MapDrawing extends Drawing{
	
	private static final String HUMAN_FILEPATH = "./res/human.png";
	private static final String MAPFRAME_FILEPATH = "./res/UI/frame.png";
	private static final String LIGHTING_FILEPATH = "./res/lighting1.png";
	private static final int MAP_PADDING = 25;	
	private GameMap gameMap;
	private MapTile[][] mapTiles = null;

	public MapDrawing(int screenWidth, int screenHeight, int mapSizeInTiles, GameMap gameMap) {
		super(screenWidth, screenHeight, mapSizeInTiles);
		this.gameMap = gameMap;
		this.mapTiles = gameMap.getMapTiles();
	}
	
	public void drawPlayer(Player player){
		boolean inversX = player.getDirection().equals("left"); 
		Position playerPos = new Position((mapSizeInTiles-1)/2, (mapSizeInTiles-1)/2);
		drawItemInMapTileRaster(playerPos, HUMAN_FILEPATH, inversX);
		
		for(EquippableItem item: player.inventory.getEquipment()){
			drawItemInMapTileRaster(playerPos, item.getTextureFilePathCharacter(), inversX);
		}
	}
	
	private void drawMapFrame() {
		int length = Math.round(screenHeight-MAP_PADDING*2);
		drawRectangle(new Position(MAP_PADDING, MAP_PADDING), length, length, textureCache.getTexture(MAPFRAME_FILEPATH));
	}
	
	public void drawLightRadius() {
		int length = Math.round(screenHeight-MAP_PADDING*2);
		drawRectangle(new Position(MAP_PADDING, MAP_PADDING), length, length, textureCache.getTexture(LIGHTING_FILEPATH));		
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
	
	public void drawMap(Player player){
		
		for(int i = 0; i < mapSizeInTiles; i++){
			for(int j = 0; j < mapSizeInTiles; j++){
				Position mapTilePos = new Position(player.getPosX() + i - (mapSizeInTiles-1)/2, player.getPosY() + j - (mapSizeInTiles-1)/2);
				if(isValidMapTile(mapTiles, mapTilePos.getX(), mapTilePos.getY())) {
					MapTile currentMapTile = mapTiles[mapTilePos.getX()][mapTilePos.getY()];
					String textureFilePath = currentMapTile.getTextureFilePath();
					if(!textureFilePath.isEmpty()) {
						drawItemInMapTileRaster(new Position(i, j), textureFilePath);
					}
				}
			}
		}
		
		for(int i = 0; i < mapSizeInTiles; i++){
			for(int j = 0; j < mapSizeInTiles; j++){
				Position mapTilePos = new Position(player.getPosX() + i - (mapSizeInTiles-1)/2, player.getPosY() + j - (mapSizeInTiles-1)/2);
				if(isValidMapTile(mapTiles, mapTilePos.getX(), mapTilePos.getY())) {
					MapTile currentMapTile = mapTiles[mapTilePos.getX()][mapTilePos.getY()];
					String decorationFilePath = currentMapTile.getDecorationFilePath();
					Scaling decorationScaling = currentMapTile.getDecorationScaling();
					if(!decorationFilePath.isEmpty() && decorationScaling != null) {
						drawItemInMapTileRaster(new Position(i, j), decorationFilePath, decorationScaling);
					}
				}
			}
		}
		drawLightRadius();
		drawMapFrame();
		
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath){
		drawItemInMapTileRaster(pos, filePath, new Scaling(1,1), false);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, boolean textureInversX){
		drawItemInMapTileRaster(pos, filePath, new Scaling(1,1), textureInversX);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, Scaling scaling){
		drawItemInMapTileRaster(pos, filePath, scaling, false);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, Scaling scaling, boolean textureInversX){
		int mapLength = Math.round(screenHeight-MAP_PADDING*2);
		int tileLength = Math.round(mapLength/mapSizeInTiles);
		Position mapTilePos = new Position(MAP_PADDING + pos.getX()*tileLength, MAP_PADDING + pos.getY()*tileLength);
		drawRectangle(mapTilePos, tileLength, tileLength, textureCache.getTexture(filePath), textureInversX, scaling);
	}

}
