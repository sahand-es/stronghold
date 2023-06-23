package view.shape;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TradeHistoryNode {
    StackPane stackPane;

    public TradeHistoryNode() {
        Rectangle rectangle = new Rectangle(600, 100);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        Rectangle governmentColor = new Rectangle(70,70);
        governmentColor.setFill(Color.BLACK);

        hBox.getChildren().add(governmentColor);

        Rectangle resourceImage = new Rectangle(90, 90);
        resourceImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource(
                        "/images/resources/" + "stone" + ".png").toExternalForm()
                )
        ));

        hBox.getChildren().add(resourceImage);

        Label amountLabel = new Label(String.valueOf(10));
        amountLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(amountLabel);

        Rectangle coinImage = new Rectangle(90,90);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        hBox.getChildren().add(coinImage);

        Label priceLabel = new Label(String.valueOf(20));
        priceLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(priceLabel);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
