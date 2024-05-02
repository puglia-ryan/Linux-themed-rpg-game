package rpg.model.monsters;

import rpg.model.Item;
import rpg.model.Monster;
import rpg.model.items.Ammo;
import rpg.model.items.Heart;

/** Enemy that represents a computer virus. It can not shoot any projectiles. */
public class EnemyVirus extends Monster {

  /**
   * @param hp hp stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public EnemyVirus(int hp, int atk, int xPos, int yPos, double moveSpeed) {

    super(hp, atk, xPos, yPos, moveSpeed, "/sprites/enemies/virus_enemy/virus_enemy");
  }

  @Override
  public boolean canShoot() {
    return false;
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
