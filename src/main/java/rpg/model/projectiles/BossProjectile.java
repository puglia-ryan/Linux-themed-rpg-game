package rpg.model.projectiles;

import javafx.scene.shape.Rectangle;
import rpg.model.Map;
import rpg.model.Projectile;

/** Projectile shot by the boss. */
public class BossProjectile extends Projectile {
  private double maxDistance;
  private double distanceTravelled;

  /**
   * Constructor of the BossProjectile class
   *
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param map current map
   * @param direction direction
   * @param isPlayerProjectile
   * @param sprite file path
   */
  public BossProjectile(
      double xPos,
      double yPos,
      double moveSpeed,
      Map map,
      String direction,
      boolean isPlayerProjectile,
      String sprite) {
    super(xPos, yPos, moveSpeed, map, direction, isPlayerProjectile, sprite);
    this.maxDistance = 7;
    this.distanceTravelled = 0;
  }

  @Override
  public void updateProjectilePos(Map map) {
    double newXpos = 0;
    double newYpos = 0;

    // Handle diagonal movement
    switch (getDirection()) {
      case "down":
        newYpos = getMoveSpeed();
        distanceTravelled += Math.abs(newYpos);
        break;
      case "up":
        newYpos = -getMoveSpeed();
        distanceTravelled += Math.abs(newYpos);
        break;
      case "right":
        newXpos = getMoveSpeed();
        distanceTravelled += Math.abs(newXpos);
        break;
      case "left":
        newXpos = -getMoveSpeed();
        distanceTravelled += Math.abs(newXpos);
        break;
      case "downRight":
        newXpos = getMoveSpeed();
        newYpos = getMoveSpeed();
        distanceTravelled += Math.sqrt(newXpos * newXpos + newYpos * newYpos);
        break;
      case "downLeft":
        newXpos = -getMoveSpeed();
        newYpos = getMoveSpeed();
        distanceTravelled += Math.sqrt(newXpos * newXpos + newYpos * newYpos);
        break;
      case "upRight":
        newXpos = getMoveSpeed();
        newYpos = -getMoveSpeed();
        distanceTravelled += Math.sqrt(newXpos * newXpos + newYpos * newYpos);
        break;
      case "upLeft":
        newXpos = -getMoveSpeed();
        newYpos = -getMoveSpeed();
        distanceTravelled += Math.sqrt(newXpos * newXpos + newYpos * newYpos);
        break;
      default:
        newYpos = getMoveSpeed();
        distanceTravelled += Math.abs(newYpos);
    }

    move(newXpos, newYpos, map);
  }

  @Override
  public Rectangle getHitbox(int tileSize) {
    double x = this.getXPos() * tileSize;
    double y = this.getYPos() * tileSize;
    return new Rectangle(x, y, tileSize, tileSize);
  }

  @Override
  public boolean canDamage() {
    return distanceTravelled > 0.1;
  }

  @Override
  public boolean projectileExpired() {
    return distanceTravelled >= maxDistance;
  }
}
