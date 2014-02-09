package bid;

import java.util.ArrayList;
import java.util.List;

public class Market {
  private List<Transaction> bids;
  private List<Transaction> offers;
  private List<Transaction> contracts;

  public Market() {
    bids = new ArrayList<Transaction>();
    offers = new ArrayList<Transaction>();
    contracts = new ArrayList<Transaction>();
  }

  public void resolve(int value) {
    for (Transaction t: contracts)
      t.payout(value);
  }

  public void placeBid(Trader seller, int amount) {
    bids.add(Transaction.Bid(seller, amount));
  }

  public void hitBid(Trader buyer, int amount) {
    for (Transaction t: bids) {
      if (t.isOfAmount(amount)) {
        performHit(buyer, t);
        break;
      }
    }
  }

  private void performHit(Trader buyer, Transaction t) {
    t.setBuyer(buyer);
    bids.remove(t);
    contracts.add(t);
  }


  public void placeOffer(Trader buyer, int amount) {
    offers.add(Transaction.Offer(buyer, amount));
  }

  public void liftOffer(Trader seller, int amount) {
    for (Transaction t: offers) {
      if (t.isOfAmount(amount)) {
        performLift(seller, t);
        break;
      }
    }
  }

  private void performLift(Trader seller, Transaction t) {
    t.setSeller(seller);
    offers.remove(t);
    contracts.add(t);
  }
}
