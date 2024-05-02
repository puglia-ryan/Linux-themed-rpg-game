package rpg.model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/** Class that represents the player character and extends the entity class */
public class Player extends Entity {

  private int maxHp;
  private int level;
  private int exp;
  private int expCapacity;
  private int projectileAmmo;
  private int keyCount;
  private boolean hasEgg;
  private boolean enteredMatrix;

  /**
   * Constructor for the player class
   *
   * @param hp hp stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param level level of the player
   */
  public Player(int hp, int atk, int xPos, int yPos, double moveSpeed, int level) {

    super(hp, atk, xPos, yPos, moveSpeed, "sprites/player/");
    this.maxHp = hp;
    this.level = (level > 0) ? level : 1;
    this.exp = 0;
    this.expCapacity = 50;
    this.projectileAmmo = 10;
    this.keyCount = 0;
    this.hasEgg = false;
    this.enteredMatrix = false;
  }

  /** Sets the enteredMatrix attribute to true */
  public void enteredMatrix() {
    enteredMatrix = true;
  }

  /**
   * Checks if player has entered a matrix tile
   *
   * @return true if player touches a matrix tile
   */
  public boolean getEnteredMatrix() {
    return enteredMatrix;
  }

  /**
   * Returns max hp stat
   *
   * @return max hp stat
   */
  public int getMaxHp() {
    return this.maxHp;
  }

  /**
   * Returns the current amount of experience points
   *
   * @return current experience amounts
   */
  public int getExp() {
    return this.exp;
  }

  /**
   * Returns the capacity of the amount of experience points to level up
   *
   * @return experience capacity
   */
  public int getExpCapacity() {
    return this.expCapacity;
  }

  /**
   * Increase the amount of experience points
   *
   * @param newExp
   */
  public void increaseExp(int newExp) {
    this.exp += newExp;
    if (this.exp >= this.expCapacity) {
      incrementLevel();
      this.exp = this.exp % this.expCapacity;
    }
  }

  /**
   * Returns the current level of the player
   *
   * @return current level
   */
  public int getLevel() {
    return this.level;
  }

  /** Increments the level and increases the attack and hp stats */
  private void incrementLevel() {
    this.level++;
    this.setAtk(this.getAtk() + 5);
    this.maxHp += 10;
  }

  /**
   * Increase the ammount of bullets to shoot
   *
   * @param ammoAmount
   */
  public void setAmmo(int ammoAmount) {
    projectileAmmo += ammoAmount;
  }

  /**
   * Returns the current amount of bullets that the player can shoot
   *
   * @return
   */
  public int getAmmo() {
    return projectileAmmo;
  }

  /**
   * Returns the amount of keys that the player has collected
   *
   * @return
   */
  public int getKeyCount() {
    return this.keyCount;
  }

  /**
   * Sets the amount of keys
   *
   * @param amount
   */
  public void setKeyCount(int amount) {
    this.keyCount += amount;
  }

  /**
   * Checks if player has collected the golden egg
   *
   * @return true if player has collected the golden egg
   */
  public boolean hasEgg() {
    return this.hasEgg;
  }

  /** Sets the hasEgg attribute to true */
  public void setEggStatus() {
    this.hasEgg = true;
  }

  /**
   * Checks if player collides with an item
   *
   * @param item
   * @param tileSize size of a tile
   * @return true if player collides with an item
   */
  public boolean checkItemCollision(Item item, int tileSize) {
    Rectangle playerHitbox = getHitbox(tileSize / 2);
    Rectangle itemHitbox = item.getHitbox(tileSize / 2);
    return playerHitbox.intersects(itemHitbox.getBoundsInLocal());
  }

  /**
   * Checks if player collides with a lock. If the player has the right amount of keys, the lock is
   * unlocked.
   *
   * @param lock
   * @param tileSize size of a tile
   * @return true if player collides with a lock and unlocks it
   */
  public boolean checkLock(Lock lock, int tileSize) {
    Rectangle playerHitbox = getHitbox(tileSize / 2);
    Rectangle LockHitbox = lock.getHitbox(tileSize / 2);
    if (playerHitbox.intersects(LockHitbox.getBoundsInLocal())
        && this.keyCount >= lock.getLockCount()) {
      lock.unlock();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean checkCollision(double targetX, double targetY, Map map) {
    try {
      if (map.getMap()[(int) Math.round(targetY)][(int) Math.round(targetX)].isDeadly()) {
        enteredMatrix();
      }
    } catch (ArrayIndexOutOfBoundsException e) {
    }
    return super.checkCollision(targetX, targetY, map);
  }

  @Override
  public void loadImages(int tileSize) {
    getSprites()
        .put(
            "front1",
            new Image(getPath() + "/walk_up/up1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "front2",
            new Image(getPath() + "/walk_up/up2.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "back1",
            new Image(getPath() + "/walk_down/down1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "back2",
            new Image(getPath() + "/walk_down/down2.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "left1",
            new Image(getPath() + "/walk_left/left1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "left2",
            new Image(getPath() + "/walk_left/left2.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "right1",
            new Image(
                getPath() + "/walk_right/right1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "right2",
            new Image(
                getPath() + "/walk_right/right2.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "idle1",
            new Image(getPath() + "/idle/idle1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put(
            "idle2",
            new Image(getPath() + "/idle/idle2.png", tileSize, tileSize, false, false, false));
    setCurrentImage(getSprites().get("idle1"));
  }
}
