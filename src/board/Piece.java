package board;

import utility.Move;
import utility.Player;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private Player player;
    private Image texture;
    private PieceType type;

    public Piece(Player player, PieceType type) {
        this.player = player;
        this.type = type;
    }



}
