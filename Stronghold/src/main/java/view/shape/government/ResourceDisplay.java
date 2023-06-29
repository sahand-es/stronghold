package view.shape.government;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Database;
import model.resource.ResourcesName;
import model.society.Government;

import java.util.ArrayList;


public class ResourceDisplay {

    Government government;
    TilePane tilePane;

    ScrollPane scrollPane;

    HBox hBox;
    ArrayList<ResourceValueNode> valueNodes;

    public ResourceDisplay() {
        this.government = Database.getCurrentGame().getCurrentGovernment();

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefSize(600,160);
        hBox.setMaxSize(600,160);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: DARKGOLDENROD");

        Label resourcesLabel = new Label("Resources");
        resourcesLabel.setStyle("-fx-font: 20 sys");

        Rectangle Image = new Rectangle(100,100);
        Image.setFill(new ImagePattern(
                new Image(ResourceDisplay.class.getResource("/images/resources/apple.png").toExternalForm())
        ));

        vBox.getChildren().addAll(resourcesLabel,Image);


        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(500,160);


        tilePane = new TilePane();
        tilePane.setPrefHeight(160);
        tilePane.setHgap(20);
        tilePane.setVgap(15);
        tilePane.setOrientation(Orientation.VERTICAL);
        tilePane.setPrefColumns(3);
        tilePane.setStyle("-fx-background-color: DARKGOLDENROD");
        valueNodes = new ArrayList<>();

        ArrayList<ResourcesName> resources = new ArrayList<>();
        resources.addAll(ResourcesName.foods);
        resources.addAll(ResourcesName.Materials);
        resources.addAll(ResourcesName.weapons);

        ResourceValueNode node;

        for (ResourcesName resource : resources) {
            node = new ResourceValueNode(resource,government.getResource().getAmount(resource));
            valueNodes.add(node);
            tilePane.getChildren().add(node.gethBox());
        }

        scrollPane.setContent(tilePane);

        hBox.getChildren().addAll(vBox,scrollPane);
    }

    public void updateValues(){
        //TODO call wen changing resources
        ResourcesName r;
        for (ResourceValueNode node : valueNodes) {
            r = node.getResource();
            node.setValue(government.getResource().getAmount(r));
        }
    }


    public HBox gethBox() {
        return hBox;
    }
}
