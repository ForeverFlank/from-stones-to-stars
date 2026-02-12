package manager.item;

import logic.item.Item;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public final class ItemParser {
    private static final String KEY_NAME = "name";
    private static final String KEY_DISPLAY_NAME = "displayName";

    public static HashMap<String, Item> parseItems(InputStream stream) {
        HashMap<String, Item> items = new HashMap<>();
        ItemParserState state = new ItemParserState();

        try (Scanner scanner = new Scanner(stream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                parseLine(line, state, items);
            }
        }

        return items;
    }

    private static void parseLine(
        String line,
        ItemParserState state,
        HashMap<String, Item> items
    ) {
        if (line.isEmpty()) {
            return;
        }

        switch (line) {
            case "{" -> {
                state.clear();
                return;
            }
            case "}" -> {
                addItem(state, items);
                return;
            }
        }

        int equalsIndex = line.indexOf('=');
        if (equalsIndex < 0) {
            return;
        }

        String key = line.substring(0, equalsIndex).trim();
        String value = line.substring(equalsIndex + 1).trim();

        switch (key) {
            case KEY_NAME -> state.name = value;
            case KEY_DISPLAY_NAME -> state.displayName = value;
        }
    }

    private static void addItem(
        ItemParserState state,
        HashMap<String, Item> items
    ) {
        if (state.name == null || state.displayName == null) {
            return;
        }

        Item item = new Item(state.name, state.displayName);
        items.put(state.name, item);

        System.out.println("Added item " + state.name);
    }

    private static final class ItemParserState {
        String name;
        String displayName;

        void clear() {
            name = null;
            displayName = null;
        }
    }
}
