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
        Point position = piece.getPosition();
        switch (piece.getType()) {
            case QUEEN:
                possibleMoves.addAll(hvdMoves(piece, true, true));
                break;
            case KING:
                for (int dx=-1; dx<2; dx++) {
                    for (int dy=-1; dy<2; dy++) {
                        if (dx!=0 && dy!=0) {
                            Point newPosition = new Point(position);
                            newPosition.moveBy(dx, dy);
                            if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y] == null) {
                                possibleMoves.add(new Move(position, newPosition));
                            }
                        }
                    }
                }
                break;
            case KNIGHT:
                for (int i=-3; i<4; i+=6) {
                    for (int j = -2; j < 3; j+=4) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(i, j);
                        if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y] == null) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                        newPosition = new Point(position);
                        newPosition.moveBy(j, i);
                        if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y] == null) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                    }
                }
                break;
            case BISHOP:
                possibleMoves.addAll(hvdMoves(piece, false, true));
                break;
            case ROOK:
                possibleMoves.addAll(hvdMoves(piece, true, false));
                break;
            case PAWN:
                
                break;
        }
        return possibleMoves;
    }

    public ArrayList<Move> allPossibleMoves() {

    }

    private ArrayList<Move> hvdMoves(Piece piece, boolean hvMoves, boolean dMoves) {
        Point position = piece.getPosition();
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for (int dx=-1; dx<2; dx++) {
            for (int dy=-1; dy<2; dy++) {
                if ( (hvMoves &&(dx == 0 || dy == 0) && !(dx == 0 && dy == 0)) || (dMoves && !(dx == 0 || dy == 0)) ) {
                    int m = 1;
                    while(true) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx * m, dy * m);
                        if (newPosition.x >= 0) {
                            if (board[newPosition.x][newPosition.y] == null) {
                                possibleMoves.add(new Move(position, newPosition));
                            } else if (board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
                                possibleMoves.add(new Move(position, newPosition));
                                break;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        m++;
                    }
                }
            }
        }
        return possibleMoves;
    }

}
