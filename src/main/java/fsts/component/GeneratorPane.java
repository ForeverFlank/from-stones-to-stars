package fsts.component;

import java.util.ArrayList;
import java.util.List;

import fsts.logic.generator.GeneratorState;
import fsts.logic.tier.TechTier;
import fsts.manager.Game;
import javafx.scene.layout.VBox;

public class GeneratorPane extends VBox {

    private final List<GeneratorButton> generatorButtons;
    private final PrestigePane prestigePane;

    public GeneratorPane() {
        generatorButtons = new ArrayList<>();

        for (GeneratorState generator : Game.getInstance().generatorManager.generatorStates) {
            for (TechTier techTier : TechTier.values()) {
                if (generator.definition.techTier != techTier) continue;
                generatorButtons.add(new GeneratorButton(generator));
            }
        }

        VBox vBox = new VBox();
        vBox.getChildren().addAll(generatorButtons);

        prestigePane = new PrestigePane();

        getChildren().addAll(vBox, prestigePane);
    }

    public void update() {
        for (GeneratorButton button : generatorButtons) {
            button.update();
        }
        prestigePane.update();
    }
}
