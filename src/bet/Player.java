package bet;

public interface Player {
  public Bid createBid(int wager, int prediction);
  public void receiveFunds(int funds);
  public int getFunds();
  public int getId();
}
