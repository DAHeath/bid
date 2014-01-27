package database;

import bet.Bid;
import bet.BidImpl;
import bet.Player;

import static database.DatabaseConnection.testDb;

public class BidEntry implements Bid {
  public static final String TABLE_NAME = "bids";
  public static final String TABLE_CREATION_QUERY =
          "CREATE TABLE " + TABLE_NAME + "(" +
                  "id INTEGER, " +
                  "wager INTEGER, " +
                  "prediction INTEGER, " +
                  "owner_id INTEGER, "  +
                  "PRIMARY KEY (id));";
  public static final String TABLE_DROP_QUERY = "DROP TABLE " + TABLE_NAME + ";";

  private Bid bid;

  public static Bid load(int id) {
    int wager = Integer.parseInt(testDb.selectQuery(TABLE_NAME, "id=" + id, "wager"));
    int prediction = Integer.parseInt(testDb.selectQuery(TABLE_NAME, "id=" + id, "prediction"));
    return new BidEntry(id, wager, prediction);
  }

  public BidEntry(int id, int wager, int prediction) {
    bid = new BidImpl(id, wager, prediction);
  }

  public BidEntry(int wager, int prediction) {
    int id = testDb.getMax(TABLE_NAME, "id") + 1;
    bid = new BidImpl(id, wager, prediction);
    insertToDatabase(id, wager, prediction);
  }

  private void insertToDatabase(int id, int wager, int prediction) {
    testDb.updateQuery(
            "INSERT INTO " + TABLE_NAME + " (id, wager, prediction) VALUES (" +
                    id + "," +
                    wager + "," +
                    prediction + ");");
  }

  @Override
  public int getId() {
    return bid.getId();
  }

  @Override
  public void setOwner(Player player) {
    bid.setOwner(player);
  }

  @Override
  public void resolve(float overUnder, int result) {
    bid.resolve(overUnder, result);
  }

  @Override
  public int hashCode() {
    return bid.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Bid && bid.equals(o);
  }

  @Override
  public String toString() {
    return bid.toString();
  }
}