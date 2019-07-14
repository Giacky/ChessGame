package gui;

import game.GameInstance;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    public GameWindow(GameInstance gameInstance) {
        super("Chess Game");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        ChessBoardPanel chessBoardPanel = new ChessBoardPanel(gameInstance.getBoardInfo());
        OptionsPanel optionsPanel = new OptionsPanel();

        Container container = getContentPane();

        container.add(chessBoardPanel, BorderLayout.WEST);
        container.add(optionsPanel, BorderLayout.EAST);

        setVisible(true);
    }

}