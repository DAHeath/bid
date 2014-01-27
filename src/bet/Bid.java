package bet;

public interface Bid {
  public int getId();
  public void setOwner(Player player);
  public void resolve(float overUnder, int result);
}
