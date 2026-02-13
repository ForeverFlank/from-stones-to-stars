package fsts.component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class GamePane extends BorderPane {

    public final StatusPane statusPane;
    public final GeneratorPane generatorPane;
    public final ResearchPane researchPane;

    public GamePane() {
        statusPane = new StatusPane();
        generatorPane = new GeneratorPane();
        researchPane = new ResearchPane();

        HBox hBox = new HBox(generatorPane, researchPane);

        setTop(statusPane);
        setCenter(hBox);

        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds(0.05),
            _ -> {
                statusPane.update();
                generatorPane.update();
            }
        );
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
