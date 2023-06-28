package view.shape.government;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.map.Map;
import model.society.Government;
import model.society.enums.Colors;

public class PopularityFactors {



    Rectangle faceImage;
    HBox hBox;
    int rate;
    int maxRate;
    int minRate;
    String rateName;


    public PopularityFactors(String rateName,int minRate , int maxRate, int rate) {
        this.rateName = rateName;
        this.maxRate = maxRate;
        this.minRate = minRate;
        this.rate = rate;


        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setStyle("-fx-background-color: DARKGOLDENROD");


        faceImage = new Rectangle(50,50);
        setFace(rate);



        Label popularityChange = new Label(rateName + " : " + rate);
        popularityChange.setPrefWidth(100);
        popularityChange.setStyle("-fx-font: 15 sys");
        popularityChange.setAlignment(Pos.TOP_CENTER);

        Slider slider = new Slider(minRate,maxRate,rate);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setPrefWidth(250);
        slider.setSnapToTicks(true);
        slider.setValue(rate);



        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace((int)slider.getValue());
                popularityChange.setText(rateName + " : " + (int) slider.getValue());
            }
        });

        hBox.getChildren().addAll(faceImage,popularityChange,slider);

    }

    private void setFace(int rate){
        if (rate > 0){
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactors.class.getResource("/images/faces/green.jpg").toExternalForm())
            ));
        } else if (rate < 0){
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactors.class.getResource("/images/faces/red.jpg").toExternalForm())
            ));
        } else {
            faceImage.setFill(new ImagePattern(
                    new Image(PopularityFactors.class.getResource("/images/faces/yellow.jpg").toExternalForm())
            ));
        }
    }


    public HBox gethBox() {
        return hBox;
    }
}
