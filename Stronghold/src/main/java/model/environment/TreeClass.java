package model.environment;

import model.map.Block;

public class TreeClass extends Environment{
    TreeType treeType;



    public TreeType getTreeType() {
        return treeType;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public TreeClass(Block block) {
        block.setEnvironment(this);
    }
}
