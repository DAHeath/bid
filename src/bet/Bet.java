package bet;

public interface Bet {
  public void acceptBid(Bid bid);
  public void resolve(int result);
}
