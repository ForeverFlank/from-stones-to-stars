package logic.recipe;

import math.BigNum;

import java.util.ArrayList;
import java.util.Objects;

public final class Recipe {
    public final String displayName;
    public final String description;
    public final ArrayList<RecipeInput> inputs;
    public final ArrayList<RecipeOutput> outputs;
    public final BigNum duration;
    public final boolean isAuto;

    public Recipe(
        String displayName,
        String description,
        ArrayList<RecipeInput> inputs,
        ArrayList<RecipeOutput> outputs,
        BigNum duration,
        boolean isAuto
    ) {
        this.displayName = displayName;
        this.description = description;
        this.inputs = inputs;
        this.outputs = outputs;
        this.duration = duration;
        this.isAuto = isAuto;
    }
}
