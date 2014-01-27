package bet;

public class PlayerImpl implements Player {
  private final int id;
  private int funds;

  public PlayerImpl(int id, int initialFunds) {
    this.id = id;
    funds = initialFunds;
  }

  public PlayerImpl(int initialFunds) {
     this(-1, initialFunds);
  }

  public Bid createBid(int wager, int prediction) {
    if (wager > funds)
      throw new InsufficientFundsException();
    funds -= wager;
    return makeBid(wager, prediction);
  }

  private Bid makeBid(int wager, int prediction) {
    Bid bid = new BidImpl(wager, prediction);
    bid.setOwner(this);
    return bid;
  }

  public void receiveFunds(int funds) {
    this.funds += funds;
  }

  public int getFunds() {
    return funds;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public int hashCode() {
    return id + 31*funds;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Player))
      return false;
    Player p = (Player)o;
    return p.hashCode() == hashCode();
  }

  @Override
  public String toString() {
    return "id: " + id + ", funds:" + funds;
  }
}
