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

  public int getWager() {
    return wager;
  }

  public boolean isWinner(float overUnder, int result) {
    if (prediction > overUnder && result > overUnder)
      return true;
    else if (prediction < overUnder && result < overUnder)
      return true;
    else
      return false;
  }

  public void payout(int winnings) {
    owner.receivePayment(winnings);
  }
}
