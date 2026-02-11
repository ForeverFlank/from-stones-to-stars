package component;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.recipe.Recipe;
import logic.recipe.RecipeInput;
import logic.recipe.RecipeOutput;

import java.util.ArrayList;

public class RecipeButton extends Button {
    VBox vBox;

    public RecipeButton(Recipe recipe) {
        setMinSize(100, 50);

        vBox = new VBox();

        String recipeName = recipe.displayName;
        ArrayList<RecipeInput> recipeInputs = recipe.inputs;
        ArrayList<RecipeOutput> recipeOutputs = recipe.outputs;

        Text recipeText = new Text(recipeName);
        recipeText.setFont(new Font(12));
        vBox.getChildren().add(recipeText);

        for (RecipeInput recipeInput : recipeInputs) {
             String resourceName = recipeInput.getResourceDisplayName();
             Text recipeInputText = new Text("< " + resourceName);
             recipeInputText.setFont(new Font(10));
             vBox.getChildren().add(recipeInputText);
        }

        for (RecipeOutput recipeOutput : recipeOutputs) {
            String resourceName = recipeOutput.getResourceDisplayName();
            Text recipeOutputText = new Text("> " + resourceName);
            recipeOutputText.setFont(new Font(10));
            vBox.getChildren().add(recipeOutputText);
        }

        setGraphic(vBox);
    }

    public void onClick() {
        // TODO
    }
}
