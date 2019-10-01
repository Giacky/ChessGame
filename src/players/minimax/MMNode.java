package players.minimax;

import board.BoardInfo;
import utility.Move;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class MMNode {
    private MMNode parent;
    private LinkedList<MMNode> children;
    private int depth;

    private BoardInfo boardInfo;
    private Move move;
    private int value;

    public MMNode(BoardInfo boardInfo, MMNode parent, Move move) {
        this.parent = parent;
        this.boardInfo = boardInfo;
        this.move = move;
        if (parent != null) {
            this.depth = parent.getDepth() + 1;
        } else {
            this.depth = 0;
        }
//        System.out.println("new node at " + depth);
//        if (depth == 3) {
//            throw new RuntimeException("ifhuaufad");
//        }
    }

    public MMNode(int value) {
        this.value = value;
    }

    private void makeChildren() {
        LinkedList<Move> possibleMoves = boardInfo.getPossibleMoves();
        children = new LinkedList<>();
        for (Move move : possibleMoves) {
            BoardInfo childBoard = boardInfo.performMove(move);
            MMNode child = new MMNode(childBoard, this, move);
            children.add(child);
        }
        Collections.shuffle(children);
    }


    public MMNode getParent() {
        return parent;
    }

    public void setParent(MMNode parent) {
        this.parent = parent;
    }

    public LinkedList<MMNode> getChildren() {
        if (children == null) {
            makeChildren();
        }
        return children;
    }

    public boolean isLeaf() {
//        if (children == null) {
//            makeChildren();
//        }
        return boardInfo.getPossibleMoves().isEmpty();
    }

    public int getDepth() {
        return depth;
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

    public boolean lessThan(MMNode node) {
        return value <= node.getValue();
    }

    public static MMNode max(MMNode node1, MMNode node2) {
        if (node1.getValue() >= node2.getValue()) {
            return node1;
        } else {
            return node2;
        }
    }

    public static MMNode min(MMNode node1, MMNode node2) {
        if (node1.getValue() <= node2.getValue()) {
            return node1;
        } else {
            return node2;
        }
    }
}
