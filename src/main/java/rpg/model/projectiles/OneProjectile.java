package rpg.model.projectiles;

import rpg.model.Map;
import rpg.model.Projectile;

/** Projectile shot by the EnemyOne */
public class OneProjectile extends Projectile {

  public OneProjectile(
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
        "sprites/projectiles/one_projectile.png");
  }
}
