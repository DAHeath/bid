package bet;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class BetTests {
  private static final float OVER_UNDER = 40.5f;
  private static final int UNDER = 30;
  private static final int OVER = 50;
  private static final int WAGER = 10;

  private BetImpl bet;
  private BidImpl bid;
  private Player winner;
  private Player loser;

  @Before
  public void setUp() {
    winner = new Player(WAGER);
    loser = new Player(WAGER);
    bet = new BetImpl(OVER_UNDER);
  }

  @Test
  public void winnerShouldHaveIncreasedFunds() {
    bid = winner.createBid(WAGER, OVER);
    bet.acceptBid(bid);
    bid = loser.createBid(WAGER, UNDER);
    bet.acceptBid(bid);
    bet.resolve(OVER);

    assertEquals(2 * WAGER, winner.getFunds());
    assertEquals(0, loser.getFunds());
  }

  @Test (expected = InsufficientFundsException.class)
  public void playerCannotCreateBidOverFunds() {
    winner.createBid(11, OVER);
  }
}
