package component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GamePane extends BorderPane {
    public final StatusPane statusPane;
    public final ResourcePane resourcePane;

    public GamePane() {
        statusPane = new StatusPane();
        resourcePane = new ResourcePane();

        setTop(statusPane);
        setCenter(resourcePane);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.05),
                _ -> Platform.runLater(this::updateGUI)
        );
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateGUI() {
        statusPane.update();
        resourcePane.update();
    }
}
