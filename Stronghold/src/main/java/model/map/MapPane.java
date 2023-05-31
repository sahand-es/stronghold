package model.map;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class MapPane extends Pane {
    private static double WIDTH;
    private static double HEIGHT;
    Map map;
    Group allTiles;

    public MapPane(Map map) {
        this.map = map;
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
        int tilesCount = (int) (WIDTH * HEIGHT);
        allTiles = new Group();

        for (int i = 0; i < tilesCount; i++) {
            int x = (int) (i / WIDTH);
            int y = (int) (i % WIDTH);

            MapTile tile = new MapTile();
            tile.setBlock(map.getBlockByXY(x, y));
            tile.setTranslateX(MapTile.TILE_WIDTH / 2 * (x-y));
            tile.setTranslateY(MapTile.TILE_WIDTH / 4 * (x+y));
            tile.setFill(new ImagePattern(new Image(tile.getBlock().getTexture().getImagePath())));

            allTiles.getChildren().add(tile);
        }
        this.getChildren().add(allTiles);
        setDragMove();
    }

    private void setDragMove() {
        MapPane mapPane = this;
        final Delta dragDelta = new Delta();
        mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = mapPane.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = mapPane.getLayoutY() - mouseEvent.getSceneY();
            mapPane.setCursor(Cursor.MOVE);
        }
    });
        mapPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            mapPane.setCursor(Cursor.HAND);
        }
    });
        mapPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            mapPane.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            mapPane.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
        }
    });
        mapPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            mapPane.setCursor(Cursor.HAND);
        }
    });


}

}
class Delta { double x, y; }
