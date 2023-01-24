/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicasino;

public abstract class Player
{
  public User user = null;
  
  /**
   * 
   * @param amountOfBet 
   */
  public abstract void placeBet(int amountOfBet);
}
