import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Card> myHand = new ArrayList<>();                                                                         // Arraylist for each hand - mine and enemy hands
    ArrayList<Card> enemyHand = new ArrayList<>();

    Deck gameDeck;

    public void startGame() throws InterruptedException {                                                               // firstly we get  the game ready
        this.gameDeck = new Deck();                                                                                     // create a new deck, which we will use for the game
        this.gameDeck.shuffleDeck();                                                                                    // shuffle the newly created deck
        myHand.add(gameDeck.getFirstCard());                                                                            // get two cards into players hand
        myHand.add(gameDeck.getFirstCard());
        //myHand.add(gameDeck.getCard(0)); // testing value
        //myHand.add(gameDeck.getCard(12)); // testing value

        printOutTheBlackJackGameAndReadyToPlay();                                                                       // print introduction
        getValueOfMyHand();                                                                                             // show my hand and total value of my starting hand
    }

    public void showMyHand() {
        System.out.println("Your hand: " + myHand);                                                                     // showing my hand, called when we are asked to get Value of our hand
    }

    public int getValueOfMyHand() {                                                                                     // to calculate value of players hand
        int myHandValue = 0;

        if (myHand.get(0).getRank() == CardRank.ACE && myHand.get(1).getRank() == CardRank.ACE) {                       // check if we have 2 ACES, then we stop and return 21
            System.out.println("You have two ACES! BLACKJACK!");
            return 21; // 2 aces return 21
        }
        for (int i = 0; i < myHand.size(); i++) {                                                                       // loop to calculate value of players hand and save it to myHandValue
            myHandValue = myHandValue + myHand.get(i).cardValue;
        }

        if (myHandValue > 21) {                                                                                         // check if player has more than 21 score. IF yes, search for ACE in hand.
            for (int i = 0; i < myHand.size(); i++) {                                                                   // if it finds an ACE it will lower value of hand by 10
                if (myHand.get(i).getRank() == CardRank.ACE) {
                    myHandValue = myHandValue - 10;
                }
            }
        }
        showMyHand();                                                                                                   // print cards in players hand
        System.out.println("Total value of your hand is: " + myHandValue);                                              // total value of players hand
        return myHandValue;
    }

    public boolean gameLoop() {                                                                                         // game loop running until we return false;
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("< 1 > To show your hand.");
        System.out.println("< 2 > To get TOP card from deck.");
        System.out.println("< 3 > To get BOTTOM card from deck.");
        System.out.println("< 4 > To get RANDOM card from deck.");
        System.out.println("< 5 > To let play your opponent once you're ready.");

        int pickedYou;
        try {
            Scanner scanner = new Scanner(System.in);
            pickedYou = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Please enter number from 1 to 5 which represents your choice.");
            return true;                                                                                                // we ask the player to pick up the choice using scanner,
        }
        if (pickedYou == 1) { // check my hand
            getValueOfMyHand();
            return true;
        }
        if (pickedYou == 5) {                                                                                           // end the gameLoop - return false, everything else return true which will cause to run again
            return false;
        }
        if (pickedYou == 2) {
            myHand.add(gameDeck.getFirstCard());
            System.out.println("You got a new card: " + myHand.get(myHand.size() - 1));
            return true;
        }
        if (pickedYou == 3) {
            myHand.add(gameDeck.getLastCard());
            System.out.println("You got a new card: " + myHand.get(myHand.size() - 1));
            return true;
        }
        if (pickedYou == 4) {
            myHand.add(gameDeck.getRandomCard());
            System.out.println("You got a new card: " + myHand.get(myHand.size() - 1));                                 // 3 options which cards we want to pick. the TOP, BOTTOM or RANDOM card from the deck
            return true;
        } else {
            return true;
        }

    }


        public void enemyTurn () throws InterruptedException {                                                          // when we are done, the enemy player will get their turn and this method tells what he gonna do
            int playerScore = getValueOfMyHand();                                                                       // firstly find out what is value of my hand, which will also print out my hand with value
            int enemyHandScore = 0;

            enemyHand.add(gameDeck.getFirstCard());
            enemyHand.add(gameDeck.getFirstCard());                                                                     // get two cards

            for (int i = 0; i < enemyHand.size(); i++) {
                enemyHandScore = enemyHandScore + enemyHand.get(i).cardValue;
            }

            System.out.println();
            System.out.println("Your opponent is ready to play!");
            TimeUnit.SECONDS.sleep(2);

            if (enemyHand.get(0).getRank() == CardRank.ACE && enemyHand.get(1).getRank() == CardRank.ACE) {             // check if we have 2 ACES, if so, we set  his value to 21;
                enemyHandScore = 21;
            }

            System.out.println("Total value of your opponent hand is: " + enemyHandScore + " with: " + enemyHand);      // print out the status of the enemy - hand and his total value
            TimeUnit.SECONDS.sleep(2);


            while (playerScore > enemyHandScore && playerScore < 22) {                                                  // Loop representing decisions what the enemy player will do
                System.out.println("I will not lose! Dealer! ONE MORE CARD!!!");                                        // if the opponent has a lower score than the player and the player has not exceeded 21 score
                TimeUnit.SECONDS.sleep(2);                                                                      // enemy will get a card from deck and recalculate his score. If he breaks a 21 score limit
                                                                                                                        // then we trigger a check for ACE which may lower score by 10 points if it finds ACE in hand
                enemyHand.add(gameDeck.getFirstCard());
                enemyHandScore = 0;

                for (int i = 0; i < enemyHand.size(); i++) {
                    enemyHandScore = enemyHandScore + enemyHand.get(i).cardValue;
                }
                System.out.println("Enemy drawn: " + enemyHand.get(enemyHand.size() - 1));

                TimeUnit.SECONDS.sleep(2);

                if (enemyHandScore > 21) {
                    for (int i = 0; i < enemyHand.size(); i++) {
                        if (enemyHand.get(i).getRank() == CardRank.ACE) {
                            enemyHandScore = enemyHandScore - 10;
                        }
                    }
                }
            }

            //TimeUnit.SECONDS.sleep(1);

            System.out.println("Opponent's hand: " + enemyHand);
            System.out.println("Total value of your opponent hand is: " + enemyHandScore);

            System.out.println("\n" +
                    "░█████╗░░█████╗░██╗░░░░░░█████╗░██╗░░░██╗██╗░░░░░░█████╗░████████╗██╗███╗░░██╗░██████╗░░░░░░░░░░\n" +
                    "██╔══██╗██╔══██╗██║░░░░░██╔══██╗██║░░░██║██║░░░░░██╔══██╗╚══██╔══╝██║████╗░██║██╔════╝░░░░░░░░░░\n" +
                    "██║░░╚═╝███████║██║░░░░░██║░░╚═╝██║░░░██║██║░░░░░███████║░░░██║░░░██║██╔██╗██║██║░░██╗░░░░░░░░░░\n" +
                    "██║░░██╗██╔══██║██║░░░░░██║░░██╗██║░░░██║██║░░░░░██╔══██║░░░██║░░░██║██║╚████║██║░░╚██╗░░░░░░░░░\n" +
                    "╚█████╔╝██║░░██║███████╗╚█████╔╝╚██████╔╝███████╗██║░░██║░░░██║░░░██║██║░╚███║╚██████╔╝██╗██╗██╗\n" +
                    "░╚════╝░╚═╝░░╚═╝╚══════╝░╚════╝░░╚═════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚═════╝░╚═╝╚═╝╚═╝");

            TimeUnit.SECONDS.sleep(1);
            if (enemyHandScore > 21 && playerScore < 22) {                                                              // Winning conditions ... WINS if player is lower than 22 and enemy above 21.
                System.out.println("\n" +                                                                               // Draw for same score
                        "██╗░░░██╗░█████╗░██╗░░░██╗░██╗░░░░░░░██╗██╗███╗░░██╗██╗██╗██╗\n" +                             // Loosing will be situation, when the enemy will not be over 21
                        "╚██╗░██╔╝██╔══██╗██║░░░██║░██║░░██╗░░██║██║████╗░██║██║██║██║\n" +                             // We know, that enemy will never end up with lower score because he would always draw a card
                        "░╚████╔╝░██║░░██║██║░░░██║░╚██╗████╗██╔╝██║██╔██╗██║██║██║██║\n" +
                        "░░╚██╔╝░░██║░░██║██║░░░██║░░████╔═████║░██║██║╚████║╚═╝╚═╝╚═╝\n" +
                        "░░░██║░░░╚█████╔╝╚██████╔╝░░╚██╔╝░╚██╔╝░██║██║░╚███║██╗██╗██╗\n" +
                        "░░░╚═╝░░░░╚════╝░░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═╝╚═╝╚═╝");
            } else if (enemyHandScore == playerScore) {
                System.out.println("\n" +
                        "██████╗░██████╗░░█████╗░░██╗░░░░░░░██╗\n" +
                        "██╔══██╗██╔══██╗██╔══██╗░██║░░██╗░░██║\n" +
                        "██║░░██║██████╔╝███████║░╚██╗████╗██╔╝\n" +
                        "██║░░██║██╔══██╗██╔══██║░░████╔═████║░\n" +
                        "██████╔╝██║░░██║██║░░██║░░╚██╔╝░╚██╔╝░\n" +
                        "╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░");

                System.out.println();
                System.out.println("░░░░░░░░░░░░░░░░░░░░░▄▀░░▌\n" +
                        "░░░░░░░░░░░░░░░░░░░▄▀▐░░░▌\n" +
                        "░░░░░░░░░░░░░░░░▄▀▀▒▐▒░░░▌\n" +
                        "░░░░░▄▀▀▄░░░▄▄▀▀▒▒▒▒▌▒▒░░▌\n" +
                        "░░░░▐▒░░░▀▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
                        "░░░░▌▒░░░░▒▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄\n" +
                        "░░░░▐▒░░░░░▒▒▒▒▒▒▒▒▒▌▒▐▒▒▒▒▒▀▄\n" +
                        "░░░░▌▀▄░░▒▒▒▒▒▒▒▒▐▒▒▒▌▒▌▒▄▄▒▒▐\n" +
                        "░░░▌▌▒▒▀▒▒▒▒▒▒▒▒▒▒▐▒▒▒▒▒█▄█▌▒▒▌\n" +
                        "░▄▀▒▐▒▒▒▒▒▒▒▒▒▒▒▄▀█▌▒▒▒▒▒▀▀▒▒▐░░░▄\n" +
                        "▀▒▒▒▒▌▒▒▒▒▒▒▒▄▒▐███▌▄▒▒▒▒▒▒▒▄▀▀▀▀\n" +
                        "▒▒▒▒▒▐▒▒▒▒▒▄▀▒▒▒▀▀▀▒▒▒▒▄█▀░░▒▌▀▀▄▄\n" +
                        "▒▒▒▒▒▒█▒▄▄▀▒▒▒▒▒▒▒▒▒▒▒░░▐▒▀▄▀▄░░░░▀\n" +
                        "▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▄▒▒▒▒▄▀▒▒▒▌░░▀▄\n" +
                        "▒▒▒▒▒▒▒▒▀▄▒▒▒▒▒▒▒▒▀▀▀▀▒▒▒▄▀");
            } else {
                System.out.println("\n" +
                        "██╗░░░██╗░█████╗░██╗░░░██╗  ██╗░░░░░░█████╗░░██████╗████████╗\n" +
                        "╚██╗░██╔╝██╔══██╗██║░░░██║  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝\n" +
                        "░╚████╔╝░██║░░██║██║░░░██║  ██║░░░░░██║░░██║╚█████╗░░░░██║░░░\n" +
                        "░░╚██╔╝░░██║░░██║██║░░░██║  ██║░░░░░██║░░██║░╚═══██╗░░░██║░░░\n" +
                        "░░░██║░░░╚█████╔╝╚██████╔╝  ███████╗╚█████╔╝██████╔╝░░░██║░░░\n" +
                        "░░░╚═╝░░░░╚════╝░░╚═════╝░  ╚══════╝░╚════╝░╚═════╝░░░░╚═╝░░░");

            }

        }

        private void printOutTheBlackJackGameAndReadyToPlay () throws InterruptedException {
            System.out.println("\n" +
                    "██████╗░██╗░░░░░░█████╗░░█████╗░██╗░░██╗░░░░░██╗░█████╗░░█████╗░██╗░░██╗  ░██████╗░░█████╗░███╗░░░███╗███████╗██╗\n" +
                    "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██║░██╔╝░░░░░██║██╔══██╗██╔══██╗██║░██╔╝  ██╔════╝░██╔══██╗████╗░████║██╔════╝██║\n" +
                    "██████╦╝██║░░░░░███████║██║░░╚═╝█████═╝░░░░░░██║███████║██║░░╚═╝█████═╝░  ██║░░██╗░███████║██╔████╔██║█████╗░░██║\n" +
                    "██╔══██╗██║░░░░░██╔══██║██║░░██╗██╔═██╗░██╗░░██║██╔══██║██║░░██╗██╔═██╗░  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░╚═╝\n" +
                    "██████╦╝███████╗██║░░██║╚█████╔╝██║░╚██╗╚█████╔╝██║░░██║╚█████╔╝██║░╚██╗  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗██╗\n" +
                    "╚═════╝░╚══════╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝╚═╝");

            TimeUnit.SECONDS.sleep(2);

            System.out.println("\n" +
                    "██████╗░███████╗░█████╗░██████╗░██╗░░░██╗  ████████╗░█████╗░  ██████╗░██╗░░░░░░█████╗░██╗░░░██╗██╗\n" +
                    "██╔══██╗██╔════╝██╔══██╗██╔══██╗╚██╗░██╔╝  ╚══██╔══╝██╔══██╗  ██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██║\n" +
                    "██████╔╝█████╗░░███████║██║░░██║░╚████╔╝░  ░░░██║░░░██║░░██║  ██████╔╝██║░░░░░███████║░╚████╔╝░██║\n" +
                    "██╔══██╗██╔══╝░░██╔══██║██║░░██║░░╚██╔╝░░  ░░░██║░░░██║░░██║  ██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░╚═╝\n" +
                    "██║░░██║███████╗██║░░██║██████╔╝░░░██║░░░  ░░░██║░░░╚█████╔╝  ██║░░░░░███████╗██║░░██║░░░██║░░░██╗\n" +
                    "╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░  ░░░╚═╝░░░░╚════╝░  ╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝");

            TimeUnit.SECONDS.sleep(1);
        }
    }
