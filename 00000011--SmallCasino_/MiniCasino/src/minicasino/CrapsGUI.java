package minicasino;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author kaleb_000
 */
public class CrapsGUI {

  static int rollCheck;
  static Craps craps = new Craps();

  public static Scene createScene() {
    BorderPane root = new BorderPane();

    //General Design
    root.setPadding(new Insets(15, 20, 10, 10));
    root.setStyle("-fx-background-color: green;");

    //Begin build top of pane
    //Builds in a left to right order
    HBox addTHBox = new HBox();
    addTHBox.setPadding(new Insets(15, 12, 15, 12));
    addTHBox.setSpacing(10);

    //Create a grid pane for data management
    GridPane tPane = new GridPane();
    tPane.setPadding(new Insets(20));
    tPane.setHgap(25);
    tPane.setVgap(15);

    //Print user Data to top
    Label user = new Label("Username");
    user.setFont(new Font("Arial Black", 24));
    user.setTextFill(Color.web("#FFFF00"));
    tPane.add(user, 0, 0, 1, 1);
    Label userName = new Label(craps.gamePlayer.user.getName());
    userName.setFont(new Font("Arial Black", 18));
    userName.setTextFill(Color.web("#FFFF00"));
    tPane.add(userName, 0, 1, 1, 1);
    Label experience = new Label("Experience");
    experience.setFont(new Font("Arial Black", 24));
    experience.setTextFill(Color.web("#FFFF00"));
    tPane.add(experience, 1, 0, 1, 1);
    Label userExperience = new Label(Integer.toString(
            craps.gamePlayer.user.getExperience()));
    userExperience.setFont(new Font("Arial Black", 18));
    userExperience.setTextFill(Color.web("#FFFF00"));
    tPane.add(userExperience, 1, 1, 1, 1);
    Label currency = new Label("Currency");
    tPane.add(currency, 2, 0, 1, 1);
    currency.setFont(new Font("Arial Black", 24));
    currency.setTextFill(Color.web("#FFFF00"));
    Label curCurrency = new Label(Integer.toString(
            craps.gamePlayer.user.getCurrency()));
    curCurrency.setFont(new Font("Arial Black", 18));
    curCurrency.setTextFill(Color.web("#FFFF00"));
    tPane.add(curCurrency, 2, 1, 1, 1);

    //Set a spacer to push Return to right
    final Pane spacer = new Pane();
    addTHBox.setHgrow(spacer, Priority.ALWAYS);
    spacer.setMinSize(10, 1);

    //Create a "Return to Home" button
    final Button returnButton = new Button("Return to Home");
    returnButton.setPrefSize(125, 50);
    returnButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Stage stage;
        Parent root = null;
        stage = (Stage) returnButton.getScene().getWindow();
        try {
          root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        } catch (IOException ex) {
          Logger.getLogger(CrapsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
      }
    });

    //Adds elements to the HBox
    addTHBox.getChildren().addAll(tPane, spacer, returnButton);

    root.setTop(addTHBox);
    //End building of top pane

    //Begin building left pane
    //Initialize a new vertical box
    VBox addLVBox = new VBox();
    addLVBox.setPadding(new Insets(15, 12, 15, 12));
    addLVBox.setSpacing(10);

    //Create a gridpane for space management
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(20));
    pane.setHgap(25);
    pane.setVgap(15);

    //Create a title for gridPane
    Label gridTitle = new Label("Game Info");
    gridTitle.setFont(new Font("Arial Black", 24));
    gridTitle.setTextFill(Color.web("#FFFF00"));
    pane.add(gridTitle, 0, 0, 1, 1);

    //Display current roll
    Label roll = new Label("Current Roll:");
    roll.setFont(new Font("Arial Black", 20));
    roll.setTextFill(Color.web("#FFFF00"));
    pane.add(roll, 0, 1, 1, 1);
    Label die = new Label();
    die.setFont(new Font("Arial Black", 28));
    die.setTextFill(Color.web("#FFFF00"));
    pane.add(die, 0, 2, 1, 1);

    //Display Target Value
    Label target = new Label("Target Roll");
    target.setFont(new Font("Arial Black", 20));
    target.setTextFill(Color.web("#FFFF00"));
    pane.add(target, 0, 3, 1, 1);
    Label targetV = new Label(Integer.toString(craps.targetDiceValue));
    targetV.setFont(new Font("Arial Black", 18));
    targetV.setTextFill(Color.web("#FFFF00"));
    pane.add(targetV, 0, 4, 1, 1);

    //Display current bet
    Label bet = new Label("Current Bet:");
    bet.setFont(new Font("Arial Black", 20));
    bet.setTextFill(Color.web("#FFFF00"));
    pane.add(bet, 0, 5, 1, 1);
    Label cBet = new Label(Integer.toString(craps.gamePlayer.getCurrentBet()));
    cBet.setFont(new Font("Arial Black", 18));
    cBet.setTextFill(Color.web("#FFFF00"));
    pane.add(cBet, 0, 6, 1, 1);

    //add elements to VBox
    addLVBox.getChildren().addAll(pane);

    root.setLeft(addLVBox);
    //End building of left pane

    //Begin building the center pane
    VBox addCVBox = new VBox();
    addCVBox.setPadding(new Insets(15, 12, 15, 12));
    addCVBox.setSpacing(10);

    //Display win or loss
    Label gameStatus = new Label("Please roll the die!");
    gameStatus.setFont(new Font("Arial Black", 23));
    gameStatus.setTextFill(Color.web("#FFFF00"));

    //Display the die
    Label dispDie = new Label();
    dispDie.setFont(new Font("Arial Black", 50));
    dispDie.setTextFill(Color.web("#FFFF00"));

    addCVBox.getChildren().addAll(gameStatus, dispDie);
    root.setCenter(addCVBox);
    //End building center pane

    //Begin building right pane
    VBox addRVBox = new VBox();
    addRVBox.setPadding(new Insets(15, 12, 15, 12));
    addRVBox.setSpacing(10);

    //Create the "Roll" button
    final Button rollButton = new Button("Roll Die");
    rollButton.setPrefSize(150, 150);
    rollButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        rollDieOnClick();
        gameStatus.setText(showGameStatus());
        die.setText(unicodeHandler(craps.Dice[0].getValue())
                + ", " + unicodeHandler(craps.Dice[1].getValue()));
        dispDie.setText(unicodeHandler(craps.Dice[0].getValue())
                + ", " + unicodeHandler(craps.Dice[1].getValue()));
        targetV.setText(Integer.toString(craps.targetDiceValue));
        cBet.setText(Integer.toString(craps.gamePlayer.getCurrentBet()));
        curCurrency.setText(Integer.toString(
                craps.gamePlayer.user.getCurrency()));
        userExperience.setText(
                Integer.toString(craps.gamePlayer.user.getExperience()));
      }
    });

    //Create the "Bet" button
    final Button betButton = new Button("Place Bet");
    betButton.setPrefSize(150, 150);
    betButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        openBetWindow();
        cBet.setText(Integer.toString(craps.gamePlayer.getCurrentBet()));
        curCurrency.setText(Integer.toString(
                craps.gamePlayer.user.getCurrency()));
      }
    });

    //Create the "Reset" Button
    final Button resetButton = new Button("Reset");
    resetButton.setPrefSize(150, 150);
    resetButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        craps.gamePlayer.placeBet(0);
        for (int j = 0; j < 2; j++) {
          craps.Dice[j].setValue(0);
        }
        gameStatus.setText("");
        die.setText(Integer.toString(craps.Dice[0].getValue())
                + ", " + Integer.toString(craps.Dice[1].getValue()));
        craps.targetDiceValue = 0;
        targetV.setText(Integer.toString(craps.targetDiceValue));
        cBet.setText(Integer.toString(craps.gamePlayer.getCurrentBet()));
        gameStatus.setText("Please roll the die!");
        rollCheck = 0;
      }
    }
    );

    //Add buttons to the box
    addRVBox.getChildren().addAll(rollButton, betButton, resetButton);

    //Add to borderpane
    root.setRight(addRVBox);
    //End building of right pane

    //Initialize the GUI
    Scene scene = new Scene(root, 1280, 720);

    return scene;
  }

  /**
   * Calls the rollDie function from the CrapsPlayer class and begins the
   * general logic of the game.
   */
  private static void rollDieOnClick() {
    craps.gamePlayer.rollDie(craps.Dice[0], craps.Dice[1]);

    if (rollCheck == 0) {
      craps.checkFirstRoll();
    } else {
      craps.checkNextRoll();
    }
  }

  /**
   * Opens a window that accepts an integer and sets it as the current bet for
   * the game.
   */
  private static void openBetWindow() {
    TextInputDialog getBet = new TextInputDialog();

    getBet.setTitle("Place Bet");
    getBet.setHeaderText("Enter bet amount");

    Optional<String> result = getBet.showAndWait();

    result.ifPresent(bet -> {
      if (Integer.parseInt(bet) < craps.gamePlayer.user.getCurrency()) {
        craps.gamePlayer.placeBet(Integer.parseInt(bet));
      } else {

      }
    });
  }

  private static String unicodeHandler(int currentValue) {
    String value;
    switch (currentValue) {
      case 1:
        value = "\u2680";
        break;
      case 2:
        value = "\u2681";
        break;
      case 3:
        value = "\u2682";
        break;
      case 4:
        value = "\u2683";
        break;
      case 5:
        value = "\u2684";
        break;
      case 6:
        value = "\u2685";
        break;
      default:
        value = Integer.toString(currentValue);
    }
    return value;
  }

  private static String showGameStatus() {
    String returnMessage;

    if (rollCheck == 0) {
      if (craps.currentDiceSum == 7 || craps.currentDiceSum == 11) {
        returnMessage = "Congrats! You rolled a "
                + Integer.toString(craps.currentDiceSum) + "! You won "
                + Integer.toString(craps.gamePlayer.getCurrentBet() * 2)
                + " dollars! Press restart to play again";
        rollCheck++;
        craps.gamePlayer.placeBet(0);
        return returnMessage;
      } //Checks if the user rolled a 2,3 or 12, which is an automatic loss
      else if (craps.currentDiceSum == 2 || craps.currentDiceSum == 3
              || craps.currentDiceSum == 12) {
        returnMessage = "You rolled a " + Integer.toString(craps.currentDiceSum)
                + "... You lost your bet... Wump wump..."
                + "Press restart to play again.";

        craps.gamePlayer.placeBet(0);
        return returnMessage;
      } //Continues informs user to roll again
      else {
        returnMessage = "You have not won yet! Keep rolling for the target"
                + " value.";
        rollCheck++;
        return returnMessage;
      }
    } else {
      if (craps.compareDiceValues() == true) {
        returnMessage = "Congrats! You rolled a "
                + Integer.toString(craps.currentDiceSum) + "! You won "
                + Integer.toString(craps.gamePlayer.getCurrentBet() * 2)
                + " dollars! Press restart to play again";

        craps.gamePlayer.placeBet(0);
        return returnMessage;
      } else if (craps.currentDiceSum == 7 || craps.currentDiceSum == 2
              || craps.currentDiceSum == 3 || craps.currentDiceSum == 12) {
        returnMessage = "You rolled a " + Integer.toString(craps.currentDiceSum)
                + "... You lost your bet... Wump wump..."
                + "Press restart to play again.";

        craps.gamePlayer.placeBet(0);
        return returnMessage;
      } else {
        returnMessage = "You have not won yet! Keep rolling for the target"
                + " value.";
        return returnMessage;
      }
    }
  }
}
