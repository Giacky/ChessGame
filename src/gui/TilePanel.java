package gui;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private JLabel pieceLabel;
    
    public TilePanel(Dimension tileSize) {
        super();
        setPreferredSize(tileSize);
        setMinimumSize(tileSize);
        setMaximumSize(tileSize);
    }


    public JLabel getPieceLabel() {
        return pieceLabel;
    }

    public void addPieceIcon(ImageIcon pieceIcon) {
        addPieceLabel(new JLabel(pieceIcon));
    }

    private void addPieceLabel(JLabel pieceLabel) {
        this.pieceLabel = pieceLabel;
        add(pieceLabel, BorderLayout.CENTER);
    }

    private void removePieceLabel() {
        if (pieceLabel != null) {
            remove(pieceLabel);
            this.pieceLabel = null;
        }
    }

    public void substitutePieceLabel(TilePanel tilePanel) {
        removePieceLabel();
        addPieceLabel(tilePanel.getPieceLabel());
        tilePanel.removePieceLabel();
    }
}
