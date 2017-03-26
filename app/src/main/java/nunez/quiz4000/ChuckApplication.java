package nunez.quiz4000;

/**
 * Created by nunez on 2/27/2017.
 */

import nunez.quiz4000.db.GamePlay;

import android.app.Application;

/**
 * @author rob
 *
 */
public class ChuckApplication extends Application{
    private GamePlay currentGame;

    /**
     * @param currentGame the currentGame to set
     */
    public void setCurrentGame(GamePlay currentGame) {
        this.currentGame = currentGame;
    }

    /**
     * @return the currentGame
     */
    public GamePlay getCurrentGame() {
        return currentGame;
    }
}
