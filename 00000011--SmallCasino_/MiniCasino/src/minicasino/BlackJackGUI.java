package minicasino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static minicasino.MiniCasino.user;

/**
 * Implements the graphical user interface for the user to play
 * the game of blackjack.
 * @author juwuanturner_howard, Bradley Cain
 */
public class BlackJackGUI implements Initializable {

  @FXML
  private Label bankAccount = new Label();
  @FXML
  private Button instructions;
  @FXML
  private Button stay;
  @FXML
  private Button hit;
  @FXML
  private Label handValue;
  @FXML
  private Label firstCard;
  @FXML
  private Label secondCard;
  @FXML
  private Label thirdCard;
  @FXML
  private Label fourthCard;
  @FXML
  private Button mainMenu;
  @FXML
  private Label dealerFirstCard;
  @FXML
  private Label dealerSecondCard;
  @FXML
  private Label dealerThirdCard;
  @FXML
  private Label dealerFourthCard;
  @FXML
  private Label dealerFifthCard;
  @FXML
  private Label fifthCard;
  @FXML
  private Label action;
  @FXML
  private Button deal;
  @FXML
  private Label dealerValue;
  @FXML
  private Label header;
  @FXML
  private Label playerFunds;
  @FXML
  private Button betOption3;
  @FXML
  private Button betOption2;
  @FXML
  private Button betOption1;
  @FXML
  private Label betAmount;

  private static Deck deck = new Deck();

  private Hand handTest;
  private Hand dealerHandTest;

  private static int handTotal = 0;
  private static int playerInitialValue = 0;
  private static int dealerHandTotal = 0;
  private static int dealerInitialValue = 0;
  private static int posCounter = 0;
  private static int bet = 0;
  private static int Account = user.getCurrency();

  /**
   * implements when user clicks the Stay button on the GUI.
   * Handles the operations for dealers hand.
   * @param event 
   */ 
  @FXML
  private void stay(ActionEvent event) {
    dealerHandTotal = dealerInitialValue;
    posCounter = 1;
    while (dealerHandTotal <= 16 && handTotal <= 21) {
      Card hitCard = dealerHandTest.hit();
      dealerHandTotal += hitCard.getCardValue();
      String cardImg = hitCard.getHexaImage();
      switch (posCounter) {
        case 1:
          dealerSecondCard.setText(cardImg);
          break;
        case 2:
          dealerThirdCard.setText(cardImg);
          break;
        case 3:
          dealerFourthCard.setText(cardImg);
          break;
        case 4:
          dealerFifthCard.setText(cardImg);
          break;
      }
      dealerValue.setText(Integer.toString(dealerHandTotal));
      posCounter++;
    }

    if (handTotal > dealerHandTotal) {
      if (handTotal <= 21) {
        action.setText("WINNER WINNER CHICKEN DINNER!");
        user.addFunds(bet * 2);
        Account += bet * 2;
        bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
      } else {
        action.setText("YOU LOST!");
        bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
      }
    } else if (handTotal < dealerHandTotal) {
      if (dealerHandTotal <= 21) {
        action.setText("YOU LOST!");
        bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
      } else {
        action.setText("WINNER WINNER CHICKEN DINNER!");
        user.addFunds(bet * 2);
        Account += bet * 2;
        bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
      }
    } else {
      action.setText("PUSH!");
      user.addFunds(bet);
      Account += bet;
      bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
    }
  }


  /**
   * implements the hit function for the user to play his hand.
   * Displays the cards to the screen so the user can see his hand.
   * @param event 
   */
  @FXML
  private void hit(ActionEvent event) {
    if (handTest == null && dealerHandTest == null) {
      return;
    }

    posCounter += 1;
    Card hitCard = handTest.hit();
    handTotal = handTest.updateHandTotal(hitCard, handTotal);
    String unicodeCardImage = null;

    String cardImg = hitCard.getHexaImage();
    switch (posCounter) {
      case 1:
        thirdCard.setText(cardImg);
        break;
      case 2:
        fourthCard.setText(cardImg);
        break;
      case 3:
        fifthCard.setText(cardImg);
        break;
    }

    if (handTotal > 21) {
      action.setText("BUSTED!");
      bankAccount.setText("Bank Account: " + (Integer.toString(Account)));
      handValue.setText(Integer.toString(handTotal));
      stay(event);
    }
    handValue.setText(Integer.toString(handTotal));
  }


  /**
   * Changes the scene from Blackjack to Main menu
   * @param event
   * @throws IOException 
   */
  @FXML
  private void BJtoMM(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;
    stage = (Stage) mainMenu.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
  }


  /**
   * Starts the game and initializes the user's and dealer's hand.
   * Every time the user clicks deal it resets each hand and the deck.
   * @param event 
   */
  @FXML
  private void deal(ActionEvent event) {
    handTest = new Hand(deck);
    dealerHandTest = new Hand(deck);
    handTotal = 0;
    dealerHandTotal = 0;
    posCounter = 0;
    dealerHandTotal = 0;
    playerInitialValue = 0;
    dealerInitialValue = 0;
    firstCard.setText(null);
    secondCard.setText(null);
    thirdCard.setText(null);
    fourthCard.setText(null);
    fifthCard.setText(null);
    dealerFirstCard.setText(null);
    dealerSecondCard.setText(null);
    dealerThirdCard.setText(null);
    dealerFourthCard.setText(null);
    dealerFifthCard.setText(null);
    Account -= bet;
    user.deductFunds(bet);
    bankAccount.setText("Bank Account: " + (Integer.toString(Account)));

    for (int i = 0; i < 4; i++) {
      Card hitCard = handTest.hit();

      String cardImg = hitCard.getHexaImage();
      switch (i) {
        case 0: {
          firstCard.setText(cardImg);
          playerInitialValue = handTest.updateHandTotal(hitCard, playerInitialValue);
          break;
        }
        case 1: {
          secondCard.setText(cardImg);
          playerInitialValue = handTest.updateHandTotal(hitCard, playerInitialValue);
          break;
        }
        case 2: {
          dealerFirstCard.setText(cardImg);
          dealerInitialValue = dealerHandTest.updateHandTotal(hitCard, dealerInitialValue);
          break;
        }
        case 3: {
          dealerSecondCard.setText("\uD83C\uDCA0");
          break;
        }
      }

      if (playerInitialValue == 21) {
        action.setText("BLACKJACK! WINNER!");
        Account += (bet * 3);
        user.addFunds(bet * 3);
      } else if (dealerInitialValue == 21) {
        action.setText("YOU LOST!");
      } else {
        action.setText("HIT OR STAY?");
      }
    }
    
    handTotal = playerInitialValue;
    dealerValue.setText(Integer.toString(dealerInitialValue));
    handValue.setText(Integer.toString(playerInitialValue));
  }


  /**
   * button for the user to decide what to bet before the deal.
   * @param event 
   */
  @FXML
  private void betOption3(ActionEvent event) {
    bet = 100;
    betAmount.setText("Bet Amount = 100");
  }


  /**
   * button for the user to decide what to bet before the deal.
   * @param event 
   */
  @FXML
  private void betOption2(ActionEvent event) {
    bet = 50;
    betAmount.setText("Bet Amount = 50");
  }


  /**
   * button for the user to decide what to bet before the deal.
   * @param event 
   */
  @FXML
  private void betOption1(ActionEvent event) {
    bet = 25;
    betAmount.setText("Bet Amount = 25");
  }
  //End of BlackJack


  /**
   * initializes GUI objects in blackjack GUI
   * @param location
   * @param resources 
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    bankAccount.setText("Bank Account: " + Integer.toString(user.getCurrency()));
  }
}
