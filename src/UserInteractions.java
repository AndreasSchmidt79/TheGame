import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import java.util.ArrayList;
import drawing.Button;


public class UserInteractions{
	Game game;

	public UserInteractions(Game game) {
		this.game = game;
	}
	
	public void update(long window) {
		
		ArrayList<Button> buttons = game.getActiveButtons();
		
		if(game.currentGameState == game.GAME_STATE_MAINMENU) {
			if(InputHandler.keyReleased(GLFW_KEY_ESCAPE)) {
				game.setCurrentGameState(game.GAME_STATE_MAP);
			}
			
			if(InputHandler.mouseButtonReleased(GLFW_MOUSE_BUTTON_LEFT)) {
				for(Button button: buttons){
					if(button.positionIsInButtonRange(InputHandler.getMousePosition())) {
						switch(button.getAction()) {
							case Button.CONTINUE:
								game.setCurrentGameState(game.GAME_STATE_MAP);
								break;
							case Button.NEW_GAME:
								game.startNewGame();
								game.setCurrentGameState(game.GAME_STATE_MAP);
								break;
							case Button.EXIT:
								glfwTerminate();
								break;
						}
					}
				}
			}
			
		} else if(game.currentGameState == game.GAME_STATE_MAP) {
			if(InputHandler.keyReleased(GLFW_KEY_A) || InputHandler.keyReleased(GLFW_KEY_LEFT)){
				game.updatePlayerMovement("left");
			}
			if(InputHandler.keyReleased(GLFW_KEY_D) || InputHandler.keyReleased(GLFW_KEY_RIGHT)){
				game.updatePlayerMovement("right");
			}
			if(InputHandler.keyReleased(GLFW_KEY_S) || InputHandler.keyReleased(GLFW_KEY_DOWN)){
				game.updatePlayerMovement("down");
			}
			if(InputHandler.keyReleased(GLFW_KEY_W) || InputHandler.keyReleased(GLFW_KEY_UP)){
				game.updatePlayerMovement("up");
			}
			if(InputHandler.keyReleased(GLFW_KEY_ESCAPE)) {
				game.setCurrentGameState(game.GAME_STATE_MAINMENU);
			}
		} else if(game.currentGameState == game.GAME_STATE_COMBAT) {
			//TBD
		}

					
	}

}
