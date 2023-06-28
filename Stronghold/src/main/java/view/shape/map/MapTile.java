package view.shape.map;

import javafx.scene.shape.Polygon;
import model.map.Block;

public class MapTile extends Polygon {
    public static final double TILE_WIDTH = 100;
    public static final double TILE_HEIGHT = 50;
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
