package model.map;

public enum Direction {
    RIGHT(0,1),
    LEFT(0, -1),
    UP(-1,0),
    DOWN(1, 0);
    ;

    public int deltaX;
    public int deltaY;

    Direction(int deltaY, int deltaX) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
}
