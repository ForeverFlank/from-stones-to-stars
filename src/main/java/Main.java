import component.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import manager.Game;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add("stylesheet.css");

        GamePane gamePane = new GamePane();
        root.getChildren().add(gamePane);

        primaryStage.setTitle("From Stones to Stars");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Game.init();
        launch(args);
    }
}
