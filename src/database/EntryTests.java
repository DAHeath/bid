package database;

import bet.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static database.DatabaseConnection.testDb;
import static junit.framework.Assert.assertEquals;

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
    assertEquals(new BetImpl(0, 10.5f), BetEntry.load(0));
  }

  @Test
  public void multipleBetPersistence() {
    new BetEntry(10.5f);
    new BetEntry(20.5f);
    assertEquals(new BetImpl(0, 10.5f), BetEntry.load(0));
    assertEquals(new BetImpl(1, 20.5f), BetEntry.load(1));

  }

  @Test
  public void bidPersistence() {
    new BidEntry(5, 10);
    assertEquals(new BidImpl(0, 5, 10), BidEntry.load(0));
  }

  @Test
  public void multipleBidPersistence() {
    new BidEntry(5, 10);
    new BidEntry(10, 20);
    assertEquals(new BidImpl(0, 5, 10), BidEntry.load(0));
    assertEquals(new BidImpl(1, 10, 20), BidEntry.load(1));
  }
}
