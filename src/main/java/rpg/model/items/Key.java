package rpg.model.items;

import rpg.model.Item;
import rpg.model.Player;

/** Keys that the player can collect to open a lock. */
public class Key extends Item {

  /**
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public Key(double xPos, double yPos) {
    super(xPos, yPos, "sprites/items/key.png");
  }

  @Override
  public void interact(Player player) {
    player.setKeyCount(1);
  }
}
