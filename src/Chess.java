import controller.GameController;
import game.GameInstance;
import view.GameView;
import players.*;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Chess {
    public static void main(String[] args) {
        final GameView[] gameView = new GameView[1];
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    gameView[0] = new GameView();
                }
            });
        } catch(InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }

        GameInstance gameInstance = new GameInstance(PlayerType.HUMAN, PlayerType.MINIMAX);
        GameController gameController = new GameController(gameView[0], gameInstance);
        gameInstance.gameLoop();
    }
}
