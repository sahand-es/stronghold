package view.shape.government;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Database;
import model.society.Government;
import utility.DataManager;
import view.fxmlcontroller.BuildingScroll;
import view.shape.government.popularity.PopularityDisplay;
import view.shape.map.MiniMap;

import java.io.IOException;
import java.util.Objects;

public class ControlPanel {
    private HBox hBox;

    private ScribesReport scribesReport;
    private ResourceDisplay resourceDisplay;
    private PopularityDisplay popularityDisplay;
    private MiniMap miniMap;
    private TabPane buildingScroll;
    private Government government;

    public ControlPanel() {
        government = Database.getCurrentGame().getCurrentGovernment();

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(160);

        try {
            buildingScroll = FXMLLoader.load(Objects.requireNonNull(
                    BuildingScroll.class.getResource("/fxml/building-scroll.fxml")));
            hBox.getChildren().add(buildingScroll); //index = 0
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: DARKGOLDENROD");

        Button optionButton = new Button("O");
        //optionButton.setPrefSize(20,20);
        optionButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        vBox.getChildren().add(optionButton);

        Button undoButton = new Button("U");
        //undoButton.setPrefSize(18,18);
        undoButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        vBox.getChildren().add(undoButton);

        Button deleteButton = new Button("D");
        //deleteButton.setPrefSize(18,18);
        deleteButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        vBox.getChildren().add(deleteButton);

        hBox.getChildren().add(vBox); //index = 1

        miniMap = new MiniMap(Database.getCurrentMap());
        hBox.getChildren().add(miniMap); //index = 2

        resourceDisplay = new ResourceDisplay();
        hBox.getChildren().add(resourceDisplay.gethBox()); //index = 3

        scribesReport = new ScribesReport(this);
        hBox.getChildren().add(scribesReport.getvBox()); //index = 4
    }

    public void setResourceDisplay(){
        resourceDisplay = new ResourceDisplay();
        hBox.getChildren().set(3 , resourceDisplay.gethBox());
    }

    public void setPopularityDisplay(){
        popularityDisplay = new PopularityDisplay(this);
        hBox.getChildren().set(3 , popularityDisplay.gethBox());
    }

    public void update(){
        scribesReport.updateValues();
        resourceDisplay.updateValues();
    }

    public HBox gethBox() {
        return hBox;
    }
}
