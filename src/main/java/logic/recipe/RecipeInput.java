package logic.recipe;

import math.BigNum;

public abstract class RecipeInput {
    public final BigNum quantity;

    public RecipeInput(BigNum quantity) {
        this.quantity = quantity;
    }

    public abstract String getResourceDisplayName();
}
