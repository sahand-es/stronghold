package view.shape;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.resource.ResourcesName;

import javafx.scene.shape.Rectangle;



public class ResourceNode {
    private final ResourcesName resourcesName;
    private final int buyPrice;
    private final int sellPrice;

    private StackPane stackPane;

    public ResourceNode(ResourcesName resourcesName, int buyPrice, int sellPrice , int gold , int resourceValue) {
        this.resourcesName = resourcesName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;

        Rectangle rectangle = new Rectangle(500,100);
        rectangle.setFill(Color.DARKGOLDENROD);



        Rectangle resourceImage = new Rectangle(90,90);
        resourceImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
                ));

        Rectangle coinImage = new Rectangle(90,90);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        Button buyButton = new Button("buy");
        buyButton.setPrefSize(200,45);
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buy " + resourcesName.name());
            }
        });


        Button sellButton = new Button("sell");
        sellButton.setPrefSize(200,45);
        sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("sell " + resourcesName.name());
            }
        });

        VBox buttons = new VBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(buyButton,sellButton);

        Label goldLabel = new Label(String.valueOf(gold));
        goldLabel.setStyle("-fx-font: 20 sys");

        Label resourceLabel = new Label(String.valueOf(resourceValue));
        resourceLabel.setStyle("-fx-font: 20 sys");


        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(resourceLabel,resourceImage,buttons,coinImage,goldLabel);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);


    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
