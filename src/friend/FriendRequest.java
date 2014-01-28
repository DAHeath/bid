package friend;

import league.Request;

public class FriendRequest extends Request {
  private final User sender;

  public FriendRequest(String name, User sender) {
    super(name);
    this.sender = sender;
  }

  @Override
  public void accept(User acceptor) {
    acceptor.addFriend(sender);
    sender.addFriend(acceptor);
  }
}
