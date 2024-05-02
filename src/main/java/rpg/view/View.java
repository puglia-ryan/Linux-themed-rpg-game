package rpg.view;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import rpg.model.Entity;
import rpg.model.Item;
import rpg.model.Lock;
import rpg.model.Map;
import rpg.model.MapLayout;
import rpg.model.Monster;
import rpg.model.Player;
import rpg.model.Projectile;

/** View class handles the user interface. */
public class View {

  private GraphicsContext backgroundgc;
  private GraphicsContext gc;
  private Canvas canvas;
  private Canvas backgroundCanvas;

  /**
   * Initiates the canvas and graphicscontext
   *
   * @param gc GraphicsContext
   * @param backgroundgc Background GrpahicsContext
   * @param canvas Canvas
   * @param backgroundCanvas Background Canvas
   */
  public View(
      GraphicsContext gc, GraphicsContext backgroundgc, Canvas canvas, Canvas backgroundCanvas) {
    this.gc = gc;
    this.backgroundgc = backgroundgc;
    this.canvas = canvas;
    this.backgroundCanvas = backgroundCanvas;
  }

  /**
   * Draws the current map
   *
   * @param currentMap
   */
  public void showMap(Map currentMap) {
    backgroundgc.clearRect(
        0, 0, backgroundgc.getCanvas().getWidth(), backgroundgc.getCanvas().getHeight());
    currentMap.drawMap(backgroundgc);
  }

  /**
   * Draws the player, enemies, items and projectiles on the current map.+
   *
   * @param player
   * @param enemies
   * @param projectiles
   * @param items
   * @param currentMap
   */
  public void showEntities(
      Player player,
      ArrayList<Monster> enemies,
      ArrayList<Projectile> projectiles,
      ArrayList<Item> items,
      Map currentMap) {
    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

    for (Entity e : enemies) {
      e.draw(gc, currentMap.getTileSize());
      drawStats(e, currentMap.getTileSize());
    }
    player.draw(gc, currentMap.getTileSize());

    for (int i = 0; i < projectiles.size(); i++) {
      if (projectiles.get(i).projectileExpired()) {
        projectiles.remove(i);
        continue;
      }
      projectiles.get(i).draw(gc, currentMap.getTileSize());
      projectiles.get(i).updateProjectilePos(currentMap);
    }

    for (Item item : items) {
      item.draw(gc, currentMap.getTileSize());
    }

    drawStats(player, currentMap.getTileSize());
    drawPlayerStatsBottom(player);
  }

  /**
   * Draws the lock on the current map.
   *
   * @param lock
   * @param currentMap
   */
  public void showLock(Lock lock, Map currentMap) {
    lock.draw(backgroundgc, currentMap.getTileSize());
  }

  /**
   * Draws the health bar for each enemy and for the player
   *
   * @param entity
   * @param tileSize
   */
  public void drawStats(Entity entity, int tileSize) {
    gc.setFill(Color.WHITE);
    gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));

    // Draw the health bar
    double healthBarWidth = tileSize * 0.6;
    double healthBarHeight = tileSize * 0.1;
    double healthPercentage = (double) entity.getHp() / entity.getMaxHp();
    double healthBarFillWidth = healthPercentage * healthBarWidth;

    // Draw the background of the health bar
    gc.setFill(Color.BLACK);
    gc.fillRect(
        entity.getXPos() * tileSize + tileSize * 0.2,
        entity.getYPos() * tileSize - tileSize * 0.15,
        healthBarWidth,
        healthBarHeight);

    // Draw the filled portion of the health bar based on the player's health
    if (healthPercentage >= 0.75) {
      gc.setFill(Color.GREEN);
    } else if (healthPercentage < 0.75 && healthPercentage >= 0.5) {
      gc.setFill(Color.YELLOW);
    } else if (healthPercentage < 0.5 && healthPercentage >= 0.25) {
      gc.setFill(Color.ORANGE);
    } else {
      gc.setFill(Color.RED);
    }
    gc.fillRect(
        entity.getXPos() * tileSize + tileSize * 0.2,
        entity.getYPos() * tileSize - tileSize * 0.15,
        healthBarFillWidth,
        healthBarHeight);
  }

  /**
   * Draws the stats of the player on the bottom of the screen.
   *
   * @param player
   */
  public void drawPlayerStatsBottom(Player player) {

    double statsBarHeight = canvas.getHeight() / 8;
    gc.setFill(Color.BLUE);
    // gc.fillRect(0, canvas.getHeight() - statsBarHeight, canvas.getHeight(), canvas.getWidth());

    gc.setFont(Font.font("Verdana", 16));
    gc.setFill(Color.WHITE);
    gc.fillText(
        "Health:"
            + player.getHp()
            + "/"
            + player.getMaxHp()
            + "    Level: "
            + player.getLevel()
            + "    Exp: "
            + player.getExp()
            + "/"
            + player.getExpCapacity()
            + "    Ammo: "
            + player.getAmmo()
            + "    Keys: "
            + player.getKeyCount()
            + "/"
            + MapLayout.LOCK.getLockCount(),
        5,
        canvas.getHeight() - statsBarHeight / 2 + 3);
  }

  /**
   * Updates canvas based on the resized window.
   *
   * @param width Width of the new canvas.
   * @param height Height of the new canvas
   * @param currentMap
   * @param player
   * @param enemies
   * @param projectiles
   * @param items
   */
  public void resizeCanvas(
      double width,
      double height,
      Map currentMap,
      Player player,
      ArrayList<Monster> enemies,
      ArrayList<Projectile> projectiles,
      ArrayList<Item> items) {

    currentMap.setTileSize(
        Math.min((int) width / currentMap.getSize(), (int) height / currentMap.getSize()));

    // Set the width, height, and position of the canvas
    canvas.setWidth(currentMap.getSize() * currentMap.getTileSize());
    canvas.setHeight(currentMap.getSize() * currentMap.getTileSize());

    // Resize the background canvas
    backgroundCanvas.setWidth(currentMap.getSize() * currentMap.getTileSize());
    backgroundCanvas.setHeight(currentMap.getSize() * currentMap.getTileSize());

    // Resize the graphics contexts
    backgroundgc = backgroundCanvas.getGraphicsContext2D();
    gc = canvas.getGraphicsContext2D();

    // Update the visuals using the new tileSize
    showMap(currentMap);
  }
}
