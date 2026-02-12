package logic.recipe;

import logic.item.Item;
import manager.Game;
import manager.item.ItemManager;
import math.BigNum;

public record RecipeInput(String itemName, BigNum amount, boolean keepItem) {

    public String getItemDisplayName() {
        ItemManager itemManager = Game.getInstance().itemManager;
        Item item = itemManager.itemByName.get(itemName);
        return item.displayName();
    }
}
