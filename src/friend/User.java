package friend;

import league.Request;

import java.util.ArrayList;
import java.util.List;

public class User {
  private List<User> friends;
  private List<Request> requests;
  private String name;

  public User(String name) {
    this.name = name;
    friends = new ArrayList<User>();
    requests = new ArrayList<Request>();
  }

  public void requestFriend(User potentialFriend) {
    potentialFriend.addRequest(new FriendRequest(name, this));
  }

  public void addFriend(User potentialFriend) {
    friends.add(potentialFriend);
  }

  public boolean hasFriend(User friend) {
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
}
