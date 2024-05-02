package rpg.model;

import javafx.scene.image.Image;

/** Contains a list of possible tiles in the game */
public enum Tile {
  MATRIXFLOOR2("matrixfloor2", "sprites/tiles/matrixfloor2.png", 0, true, true),
  FLOOR1("floor1", "sprites/tiles/floor1.png", 1, true, false),
  WALL1("wall1", "sprites/tiles/wall1.png", 2, false, false),
  WALL1_VERTICAL("wall1_vertical", "sprites/tiles/wall1_vertical.png", 3, false, false),
  WALL1_HORIZONTAL("wall1_horizontal", "sprites/tiles/wall1_horizontal.png", 4, false, false),
  WALL1_CORNER1("wall1_corner1", "sprites/tiles/wall1_corner1.png", 5, false, false),
  WALL1_CORNER2("wall1_corner2", "sprites/tiles/wall1_corner2.png", 6, false, false),
  WALL1_CORNER3("wall1_corner3", "sprites/tiles/wall1_corner3.png", 7, false, false),
  WALL1_CORNER4("wall1_corner4", "sprites/tiles/wall1_corner4.png", 8, false, false),
  WALL1_CORNER5("wall1_corner5", "sprites/tiles/wall1_corner5.png", 9, false, false),
  WALL1_CORNER6("wall1_corner6", "sprites/tiles/wall1_corner6.png", 10, false, false),
  WALL1_CORNER7("wall1_corner7", "sprites/tiles/wall1_corner7.png", 11, false, false),
  WALL1_CORNER8("wall1_corner8", "sprites/tiles/wall1_corner8.png", 12, false, false);

  private final String name;
  private final String path;
  private final int id;
  private final boolean isTraversable;
  private final boolean deadly;

  /**
   * Constructor for a tile
   *
   * @param name
   * @param path
   * @param id
   * @param isTraversable indicates if entity can traverse the tile
   * @param deadly indicates if player can die by traversing the tile
   */
  Tile(String name, String path, int id, boolean isTraversable, boolean deadly) {
    this.name = name;
    this.path = path;
    this.id = id;
    this.isTraversable = isTraversable;
    this.deadly = deadly;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Returns the file path of the sprite
   *
   * @return file
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Returns the id
   *
   * @return id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Checks if tile is traversable
   *
   * @return true if entity can traverse the tile
   */
  public boolean isTraversable() {
    return this.isTraversable;
  }

  /**
   * Checks if tile can kill the player
   *
   * @return true if deadly
   */
  public boolean isDeadly() {
    return deadly;
  }

  /**
   * Returns an Image object of the tile. The size of the image is based on the tile size.
   *
   * @param tileSize size of a tile
   * @return Image of the tile
   */
  public Image getImage(int tileSize) {
    return new Image(path, tileSize, tileSize, false, false, false);
  }
}
