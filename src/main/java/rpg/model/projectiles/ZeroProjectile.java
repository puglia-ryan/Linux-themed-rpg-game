package rpg.model.projectiles;

import rpg.model.Map;
import rpg.model.Projectile;

/** Projectile shot by the EnemyZero */
public class ZeroProjectile extends Projectile {

  public ZeroProjectile(
      double xPos,
      double yPos,
      double moveSpeed,
      Map map,
      String direction,
      boolean isPlayerProjectile) {
    super(
        xPos,
        yPos,
        moveSpeed,
        map,
        direction,
        isPlayerProjectile,
        "sprites/projectiles/zero_projectile.png");
  }
}
