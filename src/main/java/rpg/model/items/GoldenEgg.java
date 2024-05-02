package rpg.model.items;

import rpg.model.Item;
import rpg.model.Player;

/** Item that the player has to collect in order to beat the game. */
public class GoldenEgg extends Item {

  /**
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public GoldenEgg(double xPos, double yPos) {
    super(xPos, yPos, "sprites/items/golden_egg.png");
  }

  @Override
  public void interact(Player player) {
    player.setEggStatus();
    ;
  }
}
