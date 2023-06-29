package view.shape.government.popularity;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Database;
import model.society.Government;
import view.shape.government.ControlPanel;

public class PopularityDisplay {
    HBox hBox;
    Government government;
    ControlPanel controlPanel;

    public PopularityDisplay(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        this.government = Database.getCurrentGame().getCurrentGovernment();
        hBox = new HBox();
        hBox.setStyle("-fx-background-color: DARKGOLDENROD");
        hBox.setSpacing(40);
        hBox.setPrefSize(600,160);
        hBox.setMaxSize(600,160);


        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);

        Label popularityLabel = new Label("Popularity");
        popularityLabel.setStyle("-fx-font: 20 sys");

        Rectangle popularityIcon = new Rectangle(100,100);
        popularityIcon.setFill(new ImagePattern(
                new Image(PopularityDisplay.class.getResource("/images/faces/icon.jpg").toExternalForm())
        ));

        vBox1.getChildren().addAll(popularityLabel,popularityIcon);

        VBox vBox2 = new VBox();
        vBox2.getChildren().add(new FoodRateNode(government,controlPanel).gethBox());
        vBox2.getChildren().add(new TaxRateNode(government,controlPanel).gethBox());
        vBox2.getChildren().add(new FearRateNode(government,controlPanel).gethBox());

        hBox.getChildren().addAll(vBox1,vBox2);
    }

    public HBox gethBox() {
        return hBox;
    }
}
