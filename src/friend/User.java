package friend;

import java.util.ArrayList;
import java.util.List;

public class User {
  List<User> potentialFriends;
  List<User> friends;

  public User() {
    potentialFriends = new ArrayList<User>();
    friends = new ArrayList<User>();
  }

  public void requestFriend(User potentialFriend) {
    potentialFriend.addFriendRequest(this);
  }

  private void addFriendRequest(User potentialFriend) {
    potentialFriends.add(potentialFriend);
  }

  public boolean hasFriendRequest(User potentialFriend) {
    return potentialFriends.contains(potentialFriend);
  }

  public void acceptRequest(User potentialFriend) {
    addFriend(potentialFriend);
    potentialFriend.addFriend(this);
  }

  private void addFriend(User potentialFriend) {
    friends.add(potentialFriend);
  }

  public boolean hasFriend(User friend) {
    return friends.contains(friend);
  }
}
