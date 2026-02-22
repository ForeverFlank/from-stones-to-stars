package croissius.component;

import croissius.manager.GameManager;
import javafx.scene.layout.HBox;

public class GamePane extends HBox {

    private final StatusPane statusPane;
    private final ShooterPane playfieldPane;
    private final ShopPane shopPane;

    // TODO use dependency injection.
    // minimize Game.getInstance()....
    public GamePane(GameManager gameManager) {

        this.statusPane = new StatusPane();
        this.playfieldPane = new ShooterPane();
        this.shopPane = new ShopPane();

        getChildren().addAll(statusPane, playfieldPane, shopPane);
    }

    public void update() {
        playfieldPane.update();
    }
}