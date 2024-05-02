package rpg.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/** Represents a lock that can not be passed if the player has not the right amount of keys. */
public class Lock {

  private boolean locked;
  private boolean updated;
  private int lockCount;
  private int xPos, yPos;
  private String path;

  /**
   * @param lockcount amount of keys to open the lock
   * @param xPos x coordinate of the lock
   * @param yPos y coordinate of the lock
   */
  public Lock(int lockcount, int xPos, int yPos) {
    this.locked = true;
    this.updated = false;
    this.lockCount = lockcount;
    this.xPos = xPos;
    this.yPos = yPos;
    this.path = "sprites/tiles/lock.png";
  }

  /**
   * Returns the current state of the lock.
   *
   * @return true if it is locked
   */
  public boolean isLocked() {
    return this.locked;
  }

  /** Unlock by setting the locked attribute to true. */
  public void unlock() {
    this.locked = false;
  }

  /**
   * Helper method for the controller. Checks if
   *
   * @return
   */
  public boolean isUpdated() {
    return this.updated;
  }

  /** Helper method for the controller. */
  public void setUpdated() {
    this.updated = !updated;
  }

  /**
   * Get the amount keys that is required to open the lock.
   *
   * @return lock count
   */
  public int getLockCount() {
    return this.lockCount;
  }

  /**
   * Returns file path of the image
   *
   * @return file path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Returns x coordinate of the lock
   *
   * @return x coordinate
   */
  public int getXPos() {
    return this.xPos;
  }

  /**
   * Returns y coordinate of the lock
   *
   * @return y coordinate
   */
  public int getYPos() {
    return this.yPos;
  }

  /**
   * Returns the collision box of the lock
   *
   * @param tileSize size of a tile
   * @return Rectangle that represents the collision box
   */
  public Rectangle getHitbox(int tileSize) {
    double x = xPos * tileSize;
    double y = yPos * tileSize;
    return new Rectangle(x, y, tileSize, tileSize);
  }

  /**
   * Draws the lock as an image on a canvas, if the lock is still unlocked
   *
   * @param gc graphics context
   * @param tileSize size of atile
   */
  public void draw(GraphicsContext gc, int tileSize) {
    if (!locked) {
      return;
    }
    double x = xPos * tileSize;
    double y = yPos * tileSize;
    gc.drawImage(getImage(tileSize), x, y);
  }

  /**
   * Returns an Image object of the lock. The size of the image is based on the tile size.
   *
   * @param tileSize size of a tile
   * @return Image of the lock
   */
  public Image getImage(int tileSize) {
    return new Image(this.path, tileSize, tileSize, false, false, false);
  }
}
