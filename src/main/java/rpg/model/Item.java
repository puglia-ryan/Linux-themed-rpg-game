package rpg.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/** Abnstract class that represents an item in the game. */
public abstract class Item {

  private String path;
  private double xPos, yPos;

  /**
   * Constructor for the Item class
   *
   * @param xPos
   * @param yPos
   * @param path file path of the sprite
   */
  public Item(double xPos, double yPos, String path) {
    this.path = path;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  /**
   * Returns the file path
   *
   * @return file path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Returns the x coordinate of the item
   *
   * @return x coordinate
   */
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Returns the y coordinate of the item
   *
   * @return y coordinate
   */
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Returns the collision box of the item
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
   * Draws the item as an image on a canvas
   *
   * @param gc graphics context
   * @param tileSize size of atile
   */
  public void draw(GraphicsContext gc, int tileSize) {
    double x = xPos * tileSize;
    double y = yPos * tileSize;
    gc.drawImage(getImage(tileSize), x, y);
  }

  /**
   * Returns an Image object of the item. The size of the image is based on the tile size.
   *
   * @param tileSize size of a tile
   * @return Image of the item
   */
  public Image getImage(int tileSize) {
    return new Image(this.path, tileSize, tileSize, false, false, false);
  }

  /**
   * Triggers an effect on the player.
   *
   * @param player player character
   */
  public abstract void interact(Player player);
}
