package view.shape;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utility.RandomGenerators;
import view.shape.map.MapTile;


public class Fire extends Group {

    public Fire() {
        setFireTiles();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.8), e -> setFireTiles()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    private void setFireTiles() {
        if (!this.getChildren().isEmpty())
            this.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            Rectangle fireTile = new Rectangle(MapTile.TILE_WIDTH, 50);
            Image image = new Image("file:src/main/resources/images/fire/" +
                    String.valueOf(RandomGenerators.randomNumber(1, 49)) +
                    ".png");
            double scale = image.getHeight() / image.getWidth();
            fireTile.setHeight(MapTile.TILE_WIDTH * scale);

            fireTile.setFill(new ImagePattern(image));
            this.getChildren().add(fireTile);
            fireTile.setLayoutX((double) i / 5 * MapTile.TILE_WIDTH);
            if (i == 2 || i == 1) {
                fireTile.setLayoutY(-3);
            }
        }

    }
}
