package component;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import logic.recipe.Recipe;
import manager.Game;

import java.util.ArrayList;

public class RecipePane extends TilePane {
    private ArrayList<RecipeButton> recipeButtons;

    public RecipePane() {
        setPrefSize(600, 400);
        setVgap(5);
        setHgap(5);

        setTileAlignment(Pos.TOP_LEFT);

        ArrayList<Recipe> recipes = Game.getInstance().recipeManager.recipes;
        recipeButtons = new ArrayList<>();
        for (Recipe recipe : recipes) {
            RecipeButton recipeButton = new RecipeButton(recipe);
            recipeButtons.add(recipeButton);
            getChildren().add(recipeButton);
        }
    }

    public void update() {
        for (RecipeButton recipeButton : recipeButtons) {
            recipeButton.update();
        }
    }
}
