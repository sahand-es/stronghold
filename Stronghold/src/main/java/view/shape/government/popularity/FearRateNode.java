package view.shape.government.popularity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.society.Government;

public class FearRateNode extends PopularityFactorNode {

    Government government;
    public FearRateNode(Government government) {
        super("Fear Rate", -5, 5, government.getFearRate());
        this.government = government;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace((int) slider.getValue());
                government.setFearRate((int) slider.getValue());
                popularityChange.setText("Fear Rate : " + Integer.toString((int)slider.getValue()));
            }
        });
    }

    @Override
    protected void setFace(int rate) {
        if (rate >2) super.setFace(1);
        else if (rate < -2) super.setFace(-1);
        else super.setFace(0);
    }
}
