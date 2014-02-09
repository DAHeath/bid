package person;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.Assert.*;

public class LeagueTests {

  public static final String LEAGUE_NAME = "LEAGUE NAME";
  private League league;
  private Person founder;

  @Before
  public void setUp() {
    founder = new PersonImpl("founder");
    league = new League(LEAGUE_NAME, founder);
  }

  @Test
  public void canMakeLeague() {

  }

  @Test
  public void canInviteUser() {
    Person toInvite = new PersonImpl("to invite");
    league.sendInvite(toInvite);
    toInvite.acceptRequest(LEAGUE_NAME);
    assertTrue(league.hasMember(toInvite));
  }
}
