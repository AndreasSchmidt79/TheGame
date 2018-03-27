import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import drawing.Drawing;
import drawing.Model;
import drawing.Position;
import drawing.Texture;


public class Main {
	
	private static int SCREEN_WIDTH = 1400;	
	private static int SCREEN_HEIGHT = (int) SCREEN_WIDTH * 9/16;
	
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
		
		Game theGame = new Game(SCREEN_WIDTH, SCREEN_HEIGHT, window, aspectRatio);
		UserInteractions userInteractions = new UserInteractions(theGame);
		theGame.updateAll();

		while(glfwWindowShouldClose(window) != true ){
			glfwPollEvents();
			userInteractions.update(window);

			
			// { REMOVE
			/*glClear(GL_COLOR_BUFFER_BIT);
			Drawing drawing = new Drawing(SCREEN_WIDTH, SCREEN_HEIGHT, 13,aspectRatio);
			drawing.drawRectangle(new Position(100,100), 100, 100, "./res/MapTiles/sand1.png");
			glfwSwapBuffers(window);*/
			// REMOVE }
		}
		
		glfwTerminate();
	}
		
}
