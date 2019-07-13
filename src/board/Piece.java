package board;

import utility.Move;
import utility.Player;
import utility.Point;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private Player player;
    private Image texture;
    private PieceType type;
    private Point position; //(0, 0) bottom left

    public Piece(Player player, Point position, PieceType type) {
        this.player = player;
        this.type = type;
        this.position = position;
    }

    public PieceType getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public void setType(PieceType type) {
        this.type = type;
    }
}
