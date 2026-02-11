package logic.recipe;

import math.BigNum;

public abstract class RecipeOutput {
    public final BigNum quantity;

    public RecipeOutput(BigNum quantity) {
        this.quantity = quantity;
    }

    public abstract String getResourceDisplayName();
}
