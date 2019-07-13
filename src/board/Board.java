package board;

import utility.Move;
import utility.Player;
import utility.Point;

import java.util.ArrayList;

public class Board {
    private Piece[][] board;
    private Player player;

    public Board() {

    }

    public void initialPositions() {

    }

    public ArrayList<Move> piecePossibleMoves(Piece piece) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        switch (piece.getType()) {
            case QUEEN:
                break;
            case KING:
                break;
            case KNIGHT:
                break;
            case BISHOP:
                break;
            case ROOK:

                break;
            case PAWN:
                break;
        }
        return possibleMoves;
    }

    public ArrayList<Move> allPossibleMoves() {

    }

    private ArrayList<Move> diagonalMoves() {

    }

    private ArrayList<Move> horizontalVerticalMoves(Point position) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        int d = 1;

        //Forward
        while(true) {
            Point newPosition = new Point(position);
            newPosition.moveBy(0, d);
            if (newPosition.y < 8 && board[newPosition.x][newPosition.y] != null) {
                possibleMoves.add(new Move(position, newPosition));
            } else {
                break;
            }
        }

        //Right
        while(true) {
            Point newPosition = new Point(position);
            newPosition.moveBy(d, 0);
            if (newPosition.x < 8 && board[newPosition.x][newPosition.y] != null) {
                possibleMoves.add(new Move(position, newPosition));
            } else {
                break;
            }
        }

        //Backwards
        while(true) {
            Point newPosition = new Point(position);
            newPosition.moveBy(0, -d);
            if (newPosition.y >= 0 && board[newPosition.x][newPosition.y] != null) {
                possibleMoves.add(new Move(position, newPosition));
            } else {
                break;
            }
        }

        //Left
        while(true) {
            Point newPosition = new Point(position);
            newPosition.moveBy(-d, 0);
            if (newPosition.x >= 0  && board[newPosition.x][newPosition.y] != null) {
                possibleMoves.add(new Move(position, newPosition));
            } else {
                break;
            }
        }
        return possibleMoves;
    }

}
