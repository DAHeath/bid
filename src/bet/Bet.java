package bet;

public interface Bet {
  public int getId();
  public void acceptBid(Bid bid);
  public void resolve(int result);
}
