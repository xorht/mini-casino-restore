package minicasino;

/**
 * 
 * @author juwuanturner_howard
 */
public class BlackjackPlayer extends Player {
  private User user;
  
  public BlackjackPlayer() {
    user = User.getUser();
  }

  @Override
  public void placeBet(int amountOfBet) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
