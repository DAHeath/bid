package bid;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class BidTests {

  private static final int INITIAL_FUNDS = 100;

  private Market market;
  private Trader seller;
  private Trader buyer;

  @Before
  public void setUp() {
    market = new Market();
    seller = new Trader(market, INITIAL_FUNDS);
    buyer = new Trader(market, INITIAL_FUNDS);
  }

  @Test
  public void bidPayout() {
    seller.placeBid(50);
    buyer.hitBid(50);
    market.resolve(100);
    assertEquals(50, seller.getFunds());
    assertEquals(150, buyer.getFunds());
  }

  @Test
  public void offerPayout() {
    buyer.placeOffer(50);
    seller.liftOffer(50);
    market.resolve(100);
    assertEquals(50, seller.getFunds());
    assertEquals(150, buyer.getFunds());
  }
}
