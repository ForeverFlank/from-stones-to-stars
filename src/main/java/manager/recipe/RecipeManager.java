package manager.recipe;

import logic.recipe.Recipe;
import util.ResourceUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

// TODO: add recipe id
public class RecipeManager {
    public final ArrayList<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
        loadAllRecipes();
    }

    private void loadAllRecipes() {
        Map<String, InputStream> inputStreams = ResourceUtils.loadResourceFiles("recipes");

        for (InputStream stream : inputStreams.values()) {
            ArrayList<Recipe> parsedRecipes = RecipeParser.parseRecipes(stream);
            recipes.addAll(parsedRecipes);
        }
    }
}
