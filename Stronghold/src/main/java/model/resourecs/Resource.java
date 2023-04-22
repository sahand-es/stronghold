package model.resourecs;

import model.society.enums.Resources;

import java.util.ArrayList;

public class Resource
{
    Resources kind;
    int count;
    private ArrayList<Resource> price;

    public Resource(Resources resource)
    {
        this.kind = resource;
    }
    
    public Resource(String resourceName)
    {
        kind = Resources.getResourceByName(resourceName);
    }

    public Resources getKind()
    {
        return kind;
    }

    public int getCount()
    {
        return count;
    }

    public void addCount(int count)
    {
    }
}
