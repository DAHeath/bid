package league;

import friend.User;
import org.junit.Test;
import org.junit.Before;

import static junit.framework.Assert.*;

public class LeagueTests {

  public static final String LEAGUE_NAME = "LEAGUE NAME";
  private League league;
  private User founder;

  @Before
  public void setUp() {
    founder = new User("founder");
    league = new League(LEAGUE_NAME, founder);
  }

  @Test
  public void canMakeLeague() {

  }

  @Test
  public void canInviteUser() {
    User toInvite = new User("to invite");
    league.sendInvite(toInvite);
    toInvite.acceptRequest(LEAGUE_NAME);
    assertTrue(league.hasMember(toInvite));
  }
}
