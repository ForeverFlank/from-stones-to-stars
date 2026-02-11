package logic.recipe;

import math.BigNum;

public class EnergyRecipeOutput extends RecipeOutput {
    public EnergyRecipeOutput(BigNum quantity) {
        super(quantity);
    }

    @Override
    public String getResourceDisplayName() {
        return "Energy";
    }
}
