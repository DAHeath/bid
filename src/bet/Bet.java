package bet;

public interface Bet extends Identifiable {
  public void acceptBid(Bid bid);
  public void resolve(int result);
}
