package database;

import bet.Bid;
import bet.Player;
import bet.PlayerImpl;

import static database.Database.testDb;

public class PlayerEntry implements Player {
  private static final String PLAYER_TABLE = "player";
  public static final String CREATE_PLAYER_TABLE =
          "CREATE TABLE " + PLAYER_TABLE + "(" +
          "id INTEGER, " +
          "funds INTEGER);";
  public static final String DROP_PLAYER_TABLE =
          "DROP TABLE " + PLAYER_TABLE;
  private Player player;

  public static Player load(int id) {
    int funds = Integer.parseInt(testDb.selectQuery(PLAYER_TABLE, "id=" + id, "funds").get(0));
    return new PlayerEntry(id, funds);
  }

  public PlayerEntry(int id, int initialFunds) {
    player = new PlayerImpl(id, initialFunds);
  }

  public PlayerEntry(int initialFunds) {
    int id = testDb.getMax(PLAYER_TABLE, "id") + 1;
    testDb.updateQuery("INSERT INTO " + PLAYER_TABLE + " VALUES (" + id + "," + initialFunds + ");");
    player = new PlayerImpl(id, initialFunds);
  }

  @Override
  public Bid createBid(int wager, int prediction) {
    Bid bid = new BidEntry(wager, prediction);
    bid.setOwner(player);
    return bid;
  }

  @Override
  public void receiveFunds(int funds) {
    player.receiveFunds(funds);
  }

  @Override
  public int getFunds() {
    return player.getFunds();
  }

  @Override
  public int getId() {
    return player.getId();
  }

  @Override
  public int hashCode() {
    return player.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Player && player.equals(o);
  }

  @Override
  public String toString() {
    return player.toString();
  }
}
