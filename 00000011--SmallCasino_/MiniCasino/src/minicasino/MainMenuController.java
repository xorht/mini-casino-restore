//code by: Juwuan Turner-Howard, Brad Cain, Luke Newman, Kaleb Nelson, and Rachel Gelmis
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
import javafx.scene.image.ImageView;
import minicasino.Deck;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {

  @FXML
  private Button blackjack;
 @FXML
  private Button crapsButton;
  @FXML
  private Button userButton;
  @FXML
  private Button instructions;
  @FXML
  private Label bankAccount = new Label();
  @FXML
  private Button mainMenuButton;
  @FXML
  public Label username = new Label();
  @FXML
  private Label currency = new Label();
  @FXML
  private Label experience = new Label();
  @FXML
  private ImageView profilePic;

  private static Deck deck = new Deck();
  private static Hand handTest = new Hand(deck);
  private static Hand dealerHandTest = new Hand(deck);
  private static int handTotal = 0;
  private static int playerInitialValue = 0;
  private static int dealerHandTotal = 0;
  private static int dealerInitialValue = 0;
  private static int posCounter = 0;
  private static int bet = 0;
  private static int Account = User.getUser().getCurrency();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    bankAccount.setText("Bank Account: " + Integer.toString(User.getUser().getCurrency()));
    username.setText(User.getUser().getName());
    currency.setText(Integer.toString(User.getUser().getCurrency()));
    experience.setText(Integer.toString(User.getUser().getExperience()));
  }

  //Start of MainMenu
  @FXML
  private void playCraps(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;
    stage = (Stage) crapsButton.getScene().getWindow();
    Scene scene = CrapsGUI.createScene();
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
  }

  @FXML
  private void playBlackjack(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;
    stage = (Stage) blackjack.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("BlackJack.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
  }

  @FXML
  private void playInstructions(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;
    stage = (Stage) instructions.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("HowToGUI.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
  }

  @FXML
  private void playUser(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;
    stage = (Stage) userButton.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("UserGUI.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
    stage.setFullScreen(true);
  }
  //End of MainMenu
}
