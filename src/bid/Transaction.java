package bid;

public class Transaction {
  private int amount;
  private Trader seller;
  private Trader buyer;

  public static Transaction Bid(Trader seller, int amount) {
    Transaction t = new Transaction(amount);
    t.seller = seller;
    return t;
  }

  public static Transaction Offer(Trader buyer, int amount) {
    Transaction t = new Transaction(amount);
    t.buyer = buyer;
    return t;
  }

  private Transaction(int amount) {
    this.amount = amount;
  }

  public boolean isOfAmount(int amount) {
    return amount == this.amount;
  }

  public void setBuyer(Trader buyer) {
    this.buyer = buyer;
  }

  public void payout(int value) {
    seller.changeFunds(amount - value);
    buyer.changeFunds(value - amount);
  }

  public void setSeller(Trader seller) {
    this.seller = seller;
  }
}
