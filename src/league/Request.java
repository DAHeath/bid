package league;

import friend.User;

public abstract class Request {
  private final String name;

  public Request(String name) {
    this.name = name;
  }

  public abstract void accept(User acceptor);

  public String getName() {
    return name;
  }
}
