package fsts.component;

import fsts.manager.Game;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ResearchPane extends BorderPane {

    private final Text fractionText;

    public ResearchPane() {
        Slider slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(1.0);
        slider.valueProperty().addListener(this::onSliderValueChange);

        fractionText = new Text("0%");

        HBox hBox = new HBox(slider, fractionText);

        setTop(hBox);
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
