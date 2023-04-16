import Models.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
        Game game = new Game(playerNames);
        game.play();
    }
}