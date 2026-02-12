package component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class GamePane extends BorderPane {
    public final StatusPane statusPane;
    public final ItemPane itemPane;
    public final RecipePane recipePane;

    public GamePane() {
        statusPane = new StatusPane();
        itemPane = new ItemPane();
        recipePane = new RecipePane();

        setTop(statusPane);
        setLeft(itemPane);
        setCenter(recipePane);

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
        itemPane.update();
        recipePane.update();
    }
}
