package view.shape.government.popularity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.society.Government;
import view.shape.government.ControlPanel;

public class TaxRateNode extends PopularityFactorNode {
    Government government;
    public TaxRateNode(Government government , ControlPanel controlPanel) {
        super("Tax Rate : " + Government.calcPopularityOfTaxRate(government.getTaxRate()),
                -3, 8, government.getTaxRate(),controlPanel);
        this.government = government;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setFace((int) slider.getValue());
                government.setTaxRate((int) slider.getValue());
                controlPanel.update();
                popularityChange.setText("Tax Rate : " + Government.calcPopularityOfTaxRate((int)slider.getValue()));
            }
        });
    }

    @Override
    protected void setFace(int rate) {
        int r = Government.calcPopularityOfTaxRate(rate);
        if (r >3) super.setFace(1);
        else if (r < -3) super.setFace(-1);
        else super.setFace(0);
    }
}
