import game.GameInstance;
import gui.GameWindow;
import players.*;
import players.minimax.MiniMax;

import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        GameInstance gameInstance = new GameInstance(new Human(), new MiniMax());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow gameWindow = new GameWindow(gameInstance);
            }
        });
    }
}
