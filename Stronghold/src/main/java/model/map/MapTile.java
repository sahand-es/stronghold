package model.map;

import javafx.scene.shape.Polygon;

public class MapTile extends Polygon {
    protected static final double TILE_WIDTH = 50;
    protected static final double TILE_HEIGHT = 25;
    private Block block;

    public MapTile() {
        super( TILE_WIDTH / 2, 0.0, TILE_WIDTH, TILE_HEIGHT / 2, TILE_WIDTH / 2, TILE_HEIGHT, 0.0, TILE_HEIGHT / 2 );
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
