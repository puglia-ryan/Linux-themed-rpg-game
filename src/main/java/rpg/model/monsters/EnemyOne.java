package rpg.model.monsters;

import javafx.scene.image.Image;
import rpg.model.Item;
import rpg.model.Monster;
import rpg.model.items.Ammo;
import rpg.model.items.Heart;

/** Enemy that represents an evil "one". */
public class EnemyOne extends Monster {

  /**
   * @param hp hp stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   */
  public EnemyOne(int hp, int atk, int xPos, int yPos, double moveSpeed) {

    super(hp, atk, xPos, yPos, moveSpeed, "/sprites/enemies/one_enemy/one_enemy");
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
  public void animate() {
    if (getAnimationCounter() < getAnimationFrameSpeed() / 3)
      setCurrentImage(getSprites().get("sprite1"));
    else if (getAnimationCounter() < getAnimationFrameSpeed() * 2 / 3)
      setCurrentImage(getSprites().get("sprite2"));
    else setCurrentImage(getSprites().get("sprite3"));
  }

  public void loadImages(int tileSize) {
    super.loadImages(tileSize);
    getSprites()
        .put("sprite3", new Image(getPath() + "3.png", tileSize, tileSize, false, false, false));
  }
}
