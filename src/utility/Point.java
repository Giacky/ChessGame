package utility;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public void moveBy(int xDelta, int yDelta) {
        this.x += xDelta;
        this.y += yDelta;
    }

    public void moveTo(int xNew, int yNew) {
        this.x = xNew;
        this.y = yNew;
    }

    public boolean equals(Point point) {
        return this.x == point.x && this.y == point.y;
    }

    public String toString() {
        return x + " " + y;
    }
}
