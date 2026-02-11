package logic.recipe;

import logic.item.Item;
import manager.Game;
import manager.item.ItemManager;
import math.BigNum;

public class ItemRecipeInput extends RecipeInput {
    public final String itemName;
    public final boolean keepItem;

    public ItemRecipeInput(String itemName, BigNum quantity, boolean keepItem) {
        super(quantity);
        this.itemName = itemName;
        this.keepItem = keepItem;
    }

    @Override
    public String getResourceDisplayName() {
        ItemManager itemManager = Game.getInstance().itemManager;
        Item item = itemManager.itemByName.get(itemName);
        return item.displayName;
    }
}
