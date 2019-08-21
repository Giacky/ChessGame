package utility;

public enum PlayerColor {
    WHITE,
    BLACK;


    public String toString() {
        switch (this) {
            case WHITE:
                return "White";
            default:
                return "Black";
        }
    }
}
