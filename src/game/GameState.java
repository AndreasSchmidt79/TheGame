package game;

public class GameState {

    private GlobalState globalState;
    private DisplayState displayState;

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

    public boolean shouldMapIntervalBeChecked(){
        return globalState.equals(GlobalState.GAME) && displayState.equals(DisplayState.MAP);
    }

    public boolean isNewGameStarted() {
        return globalState.equals(GlobalState.NEWGAME);
    }


    public boolean isExitGame() {
        return globalState.equals(GlobalState.EXIT);
    }
}
