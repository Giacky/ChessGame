package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame{

    public GameWindow() {
        super("Chess Game");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        ChessBoardPanel chessBoardPanel = new ChessBoardPanel();
        OptionsPanel optionsPanel = new OptionsPanel();

        Container container = getContentPane();

        container.add(chessBoardPanel, BorderLayout.WEST);
        container.add(optionsPanel, BorderLayout.EAST);




        setVisible(true);
    }
}