package rpg.model.monsters;

import javafx.scene.image.Image;
import rpg.model.Item;
import rpg.model.Monster;
import rpg.model.items.Ammo;
import rpg.model.items.Heart;

/** Enemy that does not move but has a low shoot cooldown. */
public class TurretEnemy extends Monster {

  /**
   * @param hp hp stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public TurretEnemy(int hp, int atk, int xPos, int yPos) {
    super(hp, atk, xPos, yPos, 0, "sprites/enemies/turret/turret");
    setShootCooldown(800);
  }

  @Override
  public void setMoveDirection() {
    double r = Math.random();
    if (r < 0.25) {
      setDirection("down");
      setCurrentImage(getSprites().get("back"));
    } else if (r < 0.5) {
      setDirection("up");
      setCurrentImage(getSprites().get("front"));
    } else if (r < 0.75) {
      setDirection("right");
      setCurrentImage(getSprites().get("right"));
    } else if (r < 0.99) {
      setDirection("left");
      setCurrentImage(getSprites().get("left"));
    } else {
      setDirection("idle");
    }
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

  @Override
  public void animate() {}

  public void loadImages(int tileSize) {
    getSprites()
        .put("back", new Image(getPath() + "_back.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put("front", new Image(getPath() + "_front.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put("left", new Image(getPath() + "_left.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put("right", new Image(getPath() + "_right.png", tileSize, tileSize, false, false, false));
    setCurrentImage(getSprites().get("front"));
  }
}
