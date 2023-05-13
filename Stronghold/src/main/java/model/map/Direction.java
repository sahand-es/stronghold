package model.map;

import model.units.enums.UnitName;

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

    public static Direction reverseDir(Direction direction) {
        switch (direction) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
        }
        return null;
    }
}
