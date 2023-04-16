package model.environment;

public class Rock extends Environment{
    Direction direction;

    public Rock(String name, int size, Direction direction) {
        super(name, size);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
