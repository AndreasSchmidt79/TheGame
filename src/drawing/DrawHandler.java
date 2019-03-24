package drawing;

import drawing.button.AbstractButton;
import drawing.character.CharacterDrawing;
import drawing.inventory.InventoryDrawing;
import drawing.map.MapDrawing;
import drawing.text.TextDrawing;
import drawing.uiDrawing.UIDrawing;
import game.DisplayState;
import game.GameState;
import game.GlobalState;
import gameMap.GameMap;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

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
            int fps,
            String infoText,
            HashMap<String, AbstractButton> buttons) {

        glClear(GL_COLOR_BUFFER_BIT);

        uiDrawing.drawBackground(textureCache);

        if (currentGameState.getDisplayState() != null) {
            if (currentGameState.getDisplayState().equals(DisplayState.MAP)) {
                mapDrawing.drawMapPanel(player, currentGameMap, textureCache);
                mapDrawing.drawPlayer(player, textureCache);
            } else if (currentGameState.getDisplayState().equals(DisplayState.INVENTORY)) {
                inventoryDrawing.drawInventory(textureCache, player, buttons);
            }

            characterDrawing.drawCharacterPanel(textureCache, player, buttons);
            uiDrawing.drawInfoPanel(textureCache);

            if (!infoText.isEmpty()) {
                textDrawing.drawInfoPanelText(infoText, textureCache);
            }
        }
        if (currentGameState.getGlobalState() == GlobalState.MAINMENU) {
            uiDrawing.drawSemiTransparentPane(textureCache);
            uiDrawing.drawMainMenu(textureCache, currentGameState, buttons);
        }

        textDrawing.drawFPS(fps, textureCache);
        glfwSwapBuffers(glWindow);

    }


}
