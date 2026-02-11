package logic.recipe;

import math.BigNum;

public class EnergyRecipeInput extends RecipeInput {
    public EnergyRecipeInput(BigNum quantity) {
        super(quantity);
    }

    @Override
    public String getResourceDisplayName() {
        return "Energy";
    }
}
