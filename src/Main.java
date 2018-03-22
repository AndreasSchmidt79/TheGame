import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;


public class Main {
	
	private static int SCREEN_WIDTH = 1400;
	private static int SCREEN_HEIGHT = 788;
	
	public static void main(String[] args) {
		showWindow();
	}
	
	private static void showWindow(){
		if(!glfwInit()){
			throw new IllegalStateException("Failed to initalize glfw");			
		}
		
		//FullScreen
		//long window = glfwCreateWindow(SCREEN_WIDTH,SCREEN_HEIGHT, "My Game", glfwGetPrimaryMonitor(), 0); 
		long window = glfwCreateWindow(SCREEN_WIDTH,SCREEN_HEIGHT, "My Game", 0, 0);
		float aspectRatio = (float)SCREEN_WIDTH/(float)SCREEN_HEIGHT;
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create Window");
		}
		
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		Game theGame = new Game(window, aspectRatio);
		glClear(GL_COLOR_BUFFER_BIT);
		theGame.updateAll();
		glfwSwapBuffers(window);
		
		while(glfwWindowShouldClose(window) != true ){
			
			glfwPollEvents();
			
			if(glfwGetKey(window, GLFW_KEY_A) == GL_TRUE || glfwGetKey(window, GLFW_KEY_LEFT) == GL_TRUE){
				theGame.update("left");
			}
			if(glfwGetKey(window, GLFW_KEY_D) == GL_TRUE || glfwGetKey(window, GLFW_KEY_RIGHT) == GL_TRUE){
				theGame.update("right");
			}
			if(glfwGetKey(window, GLFW_KEY_S) == GL_TRUE || glfwGetKey(window, GLFW_KEY_DOWN) == GL_TRUE){
				theGame.update("down");
			}
			if(glfwGetKey(window, GLFW_KEY_W) == GL_TRUE || glfwGetKey(window, GLFW_KEY_UP) == GL_TRUE){
				theGame.update("up");
			}
			
			if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
				glfwTerminate();
			}			
			
		}
		
		glfwTerminate();
	}
		
}
