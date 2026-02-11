package manager;

import logic.item.Item;
import util.ResourceUtils;

import java.io.InputStream;
import java.util.*;

public class ItemManager {
    public HashMap<String, Item> itemByName;

    public ItemManager() {
        itemByName = new HashMap<>();
        loadAllItems();
    }

    private void loadAllItems() {
        Map<String, InputStream> inputStreams = ResourceUtils.loadResourceFiles("items");

        for (InputStream stream : inputStreams.values()) {
            HashMap<String, Item> items = ItemParser.parseItems(stream);
            itemByName.putAll(items);
        }
    }
}