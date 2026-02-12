package component;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.recipe.Recipe;
import logic.recipe.RecipeInput;
import logic.recipe.RecipeOutput;
import manager.Game;

import java.util.ArrayList;

public class RecipeButton extends Button {
    private final Recipe recipe;

    private Rectangle cooldownOverlay;
    private boolean isOnCooldown = false;

    public RecipeButton(Recipe recipe) {
        this.recipe = recipe;

        setMinSize(100, 50);
        setPadding(Insets.EMPTY);

        String recipeName = recipe.displayName;
        ArrayList<RecipeInput> recipeInputs = recipe.inputs;
        ArrayList<RecipeOutput> recipeOutputs = recipe.outputs;

        Text recipeText = new Text(recipeName);
        recipeText.setFont(new Font(12));

        VBox vBox = new VBox(recipeText);
        vBox.setPadding(new Insets(5, 10, 5, 10));

        addRecipeInputs(recipeInputs, vBox);
        addRecipeOutputs(recipeOutputs, vBox);
        addCooldownOverlay(vBox);

        setOnAction(_ -> onClick());
        update();
    }

    private void addRecipeInputs(ArrayList<RecipeInput> recipeInputs, VBox vBox) {
        for (RecipeInput recipeInput : recipeInputs) {
            String itemName = recipeInput.getItemDisplayName();
            String itemAmount = recipeInput.amount().format(3, 1e3, true);

            Text recipeInputText = new Text("< " + itemAmount + " " + itemName);
            recipeInputText.setFont(new Font(10));

            vBox.getChildren().add(recipeInputText);
        }
    }

    private void addRecipeOutputs(ArrayList<RecipeOutput> recipeOutputs, VBox vBox) {
        for (RecipeOutput recipeOutput : recipeOutputs) {
            String itemName = recipeOutput.getItemDisplayName();
            String itemAmount = recipeOutput.amount().format(3, 1e3, true);

            Text recipeOutputText = new Text("> " + itemAmount + " " + itemName);
            recipeOutputText.setFont(new Font(10));

            vBox.getChildren().add(recipeOutputText);
        }
    }

    private void addCooldownOverlay(VBox vBox) {
        cooldownOverlay = new Rectangle();
        cooldownOverlay.setScaleX(0);
        cooldownOverlay.setFill(Color.rgb(100, 100, 100, 0.4));
        cooldownOverlay.setMouseTransparent(true);
        cooldownOverlay.setManaged(false);

        StackPane stackPane = new StackPane(vBox, cooldownOverlay);
        stackPane.setPadding(Insets.EMPTY);
        cooldownOverlay.widthProperty().bind(stackPane.widthProperty());
        cooldownOverlay.heightProperty().bind(stackPane.heightProperty());

        setGraphic(stackPane);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(stackPane.widthProperty());
        clip.heightProperty().bind(stackPane.heightProperty());
        stackPane.setClip(clip);
    }

    public void update() {
        // TODO: wait until the cooldown finishes,
        // then, get the recipe output
        // do this logic in RecipeManager
        boolean canUseRecipe = Game.getInstance().canUseRecipe(recipe);
        setDisable(!canUseRecipe || isOnCooldown);
    }

    private void onClick() {
        boolean canUseRecipe = Game.getInstance().canUseRecipe(recipe);
        if (!canUseRecipe || isOnCooldown) return;

        Game.getInstance().useRecipe(recipe);

        startCooldown(recipe.duration.mantissa * Math.pow(10, recipe.duration.exponent));
    }

    private void startCooldown(double seconds) {
        isOnCooldown = true;
        setDisable(true);

        cooldownOverlay.setScaleX(1);

        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(seconds),
                new KeyValue(cooldownOverlay.scaleXProperty(), 0)
            )
        );

        timeline.setOnFinished(e -> {
            isOnCooldown = false;
            update();
        });

        timeline.play();
    }
}
