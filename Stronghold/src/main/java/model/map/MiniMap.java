package model.map;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MiniMap extends Pane {

    private static double WIDTH = 180;
    private static double HEIGHT = 180;
    Map map;
    Group allMiniTiles;

    public MiniMap(Map map) {

        this.map = map;
        double size = WIDTH/map.getWidth();
        allMiniTiles = new Group();

        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                Rectangle miniMapTile = new Rectangle(size, size);
                miniMapTile.setFill(new ImagePattern(new Image(map.getBlockByXY(x,y).getTexture().getImagePath())));

                miniMapTile.setLayoutX(x * size);
                miniMapTile.setLayoutY(y * size);
                allMiniTiles.getChildren().add(miniMapTile);
            }
        }
        this.getChildren().add(allMiniTiles);
    }
}
