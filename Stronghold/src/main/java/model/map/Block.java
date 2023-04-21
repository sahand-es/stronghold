package model.map;

import model.environment.Environment;
import model.environment.buldings.Building;
import model.units.Person;

import java.util.ArrayList;

public class Block
{
    private final int x;
    private final int y;
    private Texture texture;

    private Environment whatIsOnThisBlock;
    private static ArrayList<Person> units;

    protected Block(int x, int y, Texture texture)
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    static
    {
        units = new ArrayList<Person>();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean canPassThisBlock()
    {
        return true;
    }

    public Environment getWhatIsOnThisBlock() {
        return whatIsOnThisBlock;
    }

    public void setWhatIsOnThisBlock(Environment whatIsOnThisBlock) {
        this.whatIsOnThisBlock = whatIsOnThisBlock;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
