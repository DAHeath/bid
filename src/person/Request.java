package person;

public abstract class Request {
  private final String name;

  public Request(String name) {
    this.name = name;
  }

  public abstract void accept(Person acceptor);

  public String getName() {
    return name;
  }
}
