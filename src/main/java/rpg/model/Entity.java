package rpg.model;

import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import rpg.controller.Controller;

/** Represents an entity. */
public abstract class Entity {
  private String path;
  private int hp, atk, maxHp;
  private double xPos, yPos, moveSpeed;
  private String direction;
  private long lastProjectileFired;
  private long prevHitTime;
  private long shootCoolDown;

  private Image currentImage;
  private HashMap<String, Image> sprites;
  private int animationCounter;
  private int idleCounter;

  private final long hitCoolDown = 500000000;
  private final int animationFrameSpeed = 30;

  /**
   * Constructor for the entity
   *
   * @param hp hitpoint stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param path file path of the image
   */
  public Entity(int hp, int atk, double xPos, double yPos, double moveSpeed, String path) {
    this.hp = hp;
    this.atk = atk;
    this.xPos = xPos;
    this.yPos = yPos;
    this.moveSpeed = moveSpeed;
    this.path = path;
    this.sprites = new HashMap<String, Image>();
    this.direction = "down";
    this.lastProjectileFired = 0;
    this.prevHitTime = 0;
    this.maxHp = hp;

    this.shootCoolDown = 500 * 1_000_000;
    this.animationCounter = 0;
    this.idleCounter = 0;
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
   * Returns the current hp value
   *
   * @return hp value
   */
  public int getHp() {
    return this.hp;
  }

  /**
   * Returns the maximum amount of hp
   *
   * @return max hp
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * Changes the current amount of hp
   *
   * @param newHp new hp value
   */
  public void setHp(int newHp) {
    this.hp = newHp;
  }

  /**
   * Decrease current hp value if entity can be hit
   *
   * @param dmgAmount amount of damage
   */
  public void loseHp(int dmgAmount) {
    if (canTakeDamage()) {
      setHp(getHp() - dmgAmount);
      prevHitTime = System.nanoTime();
    }
  }

  /**
   * Checks if entity can take damage based on the hit cool down
   *
   * @return true if entity can take damage
   */
  public boolean canTakeDamage() {
    return (System.nanoTime() - prevHitTime >= hitCoolDown);
  }

  /**
   * Returns the attack stat
   *
   * @return attack stat
   */
  public int getAtk() {
    return this.atk;
  }

  /**
   * Sets a new attack stat
   *
   * @param newAtk new attack stat
   */
  public void setAtk(int newAtk) {
    this.atk = newAtk;
  }

  /**
   * Returns x coordinate of the entity
   *
   * @return x coordinate
   */
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Returns y coordinate of the entity
   *
   * @return y coordinate
   */
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Returns the move speed of the entity
   *
   * @return
   */
  public double getMoveSpeed() {
    return this.moveSpeed;
  }

  /**
   * Sets new coordinates for the entity
   *
   * @param newX new x coordinate
   * @param newY new y coordinate
   */
  public void setPos(double newX, double newY) {
    this.xPos = newX;
    this.yPos = newY;
  }

  /**
   * Moves the entity up. Changes the sprite based on the movement direction.
   *
   * @param map current map
   */
  public void moveUp(Map map) {
    if (animationCounter < animationFrameSpeed / 2) setCurrentImage(getSprites().get("front1"));
    else if (animationCounter >= animationFrameSpeed / 2)
      setCurrentImage(getSprites().get("front2"));
    move(0, -moveSpeed, map);
  }

  /**
   * Moves the entity down. Changes the sprite based on the movement direction.
   *
   * @param map current map
   */
  public void moveDown(Map map) {
    if (animationCounter < animationFrameSpeed / 2) setCurrentImage(getSprites().get("back1"));
    else if (animationCounter >= animationFrameSpeed / 2)
      setCurrentImage(getSprites().get("back2"));
    move(0, moveSpeed, map);
  }

  /**
   * Moves the entity left. Changes the sprite based on the movement direction.
   *
   * @param map current map
   */
  public void moveLeft(Map map) {
    if (animationCounter < animationFrameSpeed / 2) setCurrentImage(getSprites().get("left1"));
    else if (animationCounter >= animationFrameSpeed / 2)
      setCurrentImage(getSprites().get("left2"));
    move(-moveSpeed, 0, map);
  }

  /**
   * Moves the entity right. Changes the sprite based on the movement direction.
   *
   * @param map current map
   */
  public void moveRight(Map map) {
    if (animationCounter < animationFrameSpeed / 2) setCurrentImage(getSprites().get("right1"));
    else if (animationCounter >= animationFrameSpeed / 2)
      setCurrentImage(getSprites().get("right2"));
    move(moveSpeed, 0, map);
  }

  /** Changes the sprite when not moving anywhere */
  public void dontMove() {
    // setDirection("front");
    if (animationCounter < animationFrameSpeed / 2) setCurrentImage(getSprites().get("idle1"));
    else if (animationCounter >= animationFrameSpeed / 2)
      setCurrentImage(getSprites().get("idle2"));
  }

  /**
   * Returns the amount of frames the entity has not moved
   *
   * @return
   */
  public int getIdleCounter() {
    return idleCounter;
  }

  /** Increments the idle counter */
  public void incrementIdleCounter() {
    idleCounter++;
  }

  /** Resets the idle counter */
  public void resetIdleCounter() {
    idleCounter = 0;
  }

  /**
   * Moves the entity after the collision check
   *
   * @param xMove moves along the x axis
   * @param yMove moves along the y axis
   * @param map current map
   */
  public void move(double xMove, double yMove, Map map) {
    double newX = xPos + xMove;
    double newY = yPos + yMove;
    if (checkCollision(newX, newY, map)) {
      xPos = newX;
      yPos = newY;
    }
  }

  /**
   * Returns the direction the entity is facing
   *
   * @return
   */
  public String getDirection() {
    return this.direction;
  }

  /**
   * Sets new direction
   *
   * @param newDirection new direction
   */
  public void setDirection(String newDirection) {
    this.direction = newDirection;
  }

  /**
   * Checks if entity is not colliding with any map elements. If it returns false, the coordinates
   * of the moving entity won't change.
   *
   * @param targetX new x coordinate
   * @param targetY new y coordinate
   * @param map current map
   * @return true if there is any collision
   */
  public boolean checkCollision(double targetX, double targetY, Map map) {
    try {
      if (!map.getMap()[(int) Math.round(targetY)][(int) Math.round(targetX)].isTraversable()) {
        return false;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      return true;
    }
    if ((Controller.getMapIndexX() == MapLayout.rowLockPos)
        && (Controller.getMapIndexY() == MapLayout.colLockPos)) {
      if (((int) (Math.round(targetX)) == map.getLock().getXPos())
          && (int) (Math.round(targetY)) == map.getLock().getYPos()
          && map.getLock().isLocked()) {
        return false;
      }
    }
    if ((targetX + moveSpeed < 0
        || targetX + moveSpeed >= map.getSize()
        || targetY + moveSpeed < 0
        || targetY + moveSpeed >= map.getSize())) {
      return false;
    }
    return true;
  }

  /**
   * Returns the collision box of the entity
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
   * Checks if entity is colliding with another entity.
   *
   * @param enemy other entity
   * @param tileSize tile size
   * @return true if there is any collision
   */
  public boolean checkEnemyCollision(Entity enemy, int tileSize) {
    Rectangle enemyHitbox = enemy.getHitbox(tileSize / 2);
    Rectangle playerHitbox = getHitbox(tileSize / 2);
    return playerHitbox.intersects(enemyHitbox.getBoundsInLocal());
  }

  /**
   * Draws the entity as an image on a canvas
   *
   * @param gc graphics context
   * @param tileSize size of a tile
   */
  public void draw(GraphicsContext gc, int tileSize) {
    if (currentImage == null) loadImages(tileSize);
    double x = xPos * tileSize;
    double y = yPos * tileSize;
    gc.drawImage(currentImage, x, y);
  }

  /**
   * Sets the current sprite of the entity
   *
   * @param newImage
   */
  public void setCurrentImage(Image newImage) {
    this.currentImage = newImage;
  }

  /** Increments the animation counter */
  public void incrementCounter() {
    if (animationCounter == animationFrameSpeed) animationCounter = 0;
    animationCounter++;
  }

  /**
   * Returns the value of the animatioun counter
   *
   * @return animation counter
   */
  public int getAnimationCounter() {
    return animationCounter;
  }

  /**
   * Returns the animation frame speed
   *
   * @return animation frame speed
   */
  public int getAnimationFrameSpeed() {
    return animationFrameSpeed;
  }

  /**
   * Get current sprite
   *
   * @return current image
   */
  public Image getCurrentImage() {
    return currentImage;
  }

  /**
   * Loads all possible sprites in to the hashmap. Images are scaled to the size of a tile
   *
   * @param tileSize size of a tile
   */
  public abstract void loadImages(int tileSize);

  /**
   * Returns the hashmap that contains all the sprites
   *
   * @return
   */
  public HashMap<String, Image> getSprites() {
    return sprites;
  }

  /**
   * Sets the time after the last projectile has been shot
   *
   * @param time time in nanoseconds
   */
  public void setLastProjectileFired(long time) {
    lastProjectileFired = time;
  }

  /**
   * Returns the time after the last projectile has been shot
   *
   * @return time in nanoseconds
   */
  public long getLastProjectileFired() {
    return lastProjectileFired;
  }

  /**
   * Returns the cooldown time between each projectile shot
   *
   * @return cooldown in nanoseconds
   */
  public long getShootCooldown() {
    return this.shootCoolDown;
  }

  /**
   * Set the cooldown after each shot
   *
   * @param cooldown in milliseconds
   */
  public void setShootCooldown(int cooldown) {
    this.shootCoolDown = 1_000_000 * cooldown;
  }

  /**
   * Checks if entity can shoot a projectile
   *
   * @return true if entity can shoot
   */
  public boolean canShoot() {
    return (System.nanoTime() - getLastProjectileFired() >= shootCoolDown);
  }
}
