package bet;

public interface Player extends Identifiable {
  public Bid createBid(int wager, int prediction);
  public void receiveFunds(int funds);
  public int getFunds();
}
