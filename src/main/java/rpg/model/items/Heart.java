package rpg.model.items;

import rpg.model.Item;
import rpg.model.Player;

/** Item that heals the player. */
public class Heart extends Item {

  private static final int amount = 30;

  /**
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public Heart(double xPos, double yPos) {
    super(xPos, yPos, "sprites/items/heart.png");
  }

  @Override
  public void interact(Player player) {
    player.setHp((player.getHp() + amount));
    if (player.getHp() > player.getMaxHp()) {
      player.setHp(player.getMaxHp());
    }
  }
}
