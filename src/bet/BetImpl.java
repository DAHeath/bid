package bet;

import java.util.ArrayList;
import java.util.List;

public class BetImpl implements Bet {
  private float overUnder;
  private List<Bid> bids;

  public BetImpl(float overUnder) {
    this.overUnder = overUnder;
    bids = new ArrayList<Bid>();
  }

  public void acceptBid(Bid bid) {
    bids.add(bid);
  }

  public void resolve(int result) {
    for (Bid bid: bids)
      bid.resolve(overUnder, result);
  }
}
