import game.GameInstance;
import gui.GameWindow;
import players.*;
import players.minimax.MiniMax;
import utility.Point;

import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        GameInstance gameInstance = new GameInstance(PlayerType.HUMAN, PlayerType.MINIMAX);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow gameWindow = new GameWindow(gameInstance);
            }
        });
    }
}
