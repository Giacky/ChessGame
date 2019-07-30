package players.minimax;

import board.BoardInfo;
import utility.Move;

import java.util.ArrayList;

public class MMNode {
    private MMNode parent;
    private ArrayList<MMNode> children;
    private int depth;

    private BoardInfo boardInfo;
    private Move move;
    private int value;

    public MMNode(MMNode parent, BoardInfo boardInfo, Move move) {
        this.parent = parent;
        this.boardInfo = boardInfo;
        this.move = move;
        if (parent != null) {
            this.depth = parent.getDepth() + 1;
        } else {
            this.depth = 0;
        }
    }


    public MMNode getParent() {
        return parent;
    }

    public void setParent(MMNode parent) {
        this.parent = parent;
    }

    public ArrayList<MMNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MMNode> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public BoardInfo getBoardInfo() {
        return boardInfo;
    }

    public void setBoardInfo(BoardInfo boardInfo) {
        this.boardInfo = boardInfo;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
