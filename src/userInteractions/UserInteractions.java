package userInteractions;

import java.util.HashMap;

import drawing.Position;
import drawing.TextureFilepath;
import drawing.button.*;
import game.DisplayState;
import game.GameState;
import game.GlobalState;
import game.UserAction;
import gameMap.GameMap;
import gameMap.MapTile;
import helper.Direction;
import inputHandler.InputHandler;
import player.Player;

import static org.lwjgl.glfw.GLFW.*;

public class UserInteractions {

    private HashMap<String, AbstractButton> buttons = new HashMap<>();

    public UserInteractions() {
        buttons.put("inventory", new IconButton(UserAction.INVENTORY, new Position(0, 0), 50, 50, TextureFilepath.UI_ICON_EQUIPMENT));
        buttons.put("closeinv", new CloseButton(UserAction.CLOSE_INVENTORY, new Position(0, 0), 30, 30));

        TextButton continueButton = new TextButton(UserAction.CONTINUE, new Position(0, 0), AbstractButton.MAIN_MENU_BUTTON_WIDTH, AbstractButton.MAIN_MENU_BUTTON_HEIGHT, "Continue");
        TextButton newGameButton = new TextButton(UserAction.NEW_GAME, new Position(0, 0), AbstractButton.MAIN_MENU_BUTTON_WIDTH, AbstractButton.MAIN_MENU_BUTTON_HEIGHT, "New game");
        TextButton exitButton = new TextButton(UserAction.EXIT, new Position(0, 0), AbstractButton.MAIN_MENU_BUTTON_WIDTH, AbstractButton.MAIN_MENU_BUTTON_HEIGHT, "Exit");

        buttons.put("continue", continueButton);
        buttons.put("newgame", newGameButton);
        buttons.put("exit", exitButton);

    }

    public void checkUserInteractions(Player player, GameState gameState, GameMap gameMap) {

        if (gameState.getGlobalState() == GlobalState.MAINMENU) {
            handleButton(buttons.get("continue"), gameState);
            handleButton(buttons.get("newgame"), gameState);
            handleButton(buttons.get("exit"), gameState);
        } else if (gameState.getGlobalState().equals(GlobalState.GAME)) {
            if (isEscapeKeyPressed()) {
                gameState.setGlobalState(GlobalState.MAINMENU);
            }

            if (gameState.getDisplayState().equals(DisplayState.MAP)) {
                Direction playerMovement = getPlayerMovement();
                if (playerMovement != null) {
                    updatePlayerMovement(player, playerMovement, gameMap.getMapTiles());
                }
                handleButton(buttons.get("inventory"), gameState);
            } else if (gameState.getDisplayState().equals(DisplayState.INVENTORY)){
                handleButton(buttons.get("closeinv"), gameState);
            } else if (gameState.getDisplayState().equals(DisplayState.COMBAT)){
                //TBD
            }

        }

    }

    public void handleButton(AbstractButton button, GameState gameState) {
        if (button.positionIsInButtonRange(InputHandler.getMousePosition())) {
            button.setButtonHover();
        } else {
            button.setButtonDefault();
        }

        if (InputHandler.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
            if (button.positionIsInButtonRange(InputHandler.getMousePosition())) {
                button.setButtonClicked();
            }
        }
        if (InputHandler.mouseButtonReleased(GLFW_MOUSE_BUTTON_LEFT)) {
            if (button.positionIsInButtonRange(InputHandler.getMousePosition())) {
                button.setButtonDefault();
                switch (button.getAction()) {
                    case CONTINUE:
                        gameState.setGlobalState(GlobalState.GAME);
                        break;
                    case NEW_GAME:
                        gameState.setUserAction(UserAction.NEW_GAME);
                        break;
                    case EXIT:
                        gameState.setUserAction(UserAction.EXIT);
                        break;
                    case INVENTORY:
                        gameState.setDisplayState(DisplayState.INVENTORY);
                        break;
                    case CLOSE_INVENTORY:
                        gameState.setDisplayState(DisplayState.MAP);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public Direction getPlayerMovement() {
        if (InputHandler.keyReleased(GLFW_KEY_A) || InputHandler.keyReleased(GLFW_KEY_LEFT)) {
            return Direction.LEFT;
        }
        if (InputHandler.keyReleased(GLFW_KEY_D) || InputHandler.keyReleased(GLFW_KEY_RIGHT)) {
            return Direction.RIGHT;
        }
        if (InputHandler.keyReleased(GLFW_KEY_S) || InputHandler.keyReleased(GLFW_KEY_DOWN)) {
            return Direction.DOWN;
        }
        if (InputHandler.keyReleased(GLFW_KEY_W) || InputHandler.keyReleased(GLFW_KEY_UP)) {
            return Direction.UP;
        }

        return null;
    }

    public boolean isEscapeKeyPressed() {
        return InputHandler.keyReleased(GLFW_KEY_ESCAPE);
    }

    private void updatePlayerMovement(Player player, Direction direction, MapTile[][] mapTiles) {
        if (direction == Direction.LEFT) {
            player.setFaceDirection("left");
            if (mapTiles[player.getPos().getX() - 1][player.getPos().getY()].isPassable()) {
                player.setPosLeft();
            }
        }
        if (direction == Direction.RIGHT) {
            player.setFaceDirection("right");
            if (mapTiles[player.getPos().getX() + 1][player.getPos().getY()].isPassable()) {
                player.setPosRight();
            }
        }
        if (direction == Direction.UP) {
            if (mapTiles[player.getPos().getX()][player.getPos().getY() - 1].isPassable()) {
                player.setPosUp();
            }
        }
        if (direction == Direction.DOWN) {
            if (mapTiles[player.getPos().getX()][player.getPos().getY() + 1].isPassable()) {
                player.setPosDown();
            }
        }
    }

    public HashMap<String, AbstractButton> getButtons() {
        return buttons;
    }
}
