package friend;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.Assert.*;

public class FriendTests {
  private User u;
  private User friend;

  @Before
  public void setUp() {
    u = new User();
    friend = new User();
  }

  @Test
  public void canRequestFriend() {
    u.requestFriend(friend);
    assertTrue(friend.hasFriendRequest(u));
  }

  @Test
  public void canAcceptRequest() {
    u.requestFriend(friend);
    friend.acceptRequest(u);
    assertTrue(u.hasFriend(friend));
    assertTrue(friend.hasFriend(u));
  }
}
