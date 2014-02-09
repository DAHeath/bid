package person;

import database.Identifiable;

public interface Person extends Identifiable {
  public void addFriend(Person person);
  public void requestFriend(Person person);
  public boolean hasFriend(Person person);

  public void addRequest(Request request);
  public void acceptRequest(String requestName);
  public boolean hasRequest(String requestName);
}
