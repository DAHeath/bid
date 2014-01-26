package bid;

import java.util.ArrayList;
import java.util.List;

public class Bet {
  private float overUnder;
  private int pot;
  private List<Bid> bids;

  public Bet(float overUnder) {
    this.overUnder = overUnder;
    bids = new ArrayList<Bid>();
  }

  public void acceptBid(Bid bid) {
    bids.add(bid);
    pot += bid.getWager();
  }

  public void resolve(int result) {
    int winnings = pot/countWinners(result);
    for (Bid bid: bids)
      if (bid.isWinner(overUnder, result))
        bid.payout(winnings);
  }

  private int countWinners(int result) {
    int numWinners = 0;
    for(Bid bid: bids)
      if (bid.isWinner(overUnder, result))
        numWinners++;
    return numWinners;
  }
}
