package betdatabase;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;
import database.Row;
import database.Table;

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

  public static Bet load(int id) {
    Row row = table.selectWhere("id", id).get(0);
    Bet bet = loadBet(id, row);
    loadBids(id, bet);
    return bet;
  }

  private static Bet loadBet(int id, Row row) {
    float overUnder = row.floatAt("over_under");
    return new BetEntry(id, overUnder);
  }

  private static void loadBids(int id, Bet bet) {
    for(Row r: betBids.selectWhere("bet_id", id))
      bet.acceptBid(BidEntry.load(r.intAt("bid_id")));
  }

  private BetEntry(int id, float overUnder) {
    bet = new BetImpl(id, overUnder);
  }

  public BetEntry(float overUnder) {
    this(table.getMax("id") + 1, overUnder);
    save(overUnder);
  }

  private void save(float overUnder) {
    Row row = new Row();
    row.addValue("id", bet.getId());
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
    row.addValue("bet_id", bet.getId());
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