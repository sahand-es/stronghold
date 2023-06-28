package view.shape.government;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.society.Government;

public class FoodRate extends PopularityFactors{
    Government government;
    public FoodRate(Government government) {
        super("Food Rate", -2, 2, 4 * government.getFoodRate());
        this.government = government;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace(4* (int) slider.getValue());
                government.setFoodRate((int) slider.getValue());
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
