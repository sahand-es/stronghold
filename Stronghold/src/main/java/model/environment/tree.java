package model.environment;

public class tree extends Environment{
    TreeType treeType;

    public tree(int size, TreeType treeType) {
        super(size);
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }
}
