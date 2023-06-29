package view.shape.government.popularity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.society.Government;
import view.shape.government.ControlPanel;

public class FoodRateNode extends PopularityFactorNode {
    Government government;
    public FoodRateNode(Government government , ControlPanel controlPanel) {
        super("Food Rate : " + 4 * government.getFoodRate(),
                -2, 2, government.getFoodRate(),controlPanel);
        this.government = government;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace(4* (int) slider.getValue());
                government.setFoodRate((int) slider.getValue());
                controlPanel.update();
                popularityChange.setText("Food Rate : " + 4 * (int)slider.getValue());
            }
        });
    }

    @Override
    protected void setFace(int rate) {
        if (rate >4) super.setFace(1);
        else if (rate < -4) super.setFace(-1);
        else super.setFace(0);
    }
}
