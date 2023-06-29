package view.shape.government.popularity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.society.Government;
import view.shape.government.ControlPanel;

public class FearRateNode extends PopularityFactorNode {

    Government government;
    public FearRateNode(Government government , ControlPanel controlPanel) {
        super("Fear Rate : " + government.getFearRate(),
                -5, 5, government.getFearRate(),controlPanel);
        this.government = government;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace((int) slider.getValue());
                government.setFearRate((int) slider.getValue());
                controlPanel.update();
                popularityChange.setText("Fear Rate : " + (int)slider.getValue());
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
