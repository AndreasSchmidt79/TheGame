import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class UserInteractions {
	
	Game game;

	public UserInteractions(Game game) {
		this.game = game;
	}
	
	public void update(long window) {
		if(glfwGetKey(window, GLFW_KEY_A) == GL_TRUE || glfwGetKey(window, GLFW_KEY_LEFT) == GL_TRUE){
			game.updatePlayerMovement("left");
		}
		if(glfwGetKey(window, GLFW_KEY_D) == GL_TRUE || glfwGetKey(window, GLFW_KEY_RIGHT) == GL_TRUE){
			game.updatePlayerMovement("right");
		}
		if(glfwGetKey(window, GLFW_KEY_S) == GL_TRUE || glfwGetKey(window, GLFW_KEY_DOWN) == GL_TRUE){
			game.updatePlayerMovement("down");
		}
		if(glfwGetKey(window, GLFW_KEY_W) == GL_TRUE || glfwGetKey(window, GLFW_KEY_UP) == GL_TRUE){
			game.updatePlayerMovement("up");
		}
		
		if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
			glfwTerminate();
		}			
	}
	
	

}
