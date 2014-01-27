package database;

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
    testDb.updateQuery(BidEntry.CREATE_BID_TABLE);
    testDb.updateQuery(BetEntry.CREATE_BET_TABLE);
    testDb.updateQuery(BetEntry.CREATE_BET_BID_TABLE);
  }

  @After
  public void tearDown() {
    testDb.updateQuery(BidEntry.DROP_BID_TABLE);
    testDb.updateQuery(BetEntry.DROP_BET_TABLE);
    testDb.updateQuery(BetEntry.DROP_BET_BID_TABLE);
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
    Bet bet = new BetEntry(10.5f);
    bet.acceptBid(new BidEntry(5, 10));
    Bet exp = new BetImpl(10.5f);
    exp.acceptBid(new BidImpl(5, 10));
    assertEquals(exp, BetEntry.load(0));
  }
}
