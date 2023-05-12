package model.environment;

import model.map.Direction;

public class Rock extends Environment{
    Direction direction;

    public Rock(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
