/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicasino;

import java.util.Random;

public class Die 
{
  /**
  * Constructor for the Die class
  * Initializes value to 0
  */
  Die()
  {
    value = 0;
  }
  
  /**
  * Function that will generate a integer between 1 and 6
  * @return the rolledValue
  */
  public int roll()
  {
    int rolledValue;
      
    //Initializes a new instance of the random class
    Random numGen = new Random();
      
    //Sets rolledValue equal to a randomly generated number
    rolledValue = numGen.nextInt(6) + 1;
     
    return rolledValue;
  }
  /**
  * @return the value
  */
  public int getValue() 
  {
    return value;
  }

  /**
  * @param value the value to set
  */
  public void setValue(int value) 
  {
    this.value = value;
  }
  
  private int value;
}
