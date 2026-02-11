package component;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import logic.recipe.Recipe;
import manager.Game;

import java.util.ArrayList;

public class ResourcePane extends TilePane {

    public ResourcePane() {
        setPrefSize(600, 400);
        setVgap(5);
        setHgap(5);

        setTileAlignment(Pos.TOP_LEFT);

        ArrayList<Recipe> recipes = Game.getInstance().recipeManager.recipes;
        for (Recipe recipe : recipes) {
            RecipeButton recipeButton = new RecipeButton(recipe);
            getChildren().add(recipeButton);
        }
    }

    public void update() {
    }
}
