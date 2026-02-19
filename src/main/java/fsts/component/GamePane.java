package fsts.component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class GamePane extends BorderPane {

    public final StatusPane statusPane;
    public final GeneratorPane generatorPane;
    // public final ResearchRatioSlider researchPane;

    public GamePane() {
        statusPane = new StatusPane();
        generatorPane = new GeneratorPane();
        // researchPane = new ResearchRatioSlider();

        Tab generatorTab = new Tab("Generators", generatorPane);
        // Tab researchTab = new Tab("Researches", resarchPane);

        // TabPane tabPane = new TabPane(generatorTab, researchTab);
        TabPane tabPane = new TabPane(generatorTab);

        setTop(statusPane);
        setCenter(tabPane);

        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds(0.05),
            _ -> {
                statusPane.update();
                generatorPane.update();
                // researchPane.update();
            }
        );
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
