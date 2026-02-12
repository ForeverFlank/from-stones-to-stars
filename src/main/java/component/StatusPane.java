package component;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import manager.Game;
import math.BigNum;

import java.math.RoundingMode;

public class StatusPane extends HBox {
    public final Text energyDisplay;

    public StatusPane() {
        energyDisplay = new Text("0");
        energyDisplay.setFont(new Font(18));

        getChildren().add(energyDisplay);
    }

    public void update() {
        BigNum energy = Game.getInstance().itemManager.getAmount("energy");
        energyDisplay.setText(energy.format(1, 1e6, false));
    }
}
