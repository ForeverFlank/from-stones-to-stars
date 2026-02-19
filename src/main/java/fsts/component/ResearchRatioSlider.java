package fsts.component;

import fsts.manager.Game;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ResearchRatioSlider extends HBox {

    private final Text fractionText;

    public ResearchRatioSlider() {
        Slider slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(1.0);
        slider.valueProperty().addListener(this::onSliderValueChange);

        fractionText = new Text("0%");

        getChildren().addAll(slider, fractionText);
        setAlignment(Pos.CENTER);
    }

    public void onSliderValueChange(
        ObservableValue<? extends Number> observableValue,
        Number oldValue,
        Number newValue
    ) {
        double fraction = newValue.doubleValue();
        Game.getInstance().researchManager.setResearchFraction(fraction);
        fractionText.setText((int) Math.round(fraction * 100.0) + "%");
    }
}
