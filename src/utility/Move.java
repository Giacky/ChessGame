package utility;

import java.util.ArrayList;
import java.util.LinkedList;

public class Move {
    public Point from;
    public Point to;
    private boolean capture = false;

    public Move(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Move(Point from, Point to, boolean capture) {
        this.from = from;
        this.to = to;
        this.capture = capture;
    }

    public boolean isCapture() {
        return capture;
    }

    public String toString() {
        return "From: " + from.toString() + "   to: " + to.toString();
    }

    public static LinkedList<Point> moveToPoint(LinkedList<Move> moves) {
        LinkedList<Point> points = new LinkedList<>();
        for (Move move : moves) {
            points.add(move.to);
        }
        return points;
    }

}
