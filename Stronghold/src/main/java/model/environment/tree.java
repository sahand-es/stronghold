package model.environment;

public class tree extends Environment{
    TreeType treeType;

    public tree(String name, int size, TreeType treeType) {
        super(name, size);
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }
}
