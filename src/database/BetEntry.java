package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;

import static database.DatabaseConnection.testDb;

public class BetEntry implements Bet {
  public static final String TABLE_NAME = "bets";
  public static final String TABLE_CREATION_QUERY =
          "CREATE TABLE " + TABLE_NAME + "(" +
                  "id INTEGER, " +
                  "over_under FLOAT, " +
                  "PRIMARY KEY (id));";
  public static final String TABLE_DROP_QUERY = "DROP TABLE " + TABLE_NAME + ";";

  private Bet bet;

  public static Bet load(int id) {
    float overUnder = Float.parseFloat(testDb.selectQuery(TABLE_NAME, "id=" + id, "over_under"));
    return new BetEntry(id, overUnder);
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
    DatabaseConnection.testDb.updateQuery("INSERT INTO " + TABLE_NAME + " VALUES (" +
            id + "," +
            overUnder + ");");
  }

  @Override
  public int getId() {
    return bet.getId();
  }

  @Override
  public void acceptBid(Bid bid) {
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
}