package league;

import friend.User;

public class LeagueRequest extends Request {
  private League league;

  public LeagueRequest(String name, League league) {
    super(name);
    this.league = league;
  }

  @Override
  public void accept(User acceptor) {
    league.addMember(acceptor);
  }
}
