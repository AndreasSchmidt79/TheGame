package userInteractions;

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

import java.util.ArrayList;

import drawing.button.ButtonAction;
import drawing.button.Button;
import helper.Direction;
import inputHandler.InputHandler;


public class UserInteractions{

	public UserInteractions() {

	}
	
	public ButtonAction getButtonPressed(ArrayList<Button> buttons) {
		
		for(Button button: buttons){
			if(button.positionIsInButtonRange(InputHandler.getMousePosition())) {
				button.setButtonHover();
			}
			else {
				button.setButtonDefault();
			}
		}

		if(InputHandler.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
			for(Button button: buttons){
				if(button.positionIsInButtonRange(InputHandler.getMousePosition())) {
					button.setButtonClicked();
				}
			}
		}

		if(InputHandler.mouseButtonReleased(GLFW_MOUSE_BUTTON_LEFT)) {
			for(Button button: buttons){
				if(button.positionIsInButtonRange(InputHandler.getMousePosition())) {
					button.setButtonDefault();
					return button.getAction();
				}
			}
		}
		return null;
	}

	public Direction getPlayerMovement() {
		if(InputHandler.keyReleased(GLFW_KEY_A) || InputHandler.keyReleased(GLFW_KEY_LEFT)){
			 return Direction.LEFT;
		}
		if(InputHandler.keyReleased(GLFW_KEY_D) || InputHandler.keyReleased(GLFW_KEY_RIGHT)){
			return Direction.RIGHT;
		}
		if(InputHandler.keyReleased(GLFW_KEY_S) || InputHandler.keyReleased(GLFW_KEY_DOWN)){
			return Direction.DOWN;
		}
		if(InputHandler.keyReleased(GLFW_KEY_W) || InputHandler.keyReleased(GLFW_KEY_UP)){
			return Direction.UP;
		}

		return null;
	}

	public boolean isEscapeKeyPressed() {
		return InputHandler.keyReleased(GLFW_KEY_ESCAPE);
	}

}
