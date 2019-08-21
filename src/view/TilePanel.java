package view;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private JLabel pieceLabel;
    
    public TilePanel(Dimension tileSize) {
        super();
        setPreferredSize(tileSize);
        setMinimumSize(tileSize);
        setMaximumSize(tileSize);
        pieceLabel = new JLabel();
        add(pieceLabel, BorderLayout.CENTER);
    }


    public JLabel getPieceLabel() {
        return pieceLabel;
    }


    public void addPieceIcon(Icon pieceIcon) {
        pieceLabel.setIcon(pieceIcon);
    }


    public void substitutePieceLabel(TilePanel tilePanel) {
        pieceLabel.setIcon(tilePanel.getPieceLabel().getIcon());
        tilePanel.getPieceLabel().setIcon(null);
    }
}
