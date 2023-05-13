package model.environment;

import model.map.Block;

public class Tree extends Environment{
    TreeType treeType;



    public TreeType getTreeType() {
        return treeType;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public Tree(Block block) {
        block.setEnvironment(this);
    }
}
