package view.shape.government.popularity;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.shape.government.ControlPanel;

public class PopularityFactorNode {



    Rectangle faceImage;
    HBox hBox;
    int rate;
    int maxRate;
    int minRate;

    Slider slider;
    Label popularityChange;

    ControlPanel controlPanel;


    public PopularityFactorNode(String string, int minRate , int maxRate, int rate , ControlPanel controlPanel) {
        this.maxRate = maxRate;
        this.minRate = minRate;
        this.rate = rate;
        this.controlPanel = controlPanel;


        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setStyle("-fx-background-color: DARKGOLDENROD");


        faceImage = new Rectangle(50,50);
        setFace(rate);



        popularityChange = new Label(string);
        popularityChange.setPrefWidth(100);
        popularityChange.setStyle("-fx-font: 15 sys");
        popularityChange.setAlignment(Pos.TOP_CENTER);

        slider = new Slider(minRate,maxRate,rate);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setPrefWidth(250);
        slider.setSnapToTicks(true);
        slider.setValue(rate);


        hBox.getChildren().addAll(faceImage,popularityChange,slider);

    }

    protected void setFace(int rate){
        if (rate > 0){
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactorNode.class.getResource("/images/faces/green.jpg").toExternalForm())
            ));
        } else if (rate < 0){
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactorNode.class.getResource("/images/faces/red.jpg").toExternalForm())
            ));
        } else {
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactorNode.class.getResource("/images/faces/yellow.jpg").toExternalForm())
            ));
        }
    }


    public HBox gethBox() {
        return hBox;
    }
}
