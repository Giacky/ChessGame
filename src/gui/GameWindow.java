package gui;

import game.GameInstance;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    public GameWindow(GameInstance gameInstance) {
        super("Chess Game");
        int tileSize = 64;
        int chessBoardSize = tileSize * 8;
        int optionsPanelWidth = 250;
        setSize(chessBoardSize+optionsPanelWidth, chessBoardSize + 24);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        ChessBoardPanel chessBoardPanel = new ChessBoardPanel(gameInstance, tileSize);
        OptionsPanel optionsPanel = new OptionsPanel(optionsPanelWidth);

        Container container = getContentPane();

        container.add(chessBoardPanel, BorderLayout.WEST);
        container.add(optionsPanel, BorderLayout.EAST);

        setVisible(true);
    }

}