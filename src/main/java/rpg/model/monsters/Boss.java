package rpg.model.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import rpg.model.Item;
import rpg.model.Monster;
import rpg.model.items.GoldenEgg;

/**
 * The final boss of the game. A sad computer moving and shooting around wildly. Drops the golden
 * egg if beaten.
 */
public class Boss extends Monster {

  private final int scaleUp = 7;
  private long prevMoveTime = 0;

  public Boss(int hp, int atk, int xPos, int yPos, double moveSpeed) {
    super(hp, atk, xPos, yPos, moveSpeed, "/sprites/enemies/boss_enemy/boss");
    setShootCooldown(100);
  }

  @Override
  public Item drop() {
    return new GoldenEgg(this.getXPos(), this.getYPos());
  }

  @Override
  public Rectangle getHitbox(int tileSize) {
    double x = (this.getXPos() - 0.33 * scaleUp) * tileSize;
    double y = this.getYPos() * tileSize;
    return new Rectangle(x, y, tileSize * (scaleUp - 1), tileSize * (scaleUp - 1));
  }

  @Override
  public void draw(GraphicsContext gc, int tileSize) {
    if (this.getCurrentImage() == null) loadImages(tileSize);
    double x = (this.getXPos() - 0.5 * scaleUp) * tileSize;
    double y = (this.getYPos() - 0.5) * tileSize;
    gc.drawImage(this.getCurrentImage(), x, y);
  }

  @Override
  public void loadImages(int tileSize) {
    super.loadImages(tileSize * scaleUp);
  }

  @Override
  public boolean canSwitchDirection() {
    return (System.nanoTime() - prevMoveTime >= 100_000_000);
  }

  @Override
  public void setMoveDirection() {
    double r = Math.random();
    if (r < 0.125) {
      setDirection("down");
    } else if (r < 0.1) {
      setDirection("up");
    } else if (r < 0.25) {
      setDirection("right");
    } else if (r < 0.375) {
      setDirection("left");
    } else if (r < 0.5) {
      setDirection("downRight");
    } else if (r < 0.625) {
      setDirection("downLeft");
    } else if (r < 0.75) {
      setDirection("upRight");
    } else if (r < 0.875) {
      setDirection("upLeft");
    } else {
      setDirection("idle");
    }
  }
}
