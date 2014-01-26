package bid;

public class Player {
  private int funds;

  public Player(int initialFunds) {
    this.funds = initialFunds;
  }

  public Bid createBid(int wager, int prediction) {
    if (wager > funds)
      throw new InsufficientFundsException();
    funds -= wager;
    return makeBid(wager, prediction);
  }

  private Bid makeBid(int wager, int prediction) {
    Bid bid = new Bid(wager, prediction);
    bid.setOwner(this);
    return bid;
  }

  public void receivePayment(int winnings) {
    funds += winnings;
  }

  public int getFunds() {
    return funds;
  }
}
