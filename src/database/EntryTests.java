package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;
import bet.BidImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static database.DatabaseConnection.testDb;
import static junit.framework.Assert.*;

public class EntryTests {

  @Before
  public void setUp() {
    testDb.connect();
    testDb.updateQuery(BidEntry.TABLE_CREATION_QUERY);
    testDb.updateQuery(BetEntry.TABLE_CREATION_QUERY);
  }

  @After
  public void tearDown() {
    testDb.updateQuery(BidEntry.TABLE_DROP_QUERY);
    testDb.updateQuery(BetEntry.TABLE_DROP_QUERY);
    testDb.disconnect();
  }

  @Test
  public void betPersistence() {
    new BetEntry(10.5f);
    Bet bet = BetEntry.load(0);
    assertEquals(new BetImpl(10.5f), bet);
  }

  @Test
  public void bidPersistence() {
    new BidEntry(5, 10);
    Bid bid = BidEntry.load(0);
    assertEquals(new BidImpl(5, 10), bid);
  }
}
