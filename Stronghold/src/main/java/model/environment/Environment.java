package model.environment;

import model.map.Block;

import java.util.ArrayList;

public abstract class Environment {
    Block block;

    public void setBlock(Block block) {
        this.block = block;
    }
}
