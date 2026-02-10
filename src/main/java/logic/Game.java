package logic;

public class Game {
    private static Game instance = new Game();

    private Game() {

    }

    public static Game getInstance() {
        return instance;
    }
}
