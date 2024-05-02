package rpg.model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/** Represents a projectile in the game and extends the Entity class */
public class Projectile extends Entity {

  private double maxDistance;
  private double distanceTravelled;
  private boolean isPlayerProjectile;

  /**
   * Constructor of the Projectile class
   *
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param map current map
   * @param direction direction
   * @param isPlayerProjectile
   * @param path file path
   */
  public Projectile(
      double xPos,
      double yPos,
      double moveSpeed,
      Map map,
      String direction,
      boolean isPlayerProjectile,
      String path) {

    super(0, 0, xPos, yPos, moveSpeed, path);
    setDirection(direction);
    this.isPlayerProjectile = isPlayerProjectile;
    this.distanceTravelled = 0;
    this.maxDistance = 7;
  }

  /**
   * Moves the projectile on the current map
   *
   * @param map current map
   */
  public void updateProjectilePos(Map map) {
    double newXpos = 0;
    double newYpos = 0;
    switch (getDirection()) {
      case "down":
        newYpos = getMoveSpeed();
        distanceTravelled += Math.abs(newYpos);
        break;
      case "idle":
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
      default:
        break;
    }
    move(newXpos, newYpos, map);
  }

  /**
   * Checks if the projectile can inflict damage after certain distance has been traveled
   *
   * @return true if distance travelled is greater than 2
   */
  public boolean canDamage() {
    return distanceTravelled > 2;
  }

  /**
   * Checks if projectile has expired after a certain distance
   *
   * @return true if distance travelled is greater than the max distance
   */
  public boolean projectileExpired() {
    return distanceTravelled >= maxDistance;
  }

  /**
   * Checks if projectile has been fired by the player character
   *
   * @return true if projectile has been fired by the player character
   */
  public boolean isPlayerProjectileFired() {
    return this.isPlayerProjectile;
  }

  @Override
  public Rectangle getHitbox(int tileSize) {
    double x = this.getXPos() * tileSize + tileSize / 3;
    double y = this.getYPos() * tileSize + tileSize / 3;
    return new Rectangle(x, y, tileSize / 3, tileSize / 3);
  }

  @Override
  public void loadImages(int tileSize) {
    setCurrentImage(new Image(getPath(), tileSize, tileSize, false, false, false));
  }
}
