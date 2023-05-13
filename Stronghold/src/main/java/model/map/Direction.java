package model.map;

import model.units.enums.UnitName;
import utility.RandomGenerators;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    DOWN(1, 0);

    private static ArrayList<Direction> directions = new ArrayList<>(List.of(UP, LEFT, RIGHT, DOWN));

    public int deltaX;
    public int deltaY;


    Direction(int deltaY, int deltaX) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static boolean isValid(String direction) {
        return Direction.getByString(direction) != null;
    }

    public static Direction getByString(String string) {
        switch (string) {
            case "n":
            case "up": {
                return UP;
            }
            case "e":
            case "left": {
                return LEFT;
            }
            case "w":
            case "right": {
                return RIGHT;
            }
            case "s":
            case "down": {
                return DOWN;
            }
            case "r":
            case "random": {
                return directions.get(RandomGenerators.randomNumber(0,3));
            }
            default: return null;
        }
    }

    public static Direction reverseDir(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return null;
    }
}
