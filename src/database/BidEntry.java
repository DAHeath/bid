package database;

import bet.Bid;
import bet.BidImpl;
import bet.Player;

import static database.Database.testDb;

public class BidEntry implements Bid {
  public static Table table = new Table(testDb, "bid");
  static {
    table.addAttribute(Table.Type.INTEGER, "id");
    table.addAttribute(Table.Type.INTEGER, "wager");
    table.addAttribute(Table.Type.INTEGER, "prediction");
    table.addAttribute(Table.Type.INTEGER, "player_id");
    table.setPrimaryKey("id");
  }

  private Bid bid;
  private final int id;
  private final int wager;
  private final int prediction;
  private int playerId;

  public static Bid load(int id) {
    Row row = table.selectWhere("id", id).get(0);
    int wager = row.intAt("wager");
    int prediction = row.intAt("prediction");
    BidEntry bid = new BidEntry(id, wager, prediction);
    loadOwner(row.intAt("player_id"), bid);
    return bid;
  }

  private static void loadOwner(int playerId, BidEntry bid) {
    if (playerId != -1)
      bid.setOwner(PlayerEntry.load(playerId));
  }

  private BidEntry(int id, int wager, int prediction) {
    this.id = id;
    this.wager = wager;
    this.prediction = prediction;
    this.playerId = -1;
    bid = new BidImpl(id, wager, prediction);
  }

  public BidEntry(int wager, int prediction) {
    this(table.getMax("id") + 1, wager, prediction);
    save();
  }

  private void save() {
    Row row = new Row();
    row.addValue("id", id);
    row.addValue("wager", wager);
    row.addValue("prediction", prediction);
    row.addValue("player_id", playerId);
    table.insertRow(row);
  }

  @Override
  public int getId() {
    return bid.getId();
  }

  @Override
  public void setOwner(Player player) {
    this.playerId = player.getId();
    bid.setOwner(player);
    updateOwner();
  }

  private void updateOwner() {
    Row row = new Row();
    row.addValue("player_id", playerId);
    table.updateRow(row, "id", id);
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