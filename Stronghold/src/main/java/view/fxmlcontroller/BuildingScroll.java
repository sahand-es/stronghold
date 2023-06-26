package view.fxmlcontroller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
import view.shape.BuildingNode;

public class BuildingScroll {
    public HBox hBox;

    @FXML
    private void initialize() {
        for (BuildingName name : BuildingName.values()) {
            VBox buildingVBox = new VBox();
            buildingVBox.setPrefHeight(150);
            buildingVBox.setPrefWidth(100);
            buildingVBox.getChildren().add(new BuildingNode(new Building(name),0));
            buildingVBox.setSpacing(8);
            Text text = new Text(name.getName());

            text.setTextAlignment(TextAlignment.CENTER);
            text.setWrappingWidth(buildingVBox.getPrefWidth());
            buildingVBox.getChildren().add(text);
            hBox.getChildren().add(buildingVBox);
        }
    }
}
