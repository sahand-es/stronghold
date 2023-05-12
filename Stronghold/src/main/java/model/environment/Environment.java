package model.environment;

import model.map.Block;

import java.util.ArrayList;

public abstract class Environment {
    int size;
    ArrayList<Block> blocks;

    public Environment(int size) {
        this.size = size;
    }


}
