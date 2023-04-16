package model.map;

import model.units.Person;

import java.util.ArrayList;

public class Block
{
    int x;
    int y;
    Texture texture;
//    ToDo: what's in this block (buildings and more..)
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
}
