package rpg.model;

import javafx.scene.image.Image;

/** Class that represents an monster in the game and extends the entity class */
public abstract class Monster extends Entity {

  private String moveDirection;
  private long prevMoveTime;
  private final long moveCoolDown = 500_000_000;

  /**
   * Constructor for the monster
   *
   * @param hp hitpoints stat
   * @param atk attack stat
   * @param xPos x coordinate
   * @param yPos y coordinate
   * @param moveSpeed movement speed
   * @param sprite file path
   */
  public Monster(int hp, int atk, int xPos, int yPos, double moveSpeed, String sprite) {
    super(hp, atk, xPos, yPos, moveSpeed, sprite);
    this.moveDirection = "down";
    this.prevMoveTime = 0;
    setShootCooldown(2000);
  }

  /** Selects the movement direction randomly */
  public void setMoveDirection() {
    double r = Math.random();
    if (r < 0.125) {
      setDirection("down");
    } else if (r < 0.25) {
      setDirection("up");
    } else if (r < 0.375) {
      setDirection("right");
    } else if (r < 0.5) {
      setDirection("left");
    } else {
      setDirection("idle");
    }
  }

  /** Changes the direction if the monster is able to */
  public void switchDirection() {
    if (canSwitchDirection()) {
      setMoveDirection();
      prevMoveTime = System.nanoTime();
    }
  }

  /**
   * Checks if monster can change the movement direction
   *
   * @return true if movement cool down is passed
   */
  public boolean canSwitchDirection() {
    return (System.nanoTime() - prevMoveTime >= moveCoolDown);
  }

  /**
   * Monster moves based on the direction
   *
   * @param map current map
   */
  public void triggerMove(Map map) {
    switchDirection();
    animate();
    switch (getDirection()) {
      case "down":
        this.moveDown(map);
        break;
      case "up":
        this.moveUp(map);
        break;
      case "left":
        this.moveLeft(map);
        break;
      case "right":
        this.moveRight(map);
        break;
      default:
        break;
    }
  }

  public void animate() {
    if (getAnimationCounter() < getAnimationFrameSpeed() / 2)
      setCurrentImage(getSprites().get("sprite1"));
    else if (getAnimationCounter() >= getAnimationFrameSpeed() / 2)
      setCurrentImage(getSprites().get("sprite2"));
  }

  @Override
  public void moveUp(Map map) {
    move(0, -getMoveSpeed(), map);
  }

  @Override
  public void moveDown(Map map) {
    move(0, getMoveSpeed(), map);
  }

  @Override
  public void moveLeft(Map map) {
    move(-getMoveSpeed(), 0, map);
  }

  @Override
  public void moveRight(Map map) {
    move(getMoveSpeed(), 0, map);
  }

  @Override
  public void loadImages(int tileSize) {
    getSprites()
        .put("sprite1", new Image(getPath() + "1.png", tileSize, tileSize, false, false, false));
    getSprites()
        .put("sprite2", new Image(getPath() + "2.png", tileSize, tileSize, false, false, false));
  }

  /**
   * Drops an item if the hp stat is 0
   *
   * @return dropped item
   */
  public abstract Item drop();
}
