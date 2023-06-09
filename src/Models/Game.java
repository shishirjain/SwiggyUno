package Models;

import java.util.ArrayList;
import java.util.Collections;
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

            boolean present=false;
            Card topCard = discardPile.get(discardPile.size() - 1);
            for(int i=0;i<currentPlayer.getHand().size();i++){
                if(currentPlayer.getHand().get(i).getRank().equals(topCard.getRank())||
                        currentPlayer.getHand().get(i).getSuit().equals(topCard.getSuit())){
                    present=true;
                    break;
                }
            }
            if (!present) {

                Card card = deck.dealCard();// Draw a card from the deck
                if (card == null) {
                    System.out.println("The draw pile is empty. The game is a draw.");
                    gameEnd = true;
                    break;
                } else if (card.getSuit().equals(topCard.getSuit())||card.getRank().equals(topCard.getRank())) {
                    System.out.println(currentPlayer+" drew a " + card);
                    discardPile.add(card);//if the drawn card is same as top card then play it.
                    currentPlayerIndex=(currentPlayerIndex+1)%players.size();
                    continue;
                } else {
                    System.out.println("Your hand: " + currentPlayer.getHand());
                    System.out.println("You drew a " + card);//if drawn card is not same as topcard store it in hand
                    currentPlayer.addCardToHand(card);
                    currentPlayerIndex=(currentPlayerIndex+1)%players.size();
                    continue;
                }
            }

            boolean cardPlayed = false;
            while (!cardPlayed ) {
                System.out.println("Your hand: " + currentPlayer.getHand());
                System.out.println("Enter the index of the card you want to play");
                Scanner scanner = new Scanner(System.in);
                int cardIndex = scanner.nextInt();

                while(cardIndex>currentPlayer.getHand().size()-1|| cardIndex<0){//checking the authenticity of input
                    System.out.println("invalid index!! Enter the index in range");
                    cardIndex= scanner.nextInt();
                }
                    Card selectedCard = currentPlayer.getHand().get(cardIndex);
                     topCard = discardPile.get(discardPile.size() - 1);
                    if (selectedCard.getSuit().equals(topCard.getSuit()) || selectedCard.getRank() == topCard.getRank()) {
                        System.out.println("You played a " + selectedCard);
                        currentPlayer.removeCardFromHand(selectedCard);
                        discardPile.add(selectedCard);

                        switch (selectedCard.getRank()) {
                            case Ace: // Ace
                                System.out.println("Skip the next player in turn");
                                currentPlayerIndex = (currentPlayerIndex + direction ) % players.size();
                                break;
                            case Jack: // Jack
                                scanner.nextLine();
                                currentPlayerIndex = (currentPlayerIndex + 1 ) % players.size();
                                System.out.println(players.get((currentPlayerIndex  ) % players.size())+" Draw 4 cards");
                                for (int i = 0; i < 4; i++) {
                                    Card drawnCard = deck.dealCard();
                                    if (drawnCard == null) {
                                        System.out.println("The draw pile is empty. The game is a draw.");
                                        gameEnd = true;
                                        break;
                                    }
                                    else {
                                        System.out.println(players.get(currentPlayerIndex)+" drew a " + drawnCard);
                                        players.get(currentPlayerIndex).addCardToHand(drawnCard);

                                    }
                                }
                                //currentPlayerIndex++;
                                break;
                            case Q: // Queen
                                scanner.nextLine();
                                currentPlayerIndex = (currentPlayerIndex + 1 ) % players.size();
                                System.out.println(players.get((currentPlayerIndex  ) % players.size())+" Draw 2 cards");
                                for (int i = 0; i < 2; i++) {
                                    Card drawnCard = deck.dealCard();
                                    if (drawnCard == null) {
                                        System.out.println("The draw pile is empty. The game is a draw.");
                                        gameEnd = true;
                                        break;
                                    }
                                    else {
                                        System.out.println(players.get(currentPlayerIndex)+" drew a " + drawnCard);
                                        //currentPlayerIndex = (currentPlayerIndex + 1 ) % players.size();
                                        players.get(currentPlayerIndex).addCardToHand(drawnCard);
                                    }
                                }
                                //currentPlayerIndex++;
                                break;
                            case K: // King
                                System.out.println("Reverse the order of play");
                                Collections.reverse(players);
                                currentPlayerIndex=players.size()-currentPlayerIndex-1;
                                //direction = -direction;

                               break;
                            default:
                               break;
                        }

                        if (currentPlayer.getHand().isEmpty()) {
                            System.out.println(currentPlayer.getName() + " has no more cards."+currentPlayer.getName()+"  win!");
                            gameEnd = true;
                            break;
                        }

                        cardPlayed = true;
                    }
                    else {
                        System.out.println("You cannot play that card.");
                    }
                //}
            }

            // Move to the next player


            currentPlayerIndex = (currentPlayerIndex+1) % players.size();

        }
    }
}
