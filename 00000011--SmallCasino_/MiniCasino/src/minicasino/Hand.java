package minicasino;

/**
 * Holds an array list of cards to portray a user's hand.
 * Keeps track of the amount of ace's on user's 
 * @author Bradley Cain
 */
import java.util.*;
import minicasino.Card;

public class Hand {

  private ArrayList<Card> cards = new ArrayList<Card>();
  private Deck deck;
  private int aceCount;
  /**
   * constructor for Hand
   * @param deck 
   */
  public Hand(Deck deck) {
    this.deck = deck;
    aceCount = 0;
  }

  /**
   * Calculates the value of the hand. Face cards are worth 10 and an Ace is
   * worth 1 or 11.
   */
  public int value() {
    int total = 0;
    boolean hasAce = false;
    System.out.println("In value");
    for (Card card : cards) {
      int val = card.getCardValue();
      if (val == 1) {
        hasAce = true;
      }
      total += val;
    }

    while (total < 11 && hasAce) {
      total += 10;
    }
    return total;
  }

  /**
   * Add one card to the hand from the shoe.
   */
  public Card hit() {
    return deck.drawCards(1).get(0);
  }

  /**
   * During the play only the first card of the dealers hand should be shown.
   * @return card
   */
  public Card firstCard() {
    return cards.get(0);
  }
  /**
   * gets an instance of Hand
   * @return an array list of cards
   */
  public ArrayList<Card> getHand() {
    return this.cards;
  }
  /**
   * updates hand total to keep track of hand total for user to see
   * @param hitCard
   * @param handTotal
   * @return 
   */
  public int updateHandTotal(Card hitCard, int handTotal) {
    handTotal += hitCard.getCardValue();
    if (hitCard.getFaceValue().equals("ACE")) {
      aceCount++;
    }

    if (aceCount > 0 && handTotal > 21) {
      handTotal -= 10;
      aceCount--;
    }
    return handTotal;
  }
}
