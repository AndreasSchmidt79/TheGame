import Drawing.Drawing;
import GameMap.*;
import Player.Player;


public class Game {
	private static int MAP_SIZE_IN_TILES = 9;
	private GameMap gameMap = null;
	private MapTile[][] mapTiles = null;
	private Player player;
	Drawing drawing;
	
	
	public Game() {
		initGame();
	}
	
	private void initGame(){
		drawing = new Drawing();
		drawing.setMapSizeInTiles(MAP_SIZE_IN_TILES);
		MapGenerator mapGenerator = new MapGenerator();
		gameMap = mapGenerator.getGameMap();				
		mapTiles = gameMap.getMapTiles();
		player = new Player(8,8);	
	}

	public void updateAll() {
		drawing.drawBackgroundMap();
		drawing.drawMap(gameMap, player);
		drawing.drawPlayer(player);			
	}
	
	public void setPlayerDirection(String direction){
		this.player.setDirection(direction);
	}
	
	public void update(String direction){
		if(direction == "left"){
			setPlayerDirection("left");
			if(mapTiles[player.getPosX()-1][player.getPosY()].isPassable()){
				player.setPosX(player.getPosX()-1);
			}
		}
		if(direction == "right"){
			setPlayerDirection("right");
			if(mapTiles[player.getPosX()+1][player.getPosY()].isPassable()){
				player.setPosX(player.getPosX()+1);
			}
		}
		if(direction == "up"){
			if(mapTiles[player.getPosX()][player.getPosY()+1].isPassable()){
				player.setPosY(player.getPosY()+1);
			}
		}
		if(direction == "down"){
			if(mapTiles[player.getPosX()][player.getPosY()-1].isPassable()){
				player.setPosY(player.getPosY()-1);
			}
		}
		
	}
	
	
}
