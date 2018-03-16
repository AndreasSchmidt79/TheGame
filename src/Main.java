import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;


public class Main {
	
	public static void main(String[] args) {
		showWindow();
	}
	
	private static void showWindow(){
		if(!glfwInit()){
			throw new IllegalStateException("Failed to initalize glfw");			
		}
		
		long window = glfwCreateWindow(800,800, "My Game", 0, 0);
		
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create Window");
		}
		
		
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		Game theGame = new Game();
		while(glfwWindowShouldClose(window) != true ){
			
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
		
			theGame.updateAll();
						
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
			
			glfwSwapBuffers(window);
		}
		
		glfwTerminate();
	}
		
}
