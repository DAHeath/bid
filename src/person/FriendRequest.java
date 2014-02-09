package person;

public class FriendRequest extends Request {
  private final Person sender;

  public FriendRequest(String name, Person sender) {
    super(name);
    this.sender = sender;
  }

  @Override
  public void accept(Person acceptor) {
    acceptor.addFriend(sender);
    sender.addFriend(acceptor);
  }
}
