package minicasino;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kaleb.nelson
 */
public class Craps extends Game {
  
  @Override
  public void play(){}
  
  @Override
  public void returnHome() {}
  
  /**
   * Constructor for the Craps class.
   */
  public Craps() {
    for(int i = 0; i < 2; i++) {
      Dice[i] = new Die();
    }
  }
  

  /**
   * Checks the first roll of the game; Uses a tracker variable inside the game
   * logic to check if needed
   */
  void checkFirstRoll() {
    //Gets the total value of the dice roll
    getDiceTotalValue(Dice[0],Dice[1]);
    
    //If/else block for checking of die
    //Checks if the user rolled 7 or 11, which is a win
    if(this.currentDiceSum == 7 || this.currentDiceSum== 11) {
      gamePlayer.user.addFunds(gamePlayer.getCurrentBet() * 2);
      this.targetDiceValue = 0;
      gamePlayer.user.addExperience(11);
    }
    //Checks if the user rolled a 2,3 or 12, which is an automatic loss
    else if(this.currentDiceSum == 2 || this.currentDiceSum == 3 || 
            this.currentDiceSum == 12) {
      this.targetDiceValue = 0;
    } else {
      //Continues informs user to roll again
      this.targetDiceValue = this.currentDiceSum;
    }
  }
  
  /**
   * This function checks the next roll of the die, being implemented with a 
   * tracking variable inside of the game logic
   */
  void checkNextRoll() {
    getDiceTotalValue(Dice[0],Dice[1]);
    
    if(compareDiceValues() == true) {
      gamePlayer.user.addFunds(gamePlayer.getCurrentBet() * 2);
      this.targetDiceValue = 0;
    } else if (this.currentDiceSum == 2 || this.currentDiceSum == 3 || 
            this.currentDiceSum == 7 || this.currentDiceSum == 12) {
      this.targetDiceValue = 0;
    } 
  }
  

  /**
   * This function serves to check if currentDiceSum and targetDiceValue are 
   * are equal to one another within checkNextRoll()
   * @return true if the currentDiceSum and targetDiceValue are equal
   * @return false if currentDiceSum and targetDiceValue are not equal
   */
  boolean compareDiceValues() {
    if(this.currentDiceSum == this.targetDiceValue) {
      return true;
    } else {
      return false;
    }
  }

  
  /**
   * This function serves to add together the two values of the die objects
   * to give an integer for use in later functions.
   * @param one carries the first index of the Dice variable in the class
   * @param two carries the second index of the Dice variable in the class
   */
  private void getDiceTotalValue(Die one, Die two) {
    int dieSum;
    
    dieSum = one.getValue() + two.getValue();
    
    this.currentDiceSum = dieSum;
  }
  
  //Variables for use in class
  Die[] Dice = new Die[2];
  CrapsPlayer gamePlayer = new CrapsPlayer();
  int targetDiceValue;
  int currentDiceSum;
}
