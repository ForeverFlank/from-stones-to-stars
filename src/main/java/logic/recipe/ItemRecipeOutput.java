package logic.recipe;

import logic.item.Item;
import manager.Game;
import manager.item.ItemManager;
import math.BigNum;

public class ItemRecipeOutput extends RecipeOutput {
    public final String itemName;

    public ItemRecipeOutput(String itemName, BigNum quantity) {
        super(quantity);
        this.itemName = itemName;
    }

    @Override
    public String getResourceDisplayName() {
        ItemManager itemManager = Game.getInstance().itemManager;
        Item item = itemManager.itemByName.get(itemName);
        return item.displayName;
    }
}
