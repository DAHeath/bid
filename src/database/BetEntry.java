package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;

import static database.DatabaseConnection.testDb;


public class BetEntry implements Bet {
  public static final String TABLE_CREATION_QUERY =
          "CREATE TABLE bets(" +
                  "id INTEGER, " +
                  "over_under FLOAT, " +
                  "PRIMARY KEY (id));";
  public static final String TABLE_DROP_QUERY = "DROP TABLE bets;";

  private static int id = 0;

  private Bet bet;

  public static Bet load(int id) {
    float overUnder = Float.parseFloat(testDb.selectQuery("bets", "id=" + id, "over_under"));
    return new BetEntry(id, overUnder);
  }

  public BetEntry(int id, float overUnder) {
    bet = new BetImpl(id, overUnder);
  }

  public BetEntry(float overUnder) {
    bet = new BetImpl(id, overUnder);
    insertToDatabase(overUnder);
    id++;
  }

  private void insertToDatabase(float overUnder) {
    DatabaseConnection.testDb.updateQuery("INSERT INTO bets VALUES (" +
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