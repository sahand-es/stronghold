package model.map;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MapPane extends Pane {
    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;
    Map map;
    Group allTiles;

    public MapPane(Map map) {
        this.map = map;
        int tilesCount = (int) (WIDTH * HEIGHT);
        allTiles = new Group();

        for (int i = 0; i < tilesCount; i++) {
            int x = (int) (i / WIDTH);
            int y = (int) (i % WIDTH);

            MapTile tile = new MapTile();
            tile.setTranslateX(MapTile.TILE_WIDTH / 2 * (x-y));
            tile.setTranslateY(MapTile.TILE_WIDTH / 4 * (x+y));
            tile.setFill(Color.GREEN);

            allTiles.getChildren().add(tile);
        }
        this.getChildren().add(allTiles);
    }
}
