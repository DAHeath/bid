package bid;

public class Bid {
  private final int wager;
  private final int prediction;
  private Player owner;

  public Bid(int wager, int prediction) {
    this.wager = wager;
    this.prediction = prediction;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public void resolve(float overUnder, int result) {
    if (isWinner(overUnder, result))
      owner.receiveFunds(2*wager);
  }

  private boolean isWinner(float overUnder, int result) {
    return (prediction > overUnder && result > overUnder) ||
            (prediction < overUnder && result < overUnder);
  }
}
