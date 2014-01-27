package bet;

public class BidImpl implements Bid {
  private int id;
  private final int wager;
  private final int prediction;
  private Player owner;

  public BidImpl(int id, int wager, int prediction) {
    this.id = id;
    this.wager = wager;
    this.prediction = prediction;
  }

  public BidImpl(int wager, int prediction) {
    this(0, wager, prediction);
  }

  @Override
  public int getId() {
    return id;
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

  @Override
  public int hashCode() {
    int largePrime = 31;
    int ownerHash = owner == null ? 1 : owner.hashCode();
    return largePrime * (id + largePrime * (wager + largePrime * (prediction + largePrime * ownerHash)));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Bid))
      return false;
    Bid b = (Bid)o;
    return b.hashCode() == hashCode();
  }

  @Override
  public String toString() {
    return "id: " + id + ", wager: " + wager + ", prediction: " + prediction;
  }
}
