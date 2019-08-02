package utility;

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

}
