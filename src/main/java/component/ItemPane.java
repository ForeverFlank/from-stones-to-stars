package component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manager.Game;

import java.util.ArrayList;
import java.util.Comparator;

public class ItemPane extends BorderPane {
    enum SortingState {
        NAME, AMOUNT
    }

    private final Button sortByNameButton;
    private final Button sortByAmountButton;
    private SortingState sortingState;
    private boolean isSortDescending;

    private final VBox itemsContainer;
    private final ArrayList<ItemAmountPane> itemAmountPanes;

    public ItemPane() {
        sortByNameButton = new Button("Name");
        sortByAmountButton = new Button("Amount");

        sortByNameButton.setOnAction(_ -> onSortButtonClick(SortingState.NAME));
        sortByAmountButton.setOnAction(_ -> onSortButtonClick(SortingState.AMOUNT));

        HBox sortButtonsContainer = new HBox();
        sortButtonsContainer.getChildren().add(sortByNameButton);
        sortButtonsContainer.getChildren().add(sortByAmountButton);

        itemsContainer = new VBox();
        itemAmountPanes = new ArrayList<>();
        for (String itemName : Game.getInstance().itemManager.itemByName.keySet()) {
            if (itemName.equals("energy")) continue;

            ItemAmountPane itemAmountPane = new ItemAmountPane(itemName);
            itemAmountPanes.add(itemAmountPane);
            itemsContainer.getChildren().add(itemAmountPane);
        }

        setTop(sortButtonsContainer);
        setCenter(itemsContainer);

        sortingState = SortingState.NAME;
        isSortDescending = false;

        applySorting();
    }

    private void onSortButtonClick(SortingState targetState) {
        if (targetState == sortingState) {
            isSortDescending = !isSortDescending;
        } else if (targetState == SortingState.NAME) {
            isSortDescending = false;
        } else if (targetState == SortingState.AMOUNT) {
            isSortDescending = true;
        }
        sortingState = targetState;
        applySorting();
    }

    private void applySorting() {
        String arrow = isSortDescending ? "▾" : "▴";

        if (sortingState == SortingState.NAME) {
            sortByNameButton.setText("Name " + arrow);
            sortByAmountButton.setText("Amount");
        }
        if (sortingState == SortingState.AMOUNT) {
            sortByNameButton.setText("Name");
            sortByAmountButton.setText("Amount " + arrow);
        }

        Comparator<ItemAmountPane> comparator;

        if (sortingState == SortingState.NAME) {
            comparator = Comparator.comparing(pane -> pane.displayName.toLowerCase());
        } else {
            comparator = Comparator.comparing(
                pane -> Game.getInstance().itemManager.getAmount(pane.itemName),
                (a, b) -> a.cmp(b)
            );
        }
        if (isSortDescending) comparator = comparator.reversed();

        itemAmountPanes.sort(comparator);
        itemsContainer.getChildren().setAll(itemAmountPanes);
    }

    public void update() {
        for (ItemAmountPane pane : itemAmountPanes) {
            pane.update();
        }
    }
}
