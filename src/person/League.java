package person;

import java.util.ArrayList;
import java.util.List;

public class League {
  private final String name;
  private List<Person> members;

  public League(String name, Person founder) {
    this.name = name;
    members = new ArrayList<Person>();
    members.add(founder);
  }

  public void sendInvite(Person toInvite) {
    toInvite.addRequest(new LeagueRequest(name, this));
  }

  public boolean hasMember(Person toInvite) {
    return members.contains(toInvite);
  }

  public void addMember(Person invitee) {
    members.add(invitee);
  }
}
