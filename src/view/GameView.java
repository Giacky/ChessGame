package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private int tileSize;
    ChessBoardPanel chessBoardPanel;

    public GameView() {
        super("Chess Game");
        this.tileSize = 64;
        int chessBoardSize = tileSize * 8;
        setSize(chessBoardSize, chessBoardSize + 24);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        chessBoardPanel = new ChessBoardPanel(tileSize);

        Container container = getContentPane();

        container.add(chessBoardPanel);

        setVisible(true);
    }

    public int getTileSize() {
        return tileSize;
    }

    public ChessBoardPanel getChessBoardPanel() {
        return chessBoardPanel;
    }


}