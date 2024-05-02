package rpg.model.monsters;

import rpg.model.Item;
import rpg.model.Monster;
import rpg.model.items.Ammo;
import rpg.model.items.Heart;

/** Enemy that represents an evil "zero". */
public class EnemyZero extends Monster {

  /**
   * @param hp hp stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public EnemyZero(int hp, int atk, int xPos, int yPos, double moveSpeed) {

    super(hp, atk, xPos, yPos, moveSpeed, "/sprites/enemies/zero_enemy/zero_enemy");
  }

  @Override
  public Item drop() {
    double r = Math.random();
    if (r < 0.3) {
      return new Heart(getXPos(), getYPos());
    } else if (r < 0.6) {
      return new Ammo(getXPos(), getYPos());
    } else {
      return null;
    }
  }
}
