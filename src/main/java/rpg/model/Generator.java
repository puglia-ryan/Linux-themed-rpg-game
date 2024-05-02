package rpg.model;

import java.util.ArrayList;
import java.util.Arrays;
import rpg.model.items.Ammo;
import rpg.model.items.Heart;
import rpg.model.items.Key;
import rpg.model.monsters.Boss;
import rpg.model.monsters.EnemyOne;
import rpg.model.monsters.EnemyVirus;
import rpg.model.monsters.EnemyWindows;
import rpg.model.monsters.EnemyZero;
import rpg.model.monsters.TurretEnemy;

/** Class that generates items and enemies for each map section. */
public class Generator {

  /** List of monsters for map section 1 */
  private ArrayList<Monster> monsters1 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyZero(40, 10, 2, 2, 0.075),
              new EnemyZero(40, 10, 13, 13, 0.075),
              new EnemyZero(40, 10, 13, 2, 0.075),
              new TurretEnemy(50, 15, 6, 6),
              new TurretEnemy(50, 15, 9, 6)));

  /** List of items for map section 1 */
  private ArrayList<Item> items1 = new ArrayList<>(Arrays.asList(new Ammo(14, 14)));

  /** List of monsters for map section 2 */
  private ArrayList<Monster> monsters2 =
      new ArrayList<>(
          Arrays.asList(
              new TurretEnemy(50, 15, 2, 3),
              new TurretEnemy(50, 15, 3, 2),
              new TurretEnemy(50, 15, 11, 12),
              new TurretEnemy(50, 15, 12, 11),
              new EnemyWindows(70, 15, 13, 2, 0.025)));

  /** List of items for map section 2 */
  private ArrayList<Item> items2 = new ArrayList<>(Arrays.asList());

  /** List of monsters for map section 3 */
  private ArrayList<Monster> monsters3 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyWindows(70, 15, 7, 5, 0.025),
              new EnemyWindows(70, 15, 7, 10, 0.025),
              new EnemyWindows(90, 20, 9, 7, 0.025),
              new EnemyVirus(50, 10, 11, 1, 0.05),
              new EnemyVirus(50, 10, 11, 14, 0.05)));

  /** List of items for map section 3 */
  private static ArrayList<Item> items3 =
      new ArrayList<>(Arrays.asList(new Key(12, 7), new Heart(7, 7), new Ammo(8, 8)));

  /** List of monsters for map section 4 */
  private ArrayList<Monster> monsters4 = new ArrayList<>(Arrays.asList());

  /** List of items for map section 4 */
  private ArrayList<Item> items4 = new ArrayList<>(Arrays.asList());

  /** List of monsters for map section 5 */
  private ArrayList<Monster> monsters5 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyOne(40, 10, 2, 2, 0.075),
              new EnemyOne(40, 10, 6, 2, 0.075),
              new EnemyOne(40, 10, 10, 10, 0.075)));

  /** List of items for map section 5 */
  private ArrayList<Item> items5 = new ArrayList<>(Arrays.asList(new Ammo(8, 1)));

  /** List of monsters for map section 6 */
  private ArrayList<Monster> monsters6 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyWindows(100, 15, 7, 7, 0.025),
              new EnemyVirus(50, 10, 1, 7, 0.1),
              new EnemyVirus(50, 10, 14, 7, 0.1)));

  /** List of items for map section 6 */
  private ArrayList<Item> items6 =
      new ArrayList<>(Arrays.asList(new Key(7, 7), new Heart(7, 1), new Ammo(8, 1)));

  /** List of monsters for map section 7 */
  private ArrayList<Monster> monsters7 =
      new ArrayList<>(
          Arrays.asList(
              new TurretEnemy(50, 15, 6, 6),
              new TurretEnemy(50, 15, 6, 9),
              new TurretEnemy(50, 15, 9, 6),
              new TurretEnemy(50, 15, 9, 9),
              new EnemyVirus(50, 10, 1, 7, 0.025),
              new EnemyVirus(50, 10, 2, 7, 0.025),
              new EnemyVirus(50, 10, 3, 7, 0.025),
              new EnemyVirus(50, 10, 14, 7, 0.025),
              new EnemyVirus(50, 10, 13, 7, 0.025),
              new EnemyVirus(50, 10, 12, 7, 0.025)));

  /** List of items for map section 7 */
  private ArrayList<Item> items7 = new ArrayList<>(Arrays.asList());

  /** List of monsters for map section 8 */
  private ArrayList<Monster> monsters8 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyWindows(70, 15, 7, 8, 0.025),
              new EnemyOne(40, 10, 14, 5, 0.1),
              new EnemyZero(40, 10, 14, 2, 0.1)));

  /** List of items for map section 8 */
  private ArrayList<Item> items8 = new ArrayList<>(Arrays.asList(new Key(7, 13), new Heart(7, 10)));

  /** List of monsters for map section 9 */
  private ArrayList<Monster> monsters9 =
      new ArrayList<>(
          Arrays.asList(
              new TurretEnemy(50, 15, 5, 5),
              new TurretEnemy(50, 15, 10, 10),
              new TurretEnemy(50, 15, 5, 10),
              new TurretEnemy(50, 15, 10, 5),
              new EnemyOne(40, 10, 3, 1, 0.05),
              new EnemyOne(40, 10, 12, 14, 0.05),
              new EnemyZero(40, 10, 12, 1, 0.05),
              new EnemyZero(40, 10, 3, 14, 0.05)));

  /** List of items for map section 9 */
  private ArrayList<Item> items9 =
      new ArrayList<>(Arrays.asList(new Ammo(11, 11), new Ammo(11, 4)));

  /** List of monsters for map section 10 */
  private ArrayList<Monster> monsters10 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyVirus(40, 10, 7, 7, 0.025),
              new EnemyVirus(40, 10, 8, 8, 0.025),
              new EnemyVirus(40, 10, 7, 8, 0.025),
              new EnemyVirus(40, 10, 8, 7, 0.025),
              new TurretEnemy(50, 15, 12, 5),
              new TurretEnemy(50, 15, 3, 5),
              new TurretEnemy(50, 15, 12, 10),
              new TurretEnemy(50, 15, 3, 10)));

  /** List of items for map section 10 */
  private ArrayList<Item> items10 = new ArrayList<>(Arrays.asList(new Ammo(1, 8)));

  /** List of monsters for map section 11 */
  private ArrayList<Monster> monsters11 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyWindows(100, 20, 7, 7, 0.025),
              new TurretEnemy(50, 15, 2, 5),
              new TurretEnemy(50, 15, 2, 10),
              new TurretEnemy(50, 15, 13, 5),
              new TurretEnemy(50, 15, 13, 10)));

  /** List of items for map section 11 */
  private ArrayList<Item> items11 = new ArrayList<>(Arrays.asList(new Ammo(7, 7)));

  /** List of monsters for map section 12 */
  private ArrayList<Monster> monsters12 =
      new ArrayList<>(Arrays.asList(new Boss(400, 20, 7, 3, 0.025)));

  /** List of items for map section 12 */
  private ArrayList<Item> items12 =
      new ArrayList<>(
          Arrays.asList(new Heart(1, 13), new Heart(14, 2), new Ammo(1, 2), new Ammo(14, 13)));

  /** List of monsters for map section 13 */
  private ArrayList<Monster> monsters13 =
      new ArrayList<>(
          Arrays.asList(
              new TurretEnemy(50, 15, 3, 12),
              new TurretEnemy(50, 15, 2, 11),
              new TurretEnemy(50, 15, 1, 10),
              new TurretEnemy(50, 15, 12, 2),
              new TurretEnemy(50, 15, 13, 3),
              new EnemyOne(40, 10, 2, 2, 0.075),
              new EnemyZero(40, 10, 13, 13, 0.075)));

  /** List of items for map section 13 */
  private ArrayList<Item> items13 = new ArrayList<>(Arrays.asList(new Heart(6, 12)));

  /** List of monsters for map section 14 */
  private ArrayList<Monster> monsters14 =
      new ArrayList<>(
          Arrays.asList(
              new TurretEnemy(50, 15, 8, 5),
              new TurretEnemy(50, 15, 5, 1),
              new EnemyOne(50, 15, 4, 7, 0.1),
              new EnemyZero(50, 15, 13, 3, 0.1),
              new EnemyZero(50, 15, 13, 13, 0.1),
              new EnemyVirus(50, 15, 5, 14, 0.01),
              new EnemyVirus(50, 15, 8, 14, 0.01),
              new EnemyVirus(50, 15, 14, 6, 0.01),
              new EnemyVirus(50, 15, 14, 9, 0.01)));

  /** List of items for map section 14 */
  private ArrayList<Item> items14 = new ArrayList<>(Arrays.asList());

  /** List of monsters for map section 15 */
  private ArrayList<Monster> monsters15 =
      new ArrayList<>(
          Arrays.asList(
              new EnemyWindows(100, 20, 5, 8, 0.025),
              new EnemyWindows(100, 15, 10, 8, 0.025),
              new EnemyVirus(50, 15, 5, 2, 0.075),
              new EnemyVirus(50, 15, 5, 13, 0.075),
              new EnemyVirus(50, 15, 10, 2, 0.01),
              new EnemyVirus(50, 15, 10, 13, 0.01)));

  /** List of items for map section 15 */
  private ArrayList<Item> items15 =
      new ArrayList<>(Arrays.asList(new Heart(12, 7), new Ammo(13, 7)));

  private ArrayList<ArrayList<ArrayList<Monster>>> monsterGrid;
  private ArrayList<ArrayList<ArrayList<Item>>> itemsGrid;

  /** Generator generates items and enemies after initialization. */
  public Generator() {
    generateMonsters();
    generateItems();
  }

  /** Stores all the created lists of enemies in another ArrayList. */
  private void generateMonsters() {
    monsterGrid = new ArrayList<>();
    // Row 1
    ArrayList<ArrayList<Monster>> monsterRow1 = new ArrayList<>();
    monsterRow1.add(monsters1);
    monsterRow1.add(monsters2);
    monsterRow1.add(monsters3);

    // Row 2
    ArrayList<ArrayList<Monster>> monsterRow2 = new ArrayList<>();
    monsterRow2.add(monsters4);
    monsterRow2.add(monsters5);
    monsterRow2.add(monsters6);

    // Row 3
    ArrayList<ArrayList<Monster>> monsterRow3 = new ArrayList<>();
    monsterRow3.add(monsters7);
    monsterRow3.add(monsters8);
    monsterRow3.add(monsters9);

    // Row 4
    ArrayList<ArrayList<Monster>> monsterRow4 = new ArrayList<>();
    monsterRow4.add(monsters10);
    monsterRow4.add(monsters11);
    monsterRow4.add(monsters12);

    // Row 5
    ArrayList<ArrayList<Monster>> monsterRow5 = new ArrayList<>();
    monsterRow5.add(monsters13);
    monsterRow5.add(monsters14);
    monsterRow5.add(monsters15);

    // Add rows
    monsterGrid.add(monsterRow1);
    monsterGrid.add(monsterRow2);
    monsterGrid.add(monsterRow3);
    monsterGrid.add(monsterRow4);
    monsterGrid.add(monsterRow5);
  }

  /** Stores all the created lists of items in another ArrayList. */
  private void generateItems() {
    itemsGrid = new ArrayList<>();
    // Row 1
    ArrayList<ArrayList<Item>> itemRow1 = new ArrayList<>();
    itemRow1.add(items1);
    itemRow1.add(items2);
    itemRow1.add(items3);

    // Row 2
    ArrayList<ArrayList<Item>> itemRow2 = new ArrayList<>();
    itemRow2.add(items4);
    itemRow2.add(items5);
    itemRow2.add(items6);

    // Row 3
    ArrayList<ArrayList<Item>> itemRow3 = new ArrayList<>();
    itemRow3.add(items7);
    itemRow3.add(items8);
    itemRow3.add(items9);

    // Row 4
    ArrayList<ArrayList<Item>> itemRow4 = new ArrayList<>();
    itemRow4.add(items10);
    itemRow4.add(items11);
    itemRow4.add(items12);

    // Row 5
    ArrayList<ArrayList<Item>> itemRow5 = new ArrayList<>();
    itemRow5.add(items13);
    itemRow5.add(items14);
    itemRow5.add(items15);

    // Add rows
    itemsGrid.add(itemRow1);
    itemsGrid.add(itemRow2);
    itemsGrid.add(itemRow3);
    itemsGrid.add(itemRow4);
    itemsGrid.add(itemRow5);
  }

  public ArrayList<ArrayList<ArrayList<Monster>>> getMonstersLayout() {
    return monsterGrid;
  }

  public ArrayList<ArrayList<ArrayList<Item>>> getItemsLayout() {
    return itemsGrid;
  }
}
