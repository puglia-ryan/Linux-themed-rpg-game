package rpg.controller;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import rpg.model.Entity;
import rpg.model.Generator;
import rpg.model.Item;
import rpg.model.Lock;
import rpg.model.Map;
import rpg.model.MapLayout;
import rpg.model.Monster;
import rpg.model.Player;
import rpg.model.Projectile;
import rpg.model.monsters.Boss;
import rpg.model.projectiles.BossProjectile;
import rpg.model.projectiles.Sword;
import rpg.view.View;

/** Controller handles the key inputs and updates the UI. */
public class Controller {

  private View view;
  private Player player;
  private ArrayList<ArrayList<ArrayList<Monster>>> monsterLayout;
  private ArrayList<Monster> enemies;
  private Map currentMap;
  private static int currentMapIndexX, currentMapIndexY;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Item> items;
  private ArrayList<ArrayList<ArrayList<Item>>> itemsLayout;
  private Lock lock;

  private boolean upKeyPressed;
  private boolean downKeyPressed;
  private boolean leftKeyPressed;
  private boolean rightKeyPressed;
  private boolean spaceKeyPressed;
  private boolean nothingPressed;
  private boolean sKeyPressed;

  /**
   * Constructor of the controller
   *
   * @param player player character
   * @param view view
   */
  public Controller(Player player, View view) {
    this.view = view;
    currentMapIndexX = 1;
    currentMapIndexY = 0;
    lock = MapLayout.LOCK;
    currentMap = new Map(MapLayout.WORLD[currentMapIndexX][currentMapIndexY], lock);
    this.player = player;

    Generator g = new Generator();
    monsterLayout = g.getMonstersLayout();
    itemsLayout = g.getItemsLayout();

    enemies = monsterLayout.get(currentMapIndexX).get(currentMapIndexY);
    items = itemsLayout.get(currentMapIndexX).get(currentMapIndexY);
  }

  /**
   * Returns the row index of the current map section.
   *
   * @return row index
   */
  public static int getMapIndexX() {
    return currentMapIndexX;
  }

  /**
   * Returns the columns index of the current map section.
   *
   * @return
   */
  public static int getMapIndexY() {
    return currentMapIndexY;
  }

  /**
   * Handles the key press event for the arrow keys, space and "S" key
   *
   * @param event key event
   */
  public void handleKeyPress(javafx.scene.input.KeyEvent event) {
    KeyCode keyCode = event.getCode();
    switch (keyCode) {
      case UP:
        upKeyPressed = true;
        nothingPressed = false;
        break;
      case DOWN:
        downKeyPressed = true;
        nothingPressed = false;
        break;
      case LEFT:
        leftKeyPressed = true;
        nothingPressed = false;
        break;
      case RIGHT:
        rightKeyPressed = true;
        nothingPressed = false;
        break;
      case SPACE:
        spaceKeyPressed = true;
        nothingPressed = false;
        break;
      case S:
        sKeyPressed = true;
        nothingPressed = false;
        break;
      default:
        nothingPressed = true;
        break;
    }
  }

  /**
   * Handles the key release event for the arrow keys, space and "S" key
   *
   * @param event key event
   */
  public void handleKeyRelease(javafx.scene.input.KeyEvent event) {
    KeyCode keyCode = event.getCode();
    switch (keyCode) {
      case UP:
        upKeyPressed = false;
        nothingPressed = true;
        break;
      case DOWN:
        downKeyPressed = false;
        nothingPressed = true;
        break;
      case LEFT:
        leftKeyPressed = false;
        nothingPressed = true;
        break;
      case RIGHT:
        rightKeyPressed = false;
        nothingPressed = true;
        break;
      case SPACE:
        spaceKeyPressed = false;
        nothingPressed = true;
        break;
      case S:
        sKeyPressed = false;
        nothingPressed = true;
        break;
      default:
        nothingPressed = false;
        break;
    }
  }

  /**
   * Triggers when the user resizes the window. The UI is updated based on the new dimensions.
   *
   * @param width Width of the new window
   * @param height Height of the new window
   */
  public void handleResize(double width, double height) {
    view.resizeCanvas(width, height, currentMap, player, enemies, projectiles, items);
    view.showEntities(player, enemies, projectiles, items, currentMap);
    if ((currentMapIndexX == MapLayout.rowLockPos) && (currentMapIndexY == MapLayout.colLockPos)) {
      view.showLock(lock, currentMap);
    }
    player.loadImages(currentMap.getTileSize());
    for (Entity e : enemies) {
      e.loadImages(currentMap.getTileSize());
    }
    for (Projectile p : projectiles) {
      p.loadImages(currentMap.getTileSize());
    }
    for (Item i : items) {
      i.getImage(currentMap.getTileSize());
    }
  }

  public boolean isUpKeyPressed() {
    return upKeyPressed;
  }

  public boolean isDownKeyPressed() {
    return downKeyPressed;
  }

  public boolean isLeftKeyPressed() {
    return leftKeyPressed;
  }

  public boolean isRightKeyPressed() {
    return rightKeyPressed;
  }

  public boolean isSpaceKeyPressed() {
    return spaceKeyPressed;
  }

  public boolean isNothingPressed() {
    return nothingPressed;
  }

  private boolean isSKeyPressed() {
    return sKeyPressed;
  }

  /** Checks the collision between the player character and all the other elements on the map. */
  public void checkCollisions() {
    /* Check collisions with the enemies. */
    for (Entity e : enemies) {
      if (player.checkEnemyCollision(e, currentMap.getTileSize())) {
        player.loseHp(e.getAtk());
      }

      /* Checks if one of the enemies is hit by a projectile of the player. */
      for (int i = 0; i < projectiles.size(); i++) {
        if (e.checkEnemyCollision(projectiles.get(i), currentMap.getTileSize())
            && projectiles.get(i).canDamage()
            && projectiles.get(i).isPlayerProjectileFired()) {
          e.loseHp(player.getAtk());
        }

        /* Checks if player is hit by a projectile. */
        if (player.checkEnemyCollision(projectiles.get(i), currentMap.getTileSize())
            && projectiles.get(i).canDamage()
            && projectiles.get(i).isPlayerProjectileFired() == false) {
          player.loseHp(10);
        }
      }
    }

    /* Checks if the player collides with an item. */
    for (Item item : items) {
      if (player.checkItemCollision(item, currentMap.getTileSize())) {
        item.interact(player);
        items.remove(item);
        break;
      }
    }
  }

  /** Updates the UI for each frame. */
  public void update() {
    int worldHeight = MapLayout.WORLD.length;
    int worldWidth = MapLayout.WORLD[0].length;

    /* Checks if an enemy is killed by the player. */
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).incrementCounter();
      if (enemies.get(i).getHp() <= 0) {
        Item droppedItem = enemies.get(i).drop();
        if (droppedItem != null) {
          items.add(droppedItem);
        }
        enemies.remove(i);
        player.increaseExp(10);
        continue;
      }

      /* Triggers the shooting of projectiles for each enemy. */
      if (enemies.get(i).canShoot()) {
        String projectilePath = enemies.get(i).getPath() + "_projectile.png";
        Projectile enemyProjectile;
        if (enemies.get(i) instanceof Boss) {
          enemyProjectile =
              new BossProjectile(
                  enemies.get(i).getXPos(),
                  enemies.get(i).getYPos() + 2,
                  0.15,
                  currentMap,
                  enemies.get(i).getDirection(),
                  false,
                  projectilePath);
        } else {
          enemyProjectile =
              new Projectile(
                  enemies.get(i).getXPos(),
                  enemies.get(i).getYPos(),
                  0.15,
                  currentMap,
                  enemies.get(i).getDirection(),
                  false,
                  projectilePath);
        }
        enemyProjectile.loadImages(currentMap.getTileSize());
        projectiles.add(enemyProjectile);
        enemies.get(i).setLastProjectileFired(System.nanoTime());
      }
    }
    moveEntites();
    handleLock();
    view.showEntities(player, enemies, projectiles, items, currentMap);

    /* Moves the player up if the up arrow key is pressed. */
    if (isUpKeyPressed()) {
      player.setDirection("up");
      player.moveUp(currentMap);
      if ((player.getYPos() <= 0) && (currentMapIndexX > 0)) {
        player.setPos(player.getXPos(), currentMap.getSize() - 1);
        currentMapIndexX--;
        loadMapSection();
      }
    }

    /* Moves the player down if the down arrow key is pressed. */
    if (isDownKeyPressed()) {
      player.setDirection("down");
      player.moveDown(currentMap);
      if ((player.getYPos() + 1 >= currentMap.getSize()) && (currentMapIndexX < worldHeight - 1)) {
        player.setPos(player.getXPos(), 0);
        currentMapIndexX++;
        loadMapSection();
      }
    }

    /* Moves the player left if the left arrow key is pressed. */
    if (isLeftKeyPressed()) {
      player.setDirection("left");
      player.moveLeft(currentMap);
      if ((player.getXPos() <= 0) && (currentMapIndexY > 0)) {
        player.setPos(currentMap.getSize() - 1, player.getYPos());
        currentMapIndexY--;
        loadMapSection();
      }
    }

    /* Moves the player right if the right arrow key is pressed. */
    if (isRightKeyPressed()) {
      player.setDirection("right");
      player.moveRight(currentMap);
      if ((player.getXPos() + 1 >= currentMap.getSize()) && (currentMapIndexY < worldWidth - 1)) {
        player.setPos(0, player.getYPos());
        currentMapIndexY++;
        loadMapSection();
      }
    }

    /* Triggers if none of the arrow keys is pressed. */
    if (isNothingPressed()) {
      if (player.getIdleCounter() >= 60) {
        player.setDirection("idle");
        player.dontMove();
      }
      player.incrementIdleCounter();
    } else {
      player.resetIdleCounter();
    }

    /* Player shoots a projectile if the space key is pressed. */
    if (isSpaceKeyPressed()) {
      if (player.canShoot() && player.getAmmo() > 0) {
        Projectile projectile =
            new Projectile(
                player.getXPos(),
                player.getYPos(),
                0.3,
                currentMap,
                player.getDirection(),
                true,
                "sprites/player/player_projectile.png");
        projectile.loadImages(currentMap.getTileSize());
        projectiles.add(projectile);
        player.setLastProjectileFired(System.nanoTime());
        player.setAmmo(-1);
      }
    }

    /* Player shoots a sword if the S key is pressed. */
    if (isSKeyPressed()) {
      if (System.nanoTime() - player.getLastProjectileFired() >= 500000000) {
        Sword sword =
            new Sword(
                player.getXPos(), player.getYPos(), 0.3, currentMap, player.getDirection(), true);
        sword.loadImages(currentMap.getTileSize());
        projectiles.add(sword);
        player.setLastProjectileFired(System.nanoTime());
      }
    }

    player.incrementCounter();
  }

  /** Loads all the components of the new map section that the player has entered. */
  public void loadMapSection() {
    currentMap.setMap(MapLayout.WORLD[currentMapIndexX][currentMapIndexY]);
    if (currentMapIndexX < monsterLayout.size()
        && currentMapIndexY < monsterLayout.get(currentMapIndexX).size()) {
      enemies = monsterLayout.get(currentMapIndexX).get(currentMapIndexY);
      items = itemsLayout.get(currentMapIndexX).get(currentMapIndexY);
    }
    view.showMap(currentMap);
    projectiles.clear();
  }

  /** Triggers the move method of each enemy on the current screen. */
  public void moveEntites() {
    for (Monster m : enemies) {
      m.triggerMove(currentMap);
    }
  }

  /** Method that handles the player interaction with the lock. */
  public void handleLock() {
    if ((currentMapIndexX == MapLayout.rowLockPos) && (currentMapIndexY == MapLayout.colLockPos)) {
      view.showLock(lock, currentMap);
      if (player.checkLock(lock, currentMap.getTileSize()) && !lock.isUpdated()) {
        view.showMap(currentMap);
        lock.setUpdated();
      }
    }
  }
}
