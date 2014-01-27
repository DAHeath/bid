package bet;

import java.util.ArrayList;
import java.util.List;

public class BetImpl implements Bet {
  private int id;
  private float overUnder;
  private List<Bid> bids;

  public BetImpl(int id, float overUnder) {
    this.id = id;
    this.overUnder = overUnder;
    bids = new ArrayList<Bid>();
  }

  public BetImpl(float overUnder) {
    this(0, overUnder);
  }

  @Override
  public int getId() {
    return id;
  }

  public void acceptBid(Bid bid) {
    bids.add(bid);
  }

  public void resolve(int result) {
    for (Bid bid: bids)
      bid.resolve(overUnder, result);
  }

  @Override
  public int hashCode() {
    return id + 31 * (Float.floatToIntBits(overUnder) + 31 * (bids.hashCode()));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Bet))
      return false;
    Bet b = (Bet)o;
    return b.hashCode() == hashCode();
  }
}
