package person;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
  private List<Person> friends;
  private List<Request> requests;
  private final int id;
  private String name;

  public PersonImpl(int id, String name) {
    this.id = id;
    this.name = name;
    friends = new ArrayList<Person>();
    requests = new ArrayList<Request>();
  }

  public PersonImpl(String name) {
    this(-1, name);
  }

  public void addFriend(Person potentialFriend) {
    friends.add(potentialFriend);
  }

  public void requestFriend(Person potentialFriend) {
    potentialFriend.addRequest(new FriendRequest(name, this));
  }

  public boolean hasFriend(Person friend) {
    return friends.contains(friend);
  }

  public void addRequest(Request request) {
    requests.add(request);
  }

  private Request getRequest(String requestName) {
    for (Request request: requests)
      if (request.getName().equals(requestName))
        return request;
    return null;
  }

  public boolean hasRequest(String requestName) {
    return getRequest(requestName) != null;
  }

  public void acceptRequest(String requestName) {
    getRequest(requestName).accept(this);
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public int hashCode() {
    return id + 31*(name.hashCode() + 31*(friends.hashCode() + 31*(requests.hashCode())));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Person))
      return false;
    Person p = (Person)o;
    return p.hashCode() == hashCode();
  }

  @Override
  public String toString() {
    return "id: " + id +
            ", name: " + name +
            ", friends: " + friends.toString() +
            ", requests: " + requests.toString();
  }
}
