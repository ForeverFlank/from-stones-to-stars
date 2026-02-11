package manager;

import logic.recipe.*;
import util.ResourceUtils;

import java.io.InputStream;
import java.util.*;

public class RecipeManager {
    public final ArrayList<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
        loadAllRecipes();
    }

    private void loadAllRecipes() {
        Map<String, InputStream> inputStreams = ResourceUtils.loadResourceFiles("items");
        for (InputStream stream : inputStreams.values()) {
            ArrayList<Recipe> parsedRecipes = RecipeParser.parseRecipes(stream);
            recipes.addAll(parsedRecipes);
        }
    }

    // TODO: throw exception if something fails

}
