package person;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.Assert.*;

public class FriendTests {
  private Person sender;
  private Person receiver;

  @Before
  public void setUp() {
    sender = new PersonImpl("sender");
    receiver = new PersonImpl("receiver");
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
