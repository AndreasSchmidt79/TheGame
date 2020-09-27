package game;

public class GameState {

    private GlobalState globalState;
    private DisplayState displayState;
    private UserAction userAction = UserAction.EMPTY;;

    public GameState() {
    }

    public GlobalState getGlobalState() {
        return globalState;
    }

    public void setGlobalState(GlobalState globalState) {
        this.globalState = globalState;
    }

    public DisplayState getDisplayState() {
        return displayState;
    }

    public void setDisplayState(DisplayState displayState) {
        this.displayState = displayState;
    }

    public UserAction getUserAction() {
        return userAction;
    }

    public void setUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    public void clearUserAction() {
        userAction = UserAction.EMPTY;
    }

    public boolean shouldMapIntervalBeChecked(){
        return globalState.equals(GlobalState.GAME) && displayState.equals(DisplayState.MAP);
    }

    public boolean isNewGameStarted() {
        return userAction.equals(UserAction.NEW_GAME);
    }


    public boolean isExitGame() {
        return userAction.equals(UserAction.EXIT);
    }
}
