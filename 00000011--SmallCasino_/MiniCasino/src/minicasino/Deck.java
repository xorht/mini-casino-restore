package minicasino;

/**
 * initializes a deck for the dealer to deal from.
 * @author Bradley Cain
 */
import java.util.*;
import minicasino.Card;

public class Deck {

  private ArrayList<Card> deck;

  /**
   * Constructor for Deck.
   * initializes all 13 cards with one for every suit. 52 cards in total 
   */
  public Deck() {
    this.deck = new ArrayList<Card>();
    for (Card.faceValue fv : Card.faceValue.values()) {
      for (Card.suit s : Card.suit.values()) {
        deck.add(new Card(fv, s));
      }
    }
  }


  /**
   * removes a card from the deck
   * @return removed card
   */
  public Card removeCard() {
    Card card = deck.remove(0);
    return card;
  }


  /**
   * shuffle the deck of cards
   */
  public void shuffle() {
    Collections.shuffle(deck);
  }


  /**
   * get an instance of deck
   * @return 
   */
  public ArrayList<Card> getDeck() {
    return this.deck;
  }


  /**
   * draws a card from the deck and re-initializes the deck if it is empty.
   * @param numCardsToDeal
   * @return a drawn card
   */
  public ArrayList<Card> drawCards(int numCardsToDeal) {
    ArrayList<Card> cards = new ArrayList<Card>(numCardsToDeal);

    for (int i = 0; i < numCardsToDeal; i++) {
      if (deck.isEmpty()) {
        deck =  (new Deck()).deck;
        } else {
        Collections.shuffle(deck);
      }
      cards.add(deck.remove(0));
    }
    return cards;
  }
}
