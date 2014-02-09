package person;

public class LeagueRequest extends Request {
  private League league;

  public LeagueRequest(String name, League league) {
    super(name);
    this.league = league;
  }

  @Override
  public void accept(Person acceptor) {
    league.addMember(acceptor);
  }
}
