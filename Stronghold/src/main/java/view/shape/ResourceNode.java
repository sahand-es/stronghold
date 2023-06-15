package view.shape;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
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

    public ResourceNode(ResourcesName resourcesName, int buyPrice, int sellPrice) {
        this.resourcesName = resourcesName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;

        Rectangle rectangle = new Rectangle(300,60);
        rectangle.setFill(Color.DARKGOLDENROD);



        Rectangle resourceImage = new Rectangle(50,50);
        resourceImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
                ));

        Rectangle coinImage = new Rectangle(50,50);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        Button buyButton = new Button("buy");
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buy " + resourcesName.name());
            }
        });


        Button sellButton = new Button("sell");
        sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("sell " + resourcesName.name());
            }
        });

        VBox buttons = new VBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(buyButton,sellButton);


        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.getChildren().addAll(resourceImage,buttons,coinImage);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,flowPane);
        stackPane.setAlignment(flowPane,Pos.CENTER_RIGHT);
        stackPane.setAlignment(rectangle,Pos.CENTER_LEFT);


    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
