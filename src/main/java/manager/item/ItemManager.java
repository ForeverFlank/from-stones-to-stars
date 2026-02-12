package manager.item;

import logic.item.Item;
import math.BigNum;
import util.ResourceUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    public HashMap<String, Item> itemByName;
    public HashMap<String, BigNum> itemAmounts;

    public ItemManager() {
        itemByName = new HashMap<>();
        itemAmounts = new HashMap<>();
        loadAllItems();
    }

    private void loadAllItems() {
        Map<String, InputStream> inputStreams = ResourceUtils.loadResourceFiles("items");

        for (InputStream stream : inputStreams.values()) {
            HashMap<String, Item> items = ItemParser.parseItems(stream);
            itemByName.putAll(items);
        }

        for (String itemName : itemByName.keySet()) {
            itemAmounts.put(itemName, new BigNum(0));
        }

        System.out.println(itemByName.size() + " items loaded.");
    }

    public Item getItem(String name) {
        return itemByName.get(name);
    }

    public BigNum getAmount(String itemName) {
        return itemAmounts.get(itemName);
    }

    public void setAmount(String itemName, BigNum amount) {
        BigNum clampedAmount = BigNum.max(amount, new BigNum(0));
        itemAmounts.put(itemName, clampedAmount);
    }

    public boolean hasEnough(String itemName, BigNum amount) {
        return getAmount(itemName).cmp(amount) >= 0;
    }

    public boolean hasEnough(String itemName, BigNum amount, double epsilon) {
        return getAmount(itemName).cmp(amount.sub(epsilon)) >= 0;
    }

    public void add(String itemName, BigNum amount) {
        setAmount(itemName, getAmount(itemName).add(amount));
    }

    public void remove(String itemName, BigNum amount) {
        setAmount(itemName, getAmount(itemName).sub(amount));
    }
}