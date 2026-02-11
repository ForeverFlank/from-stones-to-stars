package manager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import manager.item.ItemManager;
import manager.recipe.RecipeManager;
import math.BigNum;

public class Game {
    private static Game instance;

    public final EnergyManager energyManager;
    public final ItemManager itemManager;
    public final RecipeManager recipeManager;
    public final ResearchManager researchManager;
    public final TimeManager timeManager;

    public static void init() {
        instance = new Game();
    }

    public static Game getInstance() {
        return instance;
    }

    private Game() {
        energyManager = new EnergyManager(new BigNum(10));
        itemManager = new ItemManager();
        recipeManager = new RecipeManager();
        researchManager = new ResearchManager();
        timeManager = new TimeManager();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.05), _ -> step());
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void step() {

    }
}
