package component;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.item.Item;
import manager.Game;
import math.BigNum;

public class ItemAmountPane extends HBox {
    public final String itemName;
    public final String displayName;
    public final Text amountText;

    public ItemAmountPane(String itemName) {
        this.itemName = itemName;

        Item item = Game.getInstance().itemManager.getItem(itemName);
        displayName = item.displayName();

        Text displayNameText = new Text(displayName);
        StackPane displayNameContainer = new StackPane(displayNameText);
        displayNameContainer.setPrefWidth(150);
        displayNameContainer.setMinWidth(150);
        displayNameContainer.setAlignment(Pos.BASELINE_LEFT);

        amountText = new Text();
        amountText.setTextAlignment(TextAlignment.RIGHT);
        StackPane amountTextContainer = new StackPane(amountText);
        amountTextContainer.setAlignment(Pos.BASELINE_RIGHT);
        amountTextContainer.setPrefWidth(50);
        amountTextContainer.setMinWidth(50);

        getChildren().add(displayNameContainer);
        getChildren().add(amountTextContainer);
    }

    public void update() {
        BigNum amount = Game.getInstance().itemManager.getAmount(itemName);
        amountText.setText(amount.format(1, 1e6, true));
    }
}
