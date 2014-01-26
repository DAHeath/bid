package database;

import bet.Bet;
import bet.BetImpl;
import bet.Bid;

public class BetEntry implements Bet {
  public static final String TABLE_CREATION_QUERY = "CREATE TABLE bets(overUnder INTEGER(10));";
  public static final String TABLE_DROP_QUERY = "DROP TABLE bets;";
  private Bet bet;

  public BetEntry(float overUnder) {
    bet = new BetImpl(overUnder);
    DatabaseConnection.instance.executeQuery("INSERT INTO bets VALUES (" + overUnder + ");");
  }

  public void acceptBid(Bid bid) {
    bet.acceptBid(bid);
  }

  public void resolve(int result) {
    bet.resolve(result);
  }

  public static void main(String[] args) {
    DatabaseConnection.instance.connect();
    DatabaseConnection.instance.executeQuery(TABLE_DROP_QUERY);
    DatabaseConnection.instance.executeQuery(TABLE_CREATION_QUERY);
    BetEntry entry = new BetEntry(10);
    DatabaseConnection.instance.disconnect();
  }
}
