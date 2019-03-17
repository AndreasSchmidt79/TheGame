package drawing.map;


import drawing.*;
import gameMap.GameMap;
import gameMap.MapTile;
import gameMap.Scaling;
import helper.RandomHelper;
import inventory.EquippableItem;
import mob.Mob;
import player.Player;

public class MapDrawing{
	
	private MapTile[][] mapTiles = null;
	private BaseDrawing baseDrawing;

	public MapDrawing(BaseDrawing baseDrawing) {
		this.baseDrawing = baseDrawing;
	}
	
	public void drawPlayer(Player player, TextureCache textureCache){
		boolean inversX = player.getFaceDirection().equals("left");
		Position playerPos = new Position((Screen.MAP_SIZE_IN_TILES-1)/2, (Screen.MAP_SIZE_IN_TILES-1)/2);
		drawItemInMapTileRaster(playerPos, TextureFilepath.MOB_PLAYER.getFilepath(), inversX, textureCache);
		
		for(EquippableItem item: player.inventory.getEquipment()){
			drawItemInMapTileRaster(playerPos, item.getTextureFilePathEquipped().getFilepath(), inversX, textureCache);
		}
	}
	
	private void drawMapFrame(TextureCache textureCache) {
		int length = Math.round(Screen.HEIGHT-Screen.PADDING*2);
		baseDrawing.drawRectangle(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_PANEL_FRAME.getFilepath()));
	}
	
	public void drawLightRadius(float lightStrength, boolean flicker, TextureCache textureCache) {
		int length = Math.round(Screen.HEIGHT-Screen.PADDING*2);
		if(flicker) {
			lightStrength += RandomHelper.getRandomLightStrength();
		}
		baseDrawing.drawRectangleZoomTexture(new Position(Screen.PADDING, Screen.PADDING), length, length, textureCache.getTexture(TextureFilepath.UI_LIGHTING.getFilepath()), lightStrength);
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
	
	public void drawMapPanel(Player player, GameMap gameMap, TextureCache textureCache){
		this.mapTiles = gameMap.getMapTiles();
		
		for(int i = 0; i < Screen.MAP_SIZE_IN_TILES; i++){
			for(int j = 0; j < Screen.MAP_SIZE_IN_TILES; j++){
				Position mapTilePos = new Position(player.getPos().getX() + i - (Screen.MAP_SIZE_IN_TILES-1)/2, player.getPos().getY() + j - (Screen.MAP_SIZE_IN_TILES-1)/2);
				if(isValidMapTile(mapTiles, mapTilePos.getX(), mapTilePos.getY())) {
					MapTile currentMapTile = mapTiles[mapTilePos.getX()][mapTilePos.getY()];
					String textureFilePath = currentMapTile.getTextureFilePath();
					if(!textureFilePath.isEmpty()) {
						drawItemInMapTileRaster(new Position(i, j), textureFilePath, textureCache);
					}
				}
			}
		}
		
		for(int i = 0; i < Screen.MAP_SIZE_IN_TILES; i++){
			for(int j = 0; j < Screen.MAP_SIZE_IN_TILES; j++){
				Position mapTilePos = new Position(player.getPos().getX() + i - (Screen.MAP_SIZE_IN_TILES-1)/2, player.getPos().getY() + j - (Screen.MAP_SIZE_IN_TILES-1)/2);
				if(isValidMapTile(mapTiles, mapTilePos.getX(), mapTilePos.getY())) {
					MapTile currentMapTile = mapTiles[mapTilePos.getX()][mapTilePos.getY()];
					String decorationFilePath = currentMapTile.getDecorationFilePath();
					Scaling decorationScaling = currentMapTile.getDecorationScaling();
					if(!decorationFilePath.isEmpty() && decorationScaling != null) {
						drawItemInMapTileRaster(new Position(i, j), decorationFilePath, decorationScaling, textureCache);
					}
					if(currentMapTile.getMob()!=null) {
						drawMob(currentMapTile.getMob(), new Position(i, j), textureCache);
						gameMap.addMobToMobsToMoveList(currentMapTile.getMob());
					}
				}
			}
		}
		
		drawLightRadius(gameMap.getLightStrength(), gameMap.isFlicker(), textureCache);
		drawMapFrame(textureCache);
	}
	
	private void drawMob(Mob mob, Position pos, TextureCache textureCache) {
		drawItemInMapTileRaster(pos, mob.getFilePath(), textureCache);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, TextureCache textureCache){
		drawItemInMapTileRaster(pos, filePath, new Scaling(1,1), false, textureCache);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, boolean textureInversX, TextureCache textureCache){
		drawItemInMapTileRaster(pos, filePath, new Scaling(1,1), textureInversX, textureCache);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, Scaling scaling, TextureCache textureCache){
		drawItemInMapTileRaster(pos, filePath, scaling, false, textureCache);
	}
	
	public void drawItemInMapTileRaster(Position pos, String filePath, Scaling scaling, boolean textureInversX, TextureCache textureCache){
		int mapLength = Math.round(Screen.HEIGHT-Screen.PADDING*2);
		int tileLength = Math.round(mapLength/Screen.MAP_SIZE_IN_TILES);
		Position mapTilePos = new Position(Screen.PADDING + pos.getX()*tileLength, Screen.PADDING + pos.getY()*tileLength);
		baseDrawing.drawRectangle(mapTilePos, tileLength, tileLength, textureCache.getTexture(filePath), textureInversX, scaling);
	}

}
