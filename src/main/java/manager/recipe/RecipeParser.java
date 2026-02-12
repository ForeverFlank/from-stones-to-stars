package manager.recipe;

import logic.recipe.Recipe;
import logic.recipe.RecipeInput;
import logic.recipe.RecipeOutput;
import math.BigNum;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public final class RecipeParser {
    private static final String KEY_DISPLAYNAME = "displayName";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_INPUT = "input";
    private static final String KEY_OUTPUT = "output";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_IS_AUTO = "isAuto";

    public static ArrayList<Recipe> parseRecipes(InputStream stream) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        RecipeParserState state = new RecipeParserState();

        try (Scanner scanner = new Scanner(stream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                parseLine(line, state, recipes);
            }
        }

        return recipes;
    }

    private static void parseLine(
        String line,
        RecipeParserState state,
        ArrayList<Recipe> recipes
    ) {
        switch (line) {
            case "" -> {
                return;
            }
            case "{" -> {
                state.clear();
                return;
            }
            case "}" -> {
                System.out.println("Added recipe '" + state.displayName + "'");
                recipes.add(buildRecipe(state));
                return;
            }
        }

        int equalsIndex = line.indexOf('=');
        if (equalsIndex < 0) {
            return;
        }

        String key = line.substring(0, equalsIndex).trim();
        String value = line.substring(equalsIndex + 1).trim();

        switch (key) {
            case KEY_DISPLAYNAME -> state.displayName = value;
            case KEY_DESCRIPTION -> state.description = value;
            case KEY_INPUT -> state.inputs.add(parseInput(value));
            case KEY_OUTPUT -> state.outputs.add(parseOutput(value));
            case KEY_DURATION -> state.duration = parseBigNum(value);
            case KEY_IS_AUTO -> state.isAuto = Boolean.parseBoolean(value);
        }
    }

    private static Recipe buildRecipe(RecipeParserState state) {
        return new Recipe(
            state.displayName,
            state.description,
            new ArrayList<>(state.inputs),
            new ArrayList<>(state.outputs),
            state.duration,
            state.isAuto
        );
    }

    private static BigNum parseBigNum(String value) {
        return new BigNum(Double.parseDouble(value));
    }

    private static RecipeInput parseInput(String value) {
        String[] parts = value.split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input definition: " + value);
        }

        BigNum amount = parseBigNum(parts[0]);
        String itemName = parts[1];
        boolean keepItem = parts.length > 2;

        return new RecipeInput(itemName, amount, keepItem);
    }

    private static RecipeOutput parseOutput(String value) {
        String[] parts = value.split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid output definition: " + value);
        }

        BigNum amount = parseBigNum(parts[0]);
        String itemName = parts[1];

        return new RecipeOutput(itemName, amount);
    }

    private static final class RecipeParserState {
        String displayName;
        String description;
        ArrayList<RecipeInput> inputs;
        ArrayList<RecipeOutput> outputs;
        boolean isAuto;
        BigNum duration;

        RecipeParserState() {
            clear();
        }

        void clear() {
            displayName = "";
            description = "";
            inputs = new ArrayList<>();
            outputs = new ArrayList<>();
            isAuto = false;
            duration = new BigNum(0);
        }
    }
}
