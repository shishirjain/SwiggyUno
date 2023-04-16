package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    int numberOfPlayer;
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private int direction;

    public Game(String[] playerNames) {
        players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        deck = new Deck();
        discardPile = new ArrayList<>();
        currentPlayerIndex = 0;
        direction = 1;

        // Deal cards to players
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                Card card = deck.dealCard();
                player.addCardToHand(card);
            }
        }

        // Place the top card of the deck on the discard pile
        Card card = deck.dealCard();
        discardPile.add(card);
    }

    public void play() {
        boolean gameEnd = false;
        while (!gameEnd) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn");
            System.out.println("Top card on discard pile: " + discardPile.get(discardPile.size() - 1));

            boolean cardPlayed = false;
            while (!cardPlayed) {
                System.out.println("Your hand: " + currentPlayer.getHand());
                System.out.println("Enter the index of the card you want to play, or -1 to draw a card:");
                Scanner scanner = new Scanner(System.in);
                int cardIndex = scanner.nextInt();

                if (cardIndex == -1) {

                    Card card = deck.dealCard();// Draw a card from the deck
                    if (card == null) {
                        System.out.println("The draw pile is empty. The game is a draw.");
                        gameEnd = true;
                        break;
                    } else {
                        System.out.println("You drew a " + card);
                        currentPlayer.addCardToHand(card);
                    }
                } else {
                    Card selectedCard = currentPlayer.getHand().get(cardIndex);
                    Card topCard = discardPile.get(discardPile.size() - 1);
                    if (selectedCard.getSuit().equals(topCard.getSuit()) || selectedCard.getRank() == topCard.getRank()) {
                        System.out.println("You played a " + selectedCard);
                        currentPlayer.removeCardFromHand(selectedCard);
                        discardPile.add(selectedCard);

                        switch (selectedCard.getRank()) {
                            case Ace: // Ace
                                System.out.println("Skip the next player in turn");
                                currentPlayerIndex = (currentPlayerIndex + direction * 2) % players.size();
                                break;
                            case Jack: // Jack
                                System.out.println("Draw 4 cards");
                                for (int i = 0; i < 4; i++) {
                                    Card drawnCard = deck.dealCard();
                                    if (drawnCard == null) {
                                        System.out.println("The draw pile is empty. The game is a draw.");
                                        gameEnd = true;
                                        break;
                                    } else {
                                        System.out.println("You drew a " + drawnCard);
                                        currentPlayer.addCardToHand(drawnCard);
                                    }
                                }
                                break;
                            case Q: // Queen
                                System.out.println("Draw 2 cards");
                                for (int i = 0; i < 2; i++) {
                                    Card drawnCard = deck.dealCard();
                                    if (drawnCard == null) {
                                        System.out.println("The draw pile is empty. The game is a draw.");
                                        gameEnd = true;
                                        break;
                                    } else {
                                        System.out.println("You drew a " + drawnCard);
                                        currentPlayer.addCardToHand(drawnCard);
                                    }
                                }
                                break;
                            case K: // King
                                System.out.println("Reverse the order of play");
                                direction = -direction;
                                break;
                            default:
                                break;
                        }

                        if (currentPlayer.getHand().isEmpty()) {
                            System.out.println(currentPlayer.getName() + " has no more cards. They win!");
                            gameEnd = true;
                            break;
                        }

                        cardPlayed = true;
                    } else {
                        System.out.println("You cannot play that card.");
                    }
                }
            }

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + direction) % players.size();
        }
    }
}
