package betdatabase;

import bet.Bid;
import bet.Player;
import bet.PlayerImpl;
import database.Row;
import database.Table;

import static database.Database.testDb;

public class PlayerEntry implements Player {
  public static Table table =  new Table(testDb, "player");
  static {
    table.addAttribute(Table.Type.INTEGER, "id");
    table.addAttribute(Table.Type.INTEGER, "funds");
    table.setPrimaryKey("id");
  }

  private Player player;

  public static Player load(int id) {
    Row row = table.selectWhere("id", id).get(0);
    int funds = row.intAt("funds");
    return new PlayerEntry(id, funds);
  }

  private PlayerEntry(int id, int initialFunds) {
    player = new PlayerImpl(id, initialFunds);
  }

  public PlayerEntry(int initialFunds) {
    this(table.getMax("id") + 1, initialFunds);
    save();
  }

  private void save() {
    Row row = new Row();
    row.addValue("id", player.getId());
    row.addValue("funds", player.getFunds());
    table.insertRow(row);
  }

  @Override
  public Bid createBid(int wager, int prediction) {
    Bid bid = new BidEntry(wager, prediction);
    bid.setOwner(player);
    player.receiveFunds(-wager);
    updateFunds();
    return bid;
  }

  @Override
  public void receiveFunds(int funds) {
    player.receiveFunds(funds);
    updateFunds();
  }

  private void updateFunds() {
    Row row = new Row();
    row.addValue("funds", player.getFunds());
    table.updateRow(row, "id", player.getId());
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
