package view.shape;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.environment.buildings.Building;
import model.map.MapTile;


public class BuildingNode extends Rectangle {
    Building building;

    public BuildingNode(Building building) {
        super(MapTile.TILE_WIDTH, (MapTile.TILE_HEIGHT * 2));
        this.building = building;

        // TODO: 6/8/2023 towers background 
        Image image = new Image(building.getName().getImagePath());

        double scale = image.getHeight()/image.getWidth();
        System.out.println(scale);
        this.setHeight(MapTile.TILE_WIDTH * scale);


        this.setFill(new ImagePattern(image));
//        this.setFill(Color.BLACK);
    }
}
