package model.resourecs;

import model.society.enums.Resources;

public class Resource
{
    Resources name;
    int count;

    public Resource(Resources resource)
    {
        this.name = resource;
    }

    public Resources getName()
    {
        return name;
    }

    public int getCount()
    {
        return count;
    }

    public void addCount(int count)
    {
    }
}
