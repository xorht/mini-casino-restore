package minicasino;

public class User
{
  static User user;
  static boolean userMade = false;

  private String name;
  private int experience;
  private int currency;
	/**
   * 
   * @return a
   */
  static final public User getUser(String name)
  {
    if(userMade == false)
    {
      System.out.println("User Made");
      userMade = true;
      user = new User(name);
      return user;
    }
    else
    {
      System.out.println("User returned");
      return user;
    }
  }
  
  static final public User getUser()
  {
    if(userMade == false)
    {
      System.out.println("User Made");
      userMade = true;
      return new User();
    }
    else
    {
      System.out.println("User returned");
      return user;
    }
  }
  
  /**
   * 
   */
  public User() {
    name = "";
    experience = currency = 0;
  }
  

/**
 * 
 * @param name 
 */  
  public User(String name)
  {
    this.name = name;
    experience = 0;
    currency = 10000;
  }

  
  /**
   * 
   * @return 
   */
  public String getName()
  {
    return name;
  }
	
  
  /**
   * 
   * @param name 
   */
  public void setname(String name)
  {
    this.name = name;
  }
    
  
  /**
   * 
   * @return 
   */
  public int getExperience()
  {
    return experience;
  }
    
  
  /**
   * 
   * @return 
   */
  public int getCurrency()
  {
    return currency;
  }
   
  
  /**
   * 
   * @param amount 
   */
  public void addFunds(int amount)
  {
    currency += amount;
  }
  
  
  /**
   * 
   * @param amount 
   */
  public void deductFunds(int amount)
  {
    currency -= amount;
  }
    
  
  /**
   * 
   * @param amount 
   */
  public void addExperience(int amount)
  {
    experience += amount; 
  }
}