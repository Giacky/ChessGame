package players.minimax;

import board.BoardInfo;
import game.GameInstance;
import players.Player;
import players.PlayerType;
import players.evaluationFunctions.EvaluationFunction;
import utility.Move;
import utility.PlayerColor;

import java.util.LinkedList;


public class MiniMax extends Player {
    private EvaluationFunction evaluationFunction;
    private int maxDepth;
    private boolean isMaximisingPlayer;

    public MiniMax(PlayerColor playerColor, EvaluationFunction evaluationFunction, int maxDepth) {
        super(playerColor);
        this.playerType = PlayerType.MINIMAX;
        switch (playerColor) {
            case WHITE:
                isMaximisingPlayer = true;
                break;
            case BLACK:
                isMaximisingPlayer = false;
                break;
        }
        this.evaluationFunction = evaluationFunction;
        this.maxDepth = maxDepth;
    }

    @Override
    public Move bestMove(BoardInfo boardInfo) {
        MMNode root = new MMNode(boardInfo, null, null);
//        int bestValue = minimax(root, isMaximisingPlayer, new MMNode(Integer.MIN_VALUE), new MMNode(Integer.MAX_VALUE)).getValue();
        MoveValue moveValue = minimax(root, isMaximisingPlayer, new MoveValue(null, Integer.MIN_VALUE), new MoveValue(null, Integer.MAX_VALUE), null);
//        for (MMNode node : root.getChildren()) {
//            if (node.getValue() == bestValue) {
//                return node.getMove();
//            }
//        }
//        throw new RuntimeException("Best node not found");
        return moveValue.move;
    }

//    private MMNode minimax(MMNode node, boolean isMaximisingPlayer, MMNode alpha, MMNode beta) {
//        if (node.isLeaf() || node.getDepth() >= maxDepth) {
//            node.setValue(evaluationFunction.evaluate(node.getBoardInfo(), playerColor));
//            return node;
//        }
//        System.out.println("node to expand " + node.getDepth());
//        if (isMaximisingPlayer) {
//            MMNode bestNode = new MMNode(Integer.MIN_VALUE);
//            LinkedList<MMNode> children = node.getChildren();
//            for (MMNode child : children) {
//                MMNode tempNode = minimax(child, false, alpha, beta);
//                bestNode = MMNode.max(bestNode, tempNode);
//                alpha = MMNode.max(alpha, bestNode);
//                if (beta.lessThan(alpha)) {
//                    break;
//                }
//            }
//            return bestNode;
//        } else {
//            MMNode bestNode = new MMNode(Integer.MAX_VALUE);
//            LinkedList<MMNode> children = node.getChildren();
//            for (MMNode child : children) {
//                MMNode tempNode = minimax(child, true, alpha, beta);
//                bestNode = MMNode.min(bestNode, tempNode);
//                beta = MMNode.min(beta, bestNode);
//                if (beta.lessThan(alpha)) {
//                    break;
//                }
//            }
//            return bestNode;
//        }
//    }



    private MoveValue minimax(MMNode node, boolean isMaximisingPlayer, MoveValue alpha, MoveValue beta, Move move) {
        if (node.isLeaf() || node.getDepth() >= maxDepth) {
            int value = evaluationFunction.evaluate(node.getBoardInfo(), playerColor);
            return new MoveValue(move, value);
        }
//        System.out.println("node to expand " + node.getDepth());
        if (isMaximisingPlayer) {
            MoveValue bestMoveValue = new MoveValue(null, Integer.MIN_VALUE);
            LinkedList<MMNode> children = node.getChildren();
            for (MMNode child : children) {
                if (node.getParent() == null) {
                    move = child.getMove();
                }
                MoveValue moveValue = minimax(child, false, alpha, beta, move);
                bestMoveValue = MoveValue.max(bestMoveValue, moveValue);
                alpha = MoveValue.max(alpha, bestMoveValue);
                if (beta.lessThan(alpha)) {
                    break;
                }
            }
            return bestMoveValue;
        } else {
            MoveValue bestMoveValue = new MoveValue(null, Integer.MAX_VALUE);
            LinkedList<MMNode> children = node.getChildren();
            for (MMNode child : children) {
                if (move == null) {
                    move = child.getMove();
                }
                MoveValue moveValue = minimax(child, true, alpha, beta, move);
                bestMoveValue = MoveValue.min(bestMoveValue, moveValue);
                beta = MoveValue.min(beta, bestMoveValue);
                if (beta.lessThan(alpha)) {
                    break;
                }
            }
            return bestMoveValue;
        }
    }



//    private int minimax(MMNode node, boolean isMaximisingPlayer, int alpha, int beta) {
//        if (node.isLeaf() || node.getDepth() >= maxDepth) {
//            return evaluationFunction.evaluate(node.getBoardInfo(), playerColor);
//        }
//        System.out.println("node to expand " + node.getDepth());
//        if (isMaximisingPlayer) {
//            int best = Integer.MIN_VALUE;
//            LinkedList<MMNode> children = node.getChildren();
//            for (MMNode child : children) {
//                int value = minimax(child, false, alpha, beta);
//                best = Math.max(best, value);
//                alpha = Math.max(alpha, best);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            return best;
//        } else {
//            int best = Integer.MAX_VALUE;
//            LinkedList<MMNode> children = node.getChildren();
//            for (MMNode child : children) {
//                int value = minimax(child, true, alpha, beta);
//                best = Math.min(best, value);
//                beta = Math.min(beta, best);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            return best;
//        }
//    }

}
