package model.environment;

import model.map.Block;

import java.util.ArrayList;

public abstract class Environment
{
    String name;
    int size;
    ArrayList<Block> blocks;

    public Environment(String name,int size){
        this.name = name;
        this.size = size;
        blocks = new ArrayList<>();
    }




}
