package bet;

import database.Identifiable;

public interface Bid extends Identifiable {
  public void setOwner(Player player);
  public void resolve(float overUnder, int result);
}
