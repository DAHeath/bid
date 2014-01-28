package friend;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.Assert.*;

public class FriendTests {
  private User sender;
  private User receiver;

  @Before
  public void setUp() {
    sender = new User("sender");
    receiver = new User("receiver");
  }

  @Test
  public void canRequestFriend() {
    sender.requestFriend(receiver);
    assertTrue(receiver.hasRequest("sender"));
  }

  @Test
  public void canAcceptRequest() {
    sender.requestFriend(receiver);
    receiver.acceptRequest("sender");
    assertTrue(sender.hasFriend(receiver));
    assertTrue(receiver.hasFriend(sender));
  }
}
