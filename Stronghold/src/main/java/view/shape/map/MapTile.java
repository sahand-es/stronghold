package view.shape.map;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import model.map.Block;
import view.GameViewController;

public class MapTile extends Polygon {
    public static final double TILE_WIDTH = 100;
    public static final double TILE_HEIGHT = 50;
    private Block block;
    private int sickRound = -1;

    public MapTile() {
        super( TILE_WIDTH / 2, 0.0, TILE_WIDTH, TILE_HEIGHT / 2, TILE_WIDTH / 2, TILE_HEIGHT, 0.0, TILE_HEIGHT / 2 );
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void makeItSick() {
        sickRound = 0;
        this.setFill(new ImagePattern(new Image(block.getTexture().getSickImagePath())));
    }

    public void nextRound() {
        if (sickRound != -1 && sickRound < 3) {
            sickRound++;
        }
        else if (sickRound == 3) {
            this.setFill(new ImagePattern(new Image(block.getTexture().getImagePath())));
            GameViewController.cureIt(this);
            sickRound = -1;
        }
    }

    public boolean isSick() {
        return sickRound != -1;
    }
}
