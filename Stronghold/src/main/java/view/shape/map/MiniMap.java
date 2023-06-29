package view.shape.map;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.map.Map;
import model.map.Texture;

import java.util.ArrayList;

public class MiniMap extends Pane {

    private static double WIDTH = 220;
    private static double HEIGHT = 220;
    Map map;
    Group allMiniTiles;

    public MiniMap(Map map) {

        this.map = map;
        double size = WIDTH / map.getWidth();
        allMiniTiles = new Group();

        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                Rectangle miniMapTile = new Rectangle(size, size);
                Image image = new Image(map.getBlockByXY(x, y).getTexture().getImagePath());
                miniMapTile.setFill(new ImagePattern(image));

                miniMapTile.setLayoutX(x * size);
                miniMapTile.setLayoutY(y * size);
                allMiniTiles.getChildren().add(miniMapTile);
            }
        }
        this.getChildren().add(allMiniTiles);
    }

    public void setTexture(Texture texture, ArrayList<MapTile> selectedTiles) {
        for (MapTile selectedTile : selectedTiles) {
            int x = selectedTile.getBlock().getX();
            int y = selectedTile.getBlock().getY();
            Rectangle miniMapTile = (Rectangle) allMiniTiles.getChildren().get(x * map.getWidth() + y);
            miniMapTile.setFill(selectedTile.getFill());
        }
    }

    public void makeItSick(MapTile tile) {
        int x = tile.getBlock().getX();
        int y = tile.getBlock().getY();
        Rectangle miniMapTile = (Rectangle) allMiniTiles.getChildren().get(x * map.getWidth() + y);
        miniMapTile.setFill(new ImagePattern(new Image(tile.getBlock().getTexture().getSickImagePath())));
    }

    public void cureIt(MapTile tile) {
        int x = tile.getBlock().getX();
        int y = tile.getBlock().getY();
        Rectangle miniMapTile = (Rectangle) allMiniTiles.getChildren().get(x * map.getWidth() + y);
        miniMapTile.setFill(new ImagePattern(new Image(tile.getBlock().getTexture().getImagePath())));
    }
}
