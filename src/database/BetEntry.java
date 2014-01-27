package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;

import static database.Database.testDb;

public class BetEntry implements Bet {
  public static Table table = new Table(testDb, "bet");
  static {
    table.addAttribute(Table.Type.INTEGER, "id");
    table.addAttribute(Table.Type.FLOAT, "over_under");
    table.setPrimaryKey("id");
  }
  public static Table betBids = new Table(testDb, "betBid");
  static {
    betBids.addAttribute(Table.Type.INTEGER, "bet_id");
    betBids.addAttribute(Table.Type.INTEGER, "bid_id");
  }

  private Bet bet;
  private final int id;
  private final float overUnder;

  public static Bet load(int id) {
    Row row = table.selectWhere("id", id).get(0);
    float overUnder = row.floatAt("over_under");
    Bet bet = new BetEntry(id, overUnder);
    for(Row r: betBids.selectWhere("bet_id", id))
      bet.acceptBid(BidEntry.load(r.intAt("bid_id")));
    return bet;
  }

  private BetEntry(int id, float overUnder) {
    this.id = id;
    this.overUnder = overUnder;
    bet = new BetImpl(id, overUnder);
  }

  public BetEntry(float overUnder) {
    this(table.getMax("id") + 1, overUnder);
    save();
  }

  private void save() {
    Row row = new Row();
    row.addValue("id", id);
    row.addValue("over_under", overUnder);
    table.insertRow(row);
  }

  @Override
  public int getId() {
    return bet.getId();
  }

  @Override
  public void acceptBid(Bid bid) {
    Row row = new Row();
    row.addValue("bet_id", id);
    row.addValue("bid_id", bid.getId());
    betBids.insertRow(row);
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