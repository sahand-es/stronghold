package view.shape.government;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Database;
import model.society.Government;
import view.shape.trade.ResourceNode;

public class ScribesReport {
    VBox vBox;

    Rectangle popularityImage;
    Rectangle governmentColor;
    Label popularityLabel;

    Label goldLabel;

    Label populationLabel;

    Government government;
    ControlPanel controlPanel;
    public ScribesReport(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        government = Database.getCurrentGame().getCurrentGovernment();

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: DARKGOLDENROD");
        vBox.setPrefHeight(160);
        vBox.setMaxHeight(160);

        popularityImage = new Rectangle(80,80);
        setPopularityImage(government.getPopularity());
        popularityImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controlPanel.setPopularityDisplay();
            }
        });

        vBox.getChildren().add(popularityImage);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        governmentColor = new Rectangle(18,18);
        governmentColor.setFill(government.getColor().getColor());

        popularityLabel = new Label(String.valueOf(government.getPopularity()));
        popularityLabel.setStyle("-fx-font: 15 sys;");

        hBox.getChildren().addAll(governmentColor,popularityLabel);

        vBox.getChildren().add(hBox);

        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controlPanel.setResourceDisplay();
            }
        });

        Rectangle coinImage = new Rectangle(35,35);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        goldLabel = new Label(String.valueOf(government.getResource().getGold()));
        goldLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().addAll(coinImage , goldLabel);

        vBox.getChildren().add(hBox);

        populationLabel = new Label(government.getPopulation() + "/" + government.getPopulationCapacity());
        populationLabel.setStyle("-fx-font: 15 sys");

        vBox.getChildren().add(populationLabel);

    }

    private void setPopularityImage (int p){

        if (p >= 50) {
            popularityImage.setFill(new ImagePattern(
                    new Image(ScribesReport.class.getResource("/images/faces/popularity.png").toExternalForm())
            ));
        }
        else {
            popularityImage.setFill(new ImagePattern(
                    new Image(ScribesReport.class.getResource("/images/faces/unpopularity.png").toExternalForm())
            ));
        }
    }

    public void updateValues(){
        government = Database.getCurrentGame().getCurrentGovernment();
        setPopularityImage(government.getPopularity());
        governmentColor.setFill(government.getColor().getColor());
        popularityLabel.setText(String.valueOf(government.getPopularity()));
        goldLabel.setText(String.valueOf(government.getResource().getGold()));
        populationLabel.setText(government.getPopulation() + "/" + government.getPopulationCapacity());
    }

    public VBox getvBox() {
        return vBox;
    }
}
