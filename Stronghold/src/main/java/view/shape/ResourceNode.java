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

import java.util.ArrayList;


public class ResourceNode {
    private final ResourcesName resourcesName;
    private final int buyPrice;
    private final int sellPrice;

    private int gold;
    private int resourceValue;

    private static ArrayList<ResourceNode> allResourceNodes = new ArrayList<>();

    private Label goldLabel;
    private Label resourceLabel;

    private Button buyButton;

    private Button sellButton;

    private StackPane stackPane;

    public ResourceNode(ResourcesName resourcesName, int buyPrice, int sellPrice , int gold , int resourceValue) {
        this.resourcesName = resourcesName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.gold = gold;
        this.resourceValue = resourceValue;

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

        buyButton = new Button("buy  " + buyPrice);
        buyButton.setPrefSize(200,45);
        buyButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buy " + resourcesName.name());
                ResourceNode.getResourceNodeByButton(buyButton).buy();
            }
        });




        sellButton = new Button("sell " + sellPrice);
        sellButton.setPrefSize(200,45);
        sellButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("sell " + resourcesName.name());
                ResourceNode.getResourceNodeByButton(buyButton).sell();
            }
        });

        VBox buttons = new VBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(buyButton,sellButton);

        goldLabel = new Label(String.valueOf(gold));
        goldLabel.setStyle("-fx-font: 20 sys");

        resourceLabel = new Label(String.valueOf(resourceValue));
        resourceLabel.setStyle("-fx-font: 20 sys");


        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(resourceLabel,resourceImage,buttons,coinImage,goldLabel);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);


        allResourceNodes.add(this);
    }


    private void buy(){
        gold -= buyPrice;
        resourceValue += 1;
        resourceLabel.setText(String.valueOf(resourceValue));
        for (ResourceNode resourceNode : allResourceNodes) {
            resourceNode.goldLabel.setText(String.valueOf(gold));
        }
    }

    private void sell(){
        gold += sellPrice;
        resourceValue -= 1;
        resourceLabel.setText(String.valueOf(resourceValue));

        for (ResourceNode resourceNode : allResourceNodes) {
            resourceNode.goldLabel.setText(String.valueOf(gold));
        }


    }

    private static ResourceNode getResourceNodeByButton(Button button){
        for (ResourceNode resourceNode : allResourceNodes) {
            if (button.equals(resourceNode.buyButton) || button.equals(resourceNode.sellButton)){
                return resourceNode;
            }

        }
        return null;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

}