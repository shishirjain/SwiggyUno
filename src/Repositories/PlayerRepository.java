package Repositories;
import Models.Card;
import Models.Hand;
import Models.Player;

import java.util.List;

public class PlayerRepository {
    private List<Player> players;

    public PlayerRepository(List<String> playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }

    public void addCardToHand(int playerIndex, Hand hand, Card card) {
        players.get(playerIndex).getHand().addCards(card);

    }


    public Player getPlayer(int index) {
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return players.size();
    }
}