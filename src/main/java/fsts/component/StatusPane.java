package fsts.component;

import fsts.manager.Game;
import fsts.math.BigNum;
import fsts.util.MetricFormatter;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusPane extends VBox {

    private final Text energyText;
    private final Text capacityText;
    private final Text finalGenerationText;

    public StatusPane() {

        energyText = new Text("0");
        energyText.setFont(new Font(18));
        StackPane energyTextContainer = new StackPane(energyText);
        energyTextContainer.setPrefWidth(100);
        energyTextContainer.setAlignment(Pos.CENTER_RIGHT);

        Text slashText = new Text(" / ");
        slashText.setFont(new Font(18));

        capacityText = new Text("0");
        capacityText.setFont(new Font(18));
        StackPane capacityTextContainer = new StackPane(capacityText);
        capacityTextContainer.setPrefWidth(100);
        capacityTextContainer.setAlignment(Pos.CENTER_LEFT);

        StackPane slashContainer = new StackPane(slashText);
        slashContainer.setAlignment(Pos.CENTER);

        BorderPane energyStatusContainer = new BorderPane();
        energyStatusContainer.setPrefWidth(200);
        energyStatusContainer.setMaxWidth(200);
        energyStatusContainer.setLeft(energyTextContainer);
        energyStatusContainer.setCenter(slashContainer);
        energyStatusContainer.setRight(capacityTextContainer);

        finalGenerationText = new Text();

        getChildren().addAll(energyStatusContainer, finalGenerationText);
    }

    public void update() {
        BigNum energy = Game.getInstance().energyManager.getEnergy();
        BigNum capacity = Game.getInstance().energyManager.getCapacity();
        BigNum finalEnergyGeneration = Game.getInstance().getFinalEnergyGeneration();

        energyText.setText(MetricFormatter.formatPositivePrefixes(energy, "Wh"));
        capacityText.setText(MetricFormatter.formatPositivePrefixes(capacity, "Wh"));
        finalGenerationText.setText(
            "Generating " + MetricFormatter.formatPositivePrefixes(finalEnergyGeneration, "W"));
    }
}