/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicasino;

public abstract class Game
{
  Player gamePlayer = null; 
  int currentBet = 0;
  
  /**
   * 
   */
  abstract void play();
  
  /**
   * 
   * @param amountRequested
   * @return 
   */
  static public boolean checkFundVailability(int amountRequested)
  {
    if(amountRequested >= User.getUser().getCurrency())
      return true;
    return false;
  }
  abstract void returnHome();
}