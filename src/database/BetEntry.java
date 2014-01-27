package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;

import java.util.List;

import static database.Database.testDb;

public class BetEntry implements Bet {
  public static final String TABLE_NAME = "bet";
  public static final String CREATE_BET_TABLE =
          "CREATE TABLE " + TABLE_NAME + "(" +
                  "id INTEGER, " +
                  "over_under FLOAT, " +
                  "PRIMARY KEY (id));";
  public static final String DROP_BET_TABLE = "DROP TABLE " + TABLE_NAME + ";";
  public static final String BET_BID = "bet_bid";
  public static final String CREATE_BET_BID_TABLE =
          "CREATE TABLE " + BET_BID + " (" +
                  "bet_id INTEGER, " +
                  "bid_id INTEGER);";
  public static final String DROP_BET_BID_TABLE = "DROP TABLE " + BET_BID + ";";

  private Bet bet;

  public static Bet load(int id) {
    float overUnder = Float.parseFloat(testDb.selectQuery(TABLE_NAME, "id=" + id, "over_under").get(0));
    Bet bet = new BetEntry(id, overUnder);
    List<String> bidIds = testDb.selectQuery(BET_BID, "bet_id=" + id, "bid_id");
    for (String bidId: bidIds)
      bet.acceptBid(BidEntry.load(Integer.parseInt(bidId)));
    return bet;
  }

  public BetEntry(int id, float overUnder) {
    bet = new BetImpl(id, overUnder);
  }

  public BetEntry(float overUnder) {
    int id = testDb.getMax(TABLE_NAME, "id") + 1;
    bet = new BetImpl(id, overUnder);
    insertToDatabase(id, overUnder);
  }

  private void insertToDatabase(int id, float overUnder) {
    testDb.updateQuery("INSERT INTO " + TABLE_NAME + " VALUES (" +
            id + "," +
            overUnder + ");");
  }

  @Override
  public int getId() {
    return bet.getId();
  }

  @Override
  public void acceptBid(Bid bid) {
    testDb.updateQuery("INSERT INTO " + BET_BID + " VALUES (" +
            getId() + "," +
            bid.getId() + ");");
    bet.acceptBid(bid);
  }

  @Override
  public void resolve(int result) {
    bet.resolve(result);
  }

  @Override
  public int hashCode() {
    return bet.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Bet && bet.equals(o);
  }

  @Override
  public String toString() {
    return bet.toString();
  }
}