import gui.GameWindow;

import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow gameWindow = new GameWindow();
            }
        });
    }
}
