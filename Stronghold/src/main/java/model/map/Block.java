package model.map;

import model.environment.Environment;
import model.environment.buldings.Building;
import model.units.Person;

import java.util.ArrayList;
import java.util.Objects;

public class Block
{
    private final int x;
    private final int y;
    private Texture texture;
    private Map map;

    private Environment whatIsOnThisBlock;
    private static ArrayList<Person> units;

    protected Block(int x, int y, Texture texture, Map map)
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.map = map;
    }

    static
    {
        units = new ArrayList<Person>();
    }

    public Map getMap() {
        return map;
    }
    public static ArrayList<Person> getUnits() {
        return units;
    }

    public static void setUnits(ArrayList<Person> units) {
        Block.units = units;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean canPassThisBlock(Person person)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return x == block.x && y == block.y && Objects.equals(map, block.map);
    }

}
