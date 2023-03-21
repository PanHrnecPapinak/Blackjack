import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    Random random = new Random();
    ArrayList<Card> deck = new ArrayList<>();

    public Deck() {                                                                                                     // double loop to build up the deck and fill up with unique cards
        for (int i = 0; i < 4; i++) {                                                                                   // parent loop 4 times for each card suit
            for (int j = 0; j < 13; j++) {                                                                              // child loop 13 times for each card rank + set their values
                deck.add(new Card(i, j));                                                                               // we have two integers using to set up the cards
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public void removeCard(int i) {
        deck.remove(i);
    }

    public Card getFirstCard() {
        Card myCard = deck.get(0);
        deck.remove(0);
        return myCard;
    }

    public Card getLastCard() {
        Card myCard = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return myCard;
    }

    public Card getRandomCard() {
        int RNG = random.nextInt(0, deck.size() - 1);
        Card myCard = deck.get(RNG);
        deck.remove(RNG);
        return myCard;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "random=" + random +
                ", deck=" + deck +
                '}';
    }
}
