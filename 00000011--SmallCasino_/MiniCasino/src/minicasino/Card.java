package minicasino;

/**
 * 
 * @author Bradley Cain
 */
public class Card {

  private suit cardSuit;
  private faceValue card;

  /**
   * creates an enumerated data type to portray 13 different cards with their
   * card value and face value.
   */
  public static enum faceValue {
    TWO(2, "TWO"), THREE(3, "THREE"), FOUR(4, "FOUR"),
    FIVE(5, "FIVE"), SIX(6, "SIX"), SEVEN(7, "SEVEN"), EIGHT(8, "EIGHT"),
    NINE(9, "NINE"), TEN(10, "TEN"), JACK(10, "JACK"), QUEEN(10, "QUEEN"),
    KING(10, "KING"), ACE(11, "ACE");

    public int cardValue;
    public String faceValue;

    private faceValue(int cardValue, String faceValue) {
      this.cardValue = cardValue;
      this.faceValue = faceValue;
    }


    /**
     * gets the card value of the card
     * @return 
     */
    public int getCardValue() {
      return this.cardValue;
    }


    /**
     * gets the face value of the card
     * @return 
     */
    public String getFaceValue() {
      return this.faceValue;
    }
  }


  /**
   * creates an enumerated data type to portray each of the 4 suits.
   */
  public enum suit {
    HEART, DIAMOND, SPADE, CLUB
  }


  /**
   * initializes card
   * @param card
   * @param cardSuit 
   */
  public Card(faceValue card, suit cardSuit) {
    this.card = card;
    this.cardSuit = cardSuit;
  }


  /**
   * gets the suit of the card
   * @return 
   */
  public suit getSuit() {
    return this.cardSuit;
  }


  /**
   * gets the card value 
   * @return 
   */
  public int getCardValue() {
    return this.card.getCardValue();
  }


  /**
   * get the face value of the card
   * @return 
   */
  public String getFaceValue() {
    return this.card.getFaceValue();
  }


  /**
   * gets the hexadecimal value of each card for the GUI
   * @return a hexadecimal string
   */
  public String getHexaImage() {
    String hexa = "0xDC";
    switch (this.getSuit()) {
      case CLUB:
        hexa += "D";
        break;
      case DIAMOND:
        hexa += "C";
        break;
      case HEART:
        hexa += "B";
        break;
      case SPADE:
        hexa += "A";
        break;
    }

    switch (this.getFaceValue()) {
      case "TWO":
      case "THREE":
      case "FOUR":
      case "FIVE":
      case "SIX":
      case "SEVEN":
      case "EIGHT":
      case "NINE":
        hexa += Integer.toString(this.getCardValue());
        break;
      case "TEN":
        hexa += "A";
        break;
      case "JACK":
        hexa += "B";
        break;
      case "QUEEN":
        hexa += "D";
        break;
      case "KING":
        hexa += "E";
        break;
      case "ACE":
        hexa += "1";
        break;
      default:
        hexa += "1";
    }

    int number = Integer.decode(hexa);
    return "\uD83C" + (char) number;
  }
}
