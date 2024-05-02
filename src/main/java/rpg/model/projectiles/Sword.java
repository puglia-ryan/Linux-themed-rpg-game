package rpg.model.projectiles;

import java.util.HashMap;
import javafx.scene.image.Image;
import rpg.model.Map;
import rpg.model.Projectile;

/** Represents a sword in the game and extends the projectile class */
public class Sword extends Projectile {

  private double maxDistance;
  private double distanceTravelled;
  private boolean isPlayerProjectile;
  private final String path = "sprites/swords/";
  private HashMap<String, Image> sprites;
  private Image currentImage;

  /**
   * Constructor for the sword class
   *
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param map current map
   * @param direction direction that the sword is facing
   * @param isPlayerProjectile checks if sword is shot by the player
   */
  public Sword(
      double xPos,
      double yPos,
      double moveSpeed,
      Map map,
      String direction,
      boolean isPlayerProjectile) {
    super(xPos, yPos, moveSpeed, map, direction, true, "sprites/swords/sword_up.png");
    this.sprites = new HashMap<String, Image>();
    loadImages(map.getTileSize() / 2);
    this.isPlayerProjectile = isPlayerProjectile;
    this.distanceTravelled = 0;
    this.maxDistance = 1;
    setCurrentImage(sprites.get(getDirection()));
  }

  @Override
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

  @Override
  public boolean canDamage() {
    return distanceTravelled > 0.1;
  }

  @Override
  public boolean projectileExpired() {
    return distanceTravelled >= maxDistance;
  }

  @Override
  public void loadImages(int tileSize) {
    this.sprites.put(
        "down", new Image(this.path + "sword_down.png", tileSize, tileSize, false, false, false));
    this.sprites.put(
        "idle", new Image(this.path + "sword_down.png", tileSize, tileSize, false, false, false));
    this.sprites.put(
        "up", new Image(this.path + "sword_up.png", tileSize, tileSize, false, false, false));
    this.sprites.put(
        "left", new Image(this.path + "sword_left.png", tileSize, tileSize, false, false, false));
    this.sprites.put(
        "right", new Image(this.path + "sword_right.png", tileSize, tileSize, false, false, false));
    currentImage = sprites.get(getDirection());
  }

  @Override
  public boolean isPlayerProjectileFired() {
    return this.isPlayerProjectile;
  }
}
