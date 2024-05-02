package rpg.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** Class that contains the tile layout. */
public class Map {

  private int tileSize = 64;
  private int mapWidth, mapHeight;
  private Tile[][] map;
  private Tile[] tiles;
  private Lock lock;

  /**
   * Converts 2d integer array to a 2d Tile array
   *
   * @param map 2d integer array where each integer represents a specific tile
   * @param lock lock object
   */
  public Map(int[][] map, Lock lock) {
    this.tiles = Tile.values();
    this.mapWidth = map[0].length;
    this.mapHeight = map.length;
    this.map = new Tile[this.mapHeight][this.mapWidth];
    for (int r = 0; r < this.mapHeight; r++) {
      for (int c = 0; c < this.mapWidth; c++) {
        this.map[r][c] = this.tiles[map[r][c]];
      }
    }
    this.lock = lock;
  }

  /**
   * Returns width/height of the map
   *
   * @return width/height
   */
  public int getSize() {
    return this.mapWidth;
  }

  /**
   * Returns size of a tile
   *
   * @return tile size
   */
  public int getTileSize() {
    return tileSize;
  }

  /**
   * Sets a new value for the tile size
   *
   * @param newTileSize new tile size
   */
  public void setTileSize(int newTileSize) {
    tileSize = newTileSize;
  }

  /**
   * Returns the tile layout as 2d array
   *
   * @return tile layout
   */
  public Tile[][] getMap() {
    return map;
  }

  /**
   * Replaces the current tile layout with a new one
   *
   * @param newMap 2d integer array which represents an new tile layout
   */
  public void setMap(int[][] newMap) {
    for (int r = 0; r < this.map.length; r++) {
      for (int c = 0; c < this.map.length; c++) {
        this.map[r][c] = this.tiles[newMap[r][c]];
      }
    }
  }

  /**
   * Returns the lock object
   *
   * @return lock
   */
  public Lock getLock() {
    return this.lock;
  }

  /**
   * Draws the map by drawing each tile in the array
   *
   * @param gc graphics context
   */
  public void drawMap(GraphicsContext gc) {
    for (int row = 0; row < this.mapHeight; row++) {
      for (int col = 0; col < this.mapWidth; col++) {
        Image tileImage = this.map[row][col].getImage(tileSize);
        gc.drawImage(tileImage, col * tileSize, row * tileSize, tileSize, tileSize);
      }
    }
  }
}
