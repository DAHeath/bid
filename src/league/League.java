package league;

import friend.User;

import java.util.ArrayList;
import java.util.List;

public class League {
  private final String name;
  private List<User> members;

  public League(String name, User founder) {
    this.name = name;
    members = new ArrayList<User>();
    members.add(founder);
  }

  public void sendInvite(User toInvite) {
    toInvite.addRequest(new LeagueRequest(name, this));
  }

  public boolean hasMember(User toInvite) {
    return members.contains(toInvite);
  }

  public void addMember(User invitee) {
    members.add(invitee);
  }
}
