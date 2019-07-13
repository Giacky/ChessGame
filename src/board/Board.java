package board;

import utility.Move;
import utility.Player;
import utility.Point;

import java.util.ArrayList;

public class Board {
    private Piece[][] board;
    private Player playerTurn;
    private int turnCount;

    public Board() {
        board = new Piece[8][8];
    }

    public void initialPositions() {
        //White player
        board[0][0] = new Rook(Player.WHITE);
        board[1][0] = new Knight(Player.WHITE);
        board[2][0] = new Bishop(Player.WHITE);
        board[3][0] = new Queen(Player.WHITE);
        board[4][0] = new King(Player.WHITE);
        board[5][0] = new Bishop(Player.WHITE);
        board[6][0] = new Knight(Player.WHITE);
        board[7][0] = new Rook(Player.WHITE);
        board[0][1] = new Pawn(Player.WHITE);
        board[1][1] = new Pawn(Player.WHITE);
        board[2][1] = new Pawn(Player.WHITE);
        board[3][1] = new Pawn(Player.WHITE);
        board[4][1] = new Pawn(Player.WHITE);
        board[5][1] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[7][1] = new Pawn(Player.WHITE);

        //Black player
        board[0][7] = new Rook(Player.BLACK);
        board[1][7] = new Knight(Player.BLACK);
        board[2][7] = new Bishop(Player.BLACK);
        board[3][7] = new Queen(Player.BLACK);
        board[4][7] = new King(Player.BLACK);
        board[5][7] = new Bishop(Player.BLACK);
        board[6][7] = new Knight(Player.BLACK);
        board[7][7] = new Rook(Player.BLACK);
        board[0][6] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn(Player.BLACK);
        board[2][6] = new Pawn(Player.BLACK);
        board[3][6] = new Pawn(Player.BLACK);
        board[4][6] = new Pawn(Player.BLACK);
        board[5][6] = new Pawn(Player.BLACK);
        board[6][6] = new Pawn(Player.BLACK);
        board[7][6] = new Pawn(Player.BLACK);
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
                            if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
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
                        if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                        newPosition = new Point(position);
                        newPosition.moveBy(j, i);
                        if (newPosition.x >= 0 && newPosition.y >= 0 && newPosition.x < 8 && newPosition.y < 8 && board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
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
                if (piece.getPlayer() == Player.WHITE) {
                    if (position.y == 1) {
                        for (int dy = 1; dy < 3; dy++) {
                            Point newPosition = new Point(position);
                            newPosition.moveBy(0, dy);
                            if (board[newPosition.x][newPosition.y] == null) {
                                possibleMoves.add(new Move(position, newPosition));
                            }  else {
                                break;
                            }
                        }
                    }
                    //Diagonal capture
                    for (int dx = -1; dx < 2; dx+=2) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx, 1);
                        if (board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                    }

                } else {
                    if (position.y == 6) {
                        for (int dy = -1; dy > -3; dy--) {
                            Point newPosition = new Point(position);
                            newPosition.moveBy(0, dy);
                            if (board[newPosition.x][newPosition.y] == null) {
                                possibleMoves.add(new Move(position, newPosition));
                            } else {
                                break;
                            }
                        }
                    }
                    //Diagonal capture
                    for (int dx = -1; dx < 2; dx+=2) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx, -1);
                        if (board[newPosition.x][newPosition.y].getPlayer() != piece.getPlayer()) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                    }
                }
                break;
        }
        return possibleMoves;
    }

    public ArrayList<Move> allPossibleMoves() {
        return null;
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
