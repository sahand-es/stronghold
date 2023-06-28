package view.shape.government;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    Label popularityLabel;

    Label goldLabel;

    Label populationLabel;

    Government government;
    public ScribesReport() {
        government = Database.getCurrentGame().getCurrentGovernment();

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: DARKGOLDENROD");
        vBox.setSpacing(10);
        vBox.setPrefHeight(160);

        popularityImage = new Rectangle(80,80);
        setPopularityImage(government.getPopularity());

        vBox.getChildren().add(popularityImage);

        popularityLabel = new Label(String.valueOf(government.getPopularity()));
        popularityLabel.setStyle("-fx-font: 15 sys;");

        vBox.getChildren().add(popularityLabel);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

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
        setPopularityImage(government.getPopularity());
        popularityLabel.setText(String.valueOf(government.getPopularity()));
        goldLabel.setText(String.valueOf(government.getResource().getGold()));
        populationLabel.setText(government.getPopulation() + "/" + government.getPopulationCapacity());
    }

    public VBox getvBox() {
        return vBox;
    }
}
