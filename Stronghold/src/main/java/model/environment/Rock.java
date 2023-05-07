package model.environment;

public class Rock extends Environment{
    Direction direction;

    public Rock(int size) {
        super(size);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
