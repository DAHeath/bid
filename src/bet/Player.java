package bet;

public class Player {
  private int funds;

  public Player(int initialFunds) {
    this.funds = initialFunds;
  }

  public BidImpl createBid(int wager, int prediction) {
    if (wager > funds)
      throw new InsufficientFundsException();
    funds -= wager;
    return makeBid(wager, prediction);
  }

  private BidImpl makeBid(int wager, int prediction) {
    BidImpl bid = new BidImpl(wager, prediction);
    bid.setOwner(this);
    return bid;
  }

  public void receiveFunds(int winnings) {
    funds += winnings;
  }

  public int getFunds() {
    return funds;
  }
}
