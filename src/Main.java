import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL;

public class Main {
	
	public static int SCREEN_WIDTH = 1400;	
	public static int SCREEN_HEIGHT = (int) SCREEN_WIDTH * 9/16;
	private static boolean IS_FULLSCREEN =  false;
	
	public static void main(String[] args) {
		showWindow();
	}
	
	private static void showWindow(){
		if(!glfwInit()){
			throw new IllegalStateException("Failed to initalize glfw");			
		}
		
		long window = glfwCreateWindow(SCREEN_WIDTH,SCREEN_HEIGHT, "My Game", IS_FULLSCREEN ? glfwGetPrimaryMonitor() : 0, 0);
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create Window");
		}
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Game theGame = new Game(SCREEN_WIDTH, SCREEN_HEIGHT, window);
		
		InputHandler.init(window);
		
		glfwSetMouseButtonCallback(window, InputHandler.mouse);
		glfwSetKeyCallback(window, InputHandler.keyboard);
		glfwSetCursorPosCallback(window, InputHandler.cursor);
		
		UserInteractions userInteractions = new UserInteractions(theGame);
		
		theGame.updateAll();
		int fps = 0;
		int frames = 0;
		double startTime = System.currentTimeMillis();

		while(glfwWindowShouldClose(window) != true ){
			
			InputHandler.update();
			glfwPollEvents();
			userInteractions.update();
			theGame.updateAll();
			frames ++;
			double endTime = System.currentTimeMillis();
			if(endTime - startTime >= 1000) {
				userInteractions.updateIntervalSecond();
				fps = frames;
				frames = 0;
				startTime = endTime;
				theGame.setFps(fps);
			}
		}
		
		glfwTerminate();
	}	
		
}
