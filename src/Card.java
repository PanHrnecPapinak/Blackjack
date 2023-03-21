public class Card {

    CardColor color;
    CardSuit suit;
    CardRank rank;
    int cardValue;

    public Card(int suit, int rank) {
        this.suit = CardSuit.cardSuitPick(suit);                                                                        // we call method inside ENUM with an int (which is from parent loop inside Card class) and it returns us selected ENUM type
        this.rank = CardRank.cardRankPick(rank);                                                                        // same just for CardRank ENUM

        if (rank == 0) {                                                                                                // We use input for cardRank to assign the right value to each card
            this.cardValue = 11;                                                                                        // the first card for child loop in Deck is ACE (also 1st place in ENUM) so we set it to 11
        } else if (rank >= 10) {                                                                                        // if the rank is 10 or more, we set value to 10 - it's for TEN, JACK, QUEEN, KING cards
            this.cardValue = 10;
        } else {
        this.cardValue = rank + 1;                                                                                      // for the rest ( TWO to NINE) we just set the value same as input plus one (first index is 0 not 1)
        }

        if (this.suit == CardSuit.SPADES || this.suit == CardSuit.CLUBS) {                                              // by the CardSuit we set the color BLACK or RED
            this.color = CardColor.BLACK;
        } else {
            this.color = CardColor.RED;
        }
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public int getCardValue() {
        return cardValue;
    }
}
