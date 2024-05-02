package rpg.model.items;

import rpg.model.Item;
import rpg.model.Player;

/** Projectile shot by the player */
public class Ammo extends Item {

  private static final int amount = 5;

  /**
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public Ammo(double xPos, double yPos) {
    super(xPos, yPos, "sprites/items/bullets.png");
  }

  @Override
  public void interact(Player player) {
    player.setAmmo(amount);
  }
}
