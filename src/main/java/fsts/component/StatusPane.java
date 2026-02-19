package fsts.component;

import fsts.manager.Game;
import fsts.math.BigNum;
import fsts.util.MetricFormatter;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusPane extends VBox {

    private final Text energyText;
    private final Text researchPointText;
    private final Text finalGenerationText;

    public StatusPane() {

        energyText = new Text("0");
        energyText.setFont(new Font(18));
        StackPane energyTextContainer = new StackPane(energyText);
        energyTextContainer.setPrefWidth(100);
        energyTextContainer.setAlignment(Pos.CENTER_LEFT);

        researchPointText = new Text("0");
        researchPointText.setFont(new Font(18));
        StackPane researchPointTextContainer = new StackPane(researchPointText);
        researchPointTextContainer.setPrefWidth(100);
        researchPointTextContainer.setAlignment(Pos.CENTER_LEFT);

        ResearchRatioSlider researchRatioSlider = new ResearchRatioSlider();
        StackPane researchRatioContainer = new StackPane(researchRatioSlider);
        researchRatioContainer.setPrefWidth(200);
        researchRatioContainer.setAlignment(Pos.CENTER_LEFT);

        HBox energyStatusContainer = new HBox(
            energyTextContainer,
            researchPointTextContainer,
            researchRatioContainer
        );
        energyStatusContainer.setPrefWidth(400);
        energyStatusContainer.setMaxWidth(400);

        finalGenerationText = new Text();

        getChildren().addAll(energyStatusContainer, finalGenerationText);
    }

    public void update() {
        BigNum energy = Game.getInstance().energyManager.getEnergy();
        BigNum researchPoint = Game.getInstance().researchManager.getResearchPoint();
        BigNum finalEnergyGeneration = Game.getInstance().getFinalEnergyGeneration();

        energyText.setText(MetricFormatter.formatPositivePrefixes(energy, "Wh"));
        researchPointText.setText(researchPoint.format(3, 1e6, false) + " RP");
        finalGenerationText.setText(
            "Generating " + MetricFormatter.formatPositivePrefixes(finalEnergyGeneration, "W"));
    }
}