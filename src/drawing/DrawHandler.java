package drawing;

import drawing.button.Button;
import drawing.character.CharacterDrawing;
import drawing.inventory.InventoryDrawing;
import drawing.map.MapDrawing;
import drawing.text.TextDrawing;
import drawing.uiDrawing.UIDrawing;
import game.DisplayState;
import game.GameState;
import gameMap.GameMap;
import player.Player;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class DrawHandler {

    private TextureCache textureCache = new TextureCache();

    private BaseDrawing baseDrawing;
    private UIDrawing uiDrawing;
    private TextDrawing textDrawing;
    private CharacterDrawing characterDrawing;
    private InventoryDrawing inventoryDrawing;
    private MapDrawing mapDrawing;

    private long glWindow;

    public DrawHandler() {
    }

    public DrawHandler(int mapSizeInTiles, long window) {
        glWindow = window;
        baseDrawing = new BaseDrawing(mapSizeInTiles);

        textDrawing = new TextDrawing(baseDrawing);
        uiDrawing = new UIDrawing(baseDrawing, new TextDrawing(baseDrawing));
        characterDrawing = new CharacterDrawing(baseDrawing);
        inventoryDrawing = new InventoryDrawing(baseDrawing);
        mapDrawing = new MapDrawing(baseDrawing);
    }

    public void drawAll(
            Player player,
            GameMap currentGameMap,
            GameState currentGameState,
            DisplayState currentDisplayState,
            int fps,
            ArrayList<Button> activeButtons,
            String infoText,
            boolean newGameStarted
    ) {

        glClear(GL_COLOR_BUFFER_BIT);

        uiDrawing.drawBackground(textureCache);

        if(newGameStarted){
            if(currentDisplayState == DisplayState.MAP) {
                mapDrawing.drawMapPanel(player, currentGameMap, textureCache);
                mapDrawing.drawPlayer(player, textureCache);
            }
            else if(currentDisplayState == DisplayState.INVENTORY) {
                inventoryDrawing.drawInventory(player, activeButtons, textureCache);
            }

            characterDrawing.drawCharacterPanel(player, activeButtons, textureCache);
            uiDrawing.drawInfoPanel(textureCache);

            if(!infoText.isEmpty()) {
                textDrawing.drawInfoPanelText(infoText, textureCache);
            }
        }
        if(currentGameState == GameState.MAINMENU) {
            uiDrawing.drawSemiTransparentPane(textureCache);
            uiDrawing.drawMainMenu(activeButtons, textureCache);
        }

        textDrawing.drawFPS(fps, textureCache);
        glfwSwapBuffers(glWindow);

    }


}
