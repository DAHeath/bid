package bid;

public class Trader {
  private final Market market;
  private int funds;

  public Trader(Market market, int initialFunds) {
    this.market = market;
    funds = initialFunds;
  }

  public void placeBid(int amount) {
    market.placeBid(this, amount);
  }

  public void hitBid(int amount) {
    market.hitBid(this, amount);
  }

  public void placeOffer(int amount) {
    market.placeOffer(this, amount);
  }

  public void liftOffer(int amount) {
    market.liftOffer(this, amount);
  }

  public int getFunds() {
    return funds;
  }

  public void changeFunds(int amount) {
    funds += amount;
  }
}
