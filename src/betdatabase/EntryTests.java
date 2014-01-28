package betdatabase;

import bet.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static database.Database.testDb;
import static junit.framework.Assert.assertEquals;

public class EntryTests {

  @Before
  public void setUp() {
    testDb.connect();
    BidEntry.table.create();
    BetEntry.table.create();
    BetEntry.betBids.create();
    PlayerEntry.table.create();
  }

  @After
  public void tearDown() {
    BidEntry.table.drop();
    BetEntry.table.drop();
    BetEntry.betBids.drop();
    PlayerEntry.table.drop();
    testDb.disconnect();
  }

  @Test
  public void multipleBidPersistence() {
    new BidEntry(5, 10);
    new BidEntry(10, 20);
    assertEquals(new BidImpl(0, 5, 10), BidEntry.load(0));
    assertEquals(new BidImpl(1, 10, 20), BidEntry.load(1));
  }

  @Test
  public void multipleBetPersistence() {
    new BetEntry(10.5f);
    new BetEntry(20.5f);
    assertEquals(new BetImpl(0, 10.5f), BetEntry.load(0));
    assertEquals(new BetImpl(1, 20.5f), BetEntry.load(1));
  }

  @Test
  public void betAcceptBidPersistence() {
    new BetEntry(10.5f).acceptBid(new BidEntry(5, 10));
    Bet exp = new BetImpl(10.5f);
    exp.acceptBid(new BidImpl(5, 10));
    assertEquals(exp, BetEntry.load(0));
  }

  @Test
  public void multiplePlayerPersistence() {
    new PlayerEntry(10);
    new PlayerEntry(20);
    assertEquals(new PlayerImpl(0, 10), PlayerEntry.load(0));
    assertEquals(new PlayerImpl(1, 20), PlayerEntry.load(1));
  }

  @Test
  public void bidOwnerPersistence() {
    new BidEntry(10, 20).setOwner(new PlayerEntry(10));
    Bid exp = new BidImpl(10, 20);
    exp.setOwner(new PlayerImpl(0, 10));
    assertEquals(exp, BidEntry.load(0));
  }

  @Test
  public void fundsPersistence() {
    Player p = new PlayerEntry(10);
    p.receiveFunds(5);
    p.createBid(10, 20);
    assertEquals(new PlayerImpl(0, 5), PlayerEntry.load(0));
  }
}
