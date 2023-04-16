import Models.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of players. The number of players can not be more than 4");
        int numOfPlayer=sc.nextInt();
        System.out.println("Please enter the names of player . ");
        int maxPlayer=4;
        if(numOfPlayer>maxPlayer){
            System.out.println("The number of players can not be more than 4");
            sc.nextLine();
        }
        else {
            sc.nextLine();
            String[]  playerNames = new String[numOfPlayer];
            for (int i = 0; i < numOfPlayer; i++) {
                int s = i + 1 ;
                System.out.println("Enter the name of " +s+" players");
              //  String name = sc.nextLine();
               // playerNames[i]=name;
                playerNames[i]=sc.nextLine();
                //sc.nextLine();
            }

            //String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
            Game game = new Game(playerNames);
            game.play();
        }
    }
}