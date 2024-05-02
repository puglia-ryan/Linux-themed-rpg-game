package rpg;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import rpg.controller.Controller;
import rpg.model.Map;
import rpg.model.MapLayout;
import rpg.model.Player;
import rpg.view.View;

// The code implemented in Map.java slighty changed to fit RPG.java

public class RPG extends Application {

  private GraphicsContext backgroundgc;
  private GraphicsContext gc;
  private Canvas backgroundCanvas;
  private Canvas canvas;

  private final int fps = 60;

  public void start(Stage primaryStage) {
    primaryStage.setTitle("Tux's Adventure Game");

    // Set up the entities and the map
    Map currentMap = new Map(MapLayout.WORLD[0][0], MapLayout.LOCK);
    Player player = new Player(100, 20, 2, 2, 0.1, 1);
    player.loadImages(currentMap.getTileSize());

    StackPane root = new StackPane();
    backgroundCanvas =
        new Canvas(
            currentMap.getSize() * currentMap.getTileSize(),
            currentMap.getSize() * currentMap.getTileSize());
    root.getChildren().add(backgroundCanvas);
    canvas =
        new Canvas(
            currentMap.getSize() * currentMap.getTileSize(),
            currentMap.getSize() * currentMap.getTileSize());
    root.getChildren().add(canvas);
    gc = canvas.getGraphicsContext2D();
    backgroundgc = backgroundCanvas.getGraphicsContext2D();

    root.setAlignment(Pos.CENTER);
    root.setPrefWidth(primaryStage.getWidth());
    root.setPrefHeight(primaryStage.getHeight() + currentMap.getTileSize());

    // Set up View und controller
    View view = new View(gc, backgroundgc, canvas, backgroundCanvas);
    Controller controller = new Controller(player, view);

    view.showMap(currentMap);
    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    setUpKeyListeners(primaryStage, controller);
    scene.setFill(Color.BLACK);

    // Game loop
    AnimationTimer gameLoop =
        new AnimationTimer() {
          private long lastUpdateTime = 0;
          private final long desiredFrameTime = 1000000000 / fps;

          public void handle(long time) {
            if (time - lastUpdateTime >= desiredFrameTime) {
              controller.checkCollisions();
              controller.update();

              if (player.getHp() <= 0 || player.getEnteredMatrix()) {
                this.stop();
                showGameOverScreen(primaryStage, "You died! Skill issue lmao");
              } else if (player.hasEgg()) {
                this.stop();
                showGameOverScreen(primaryStage, "Congrats! You beat the game.");
              }

              lastUpdateTime = time;
            }
          }
        };

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setWidth(screenBounds.getWidth());
    primaryStage.setHeight(screenBounds.getHeight());

    gameLoop.start();
    primaryStage.show();
  }

  public void setUpKeyListeners(Stage primaryStage, Controller controller) {
    Scene scene = primaryStage.getScene();
    scene.setOnKeyPressed(controller::handleKeyPress);
    scene.setOnKeyReleased(controller::handleKeyRelease);
    primaryStage
        .widthProperty()
        .addListener(
            (obs, oldWidth, newWidth) -> {
              controller.handleResize(newWidth.doubleValue(), primaryStage.getHeight());
            });
    primaryStage
        .heightProperty()
        .addListener(
            (obs, oldHeight, newHeight) -> {
              controller.handleResize(primaryStage.getWidth(), newHeight.doubleValue());
            });
  }

  private void showGameOverScreen(Stage primaryStage, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(message);
    alert.setContentText("Would you like to restart the game?");

    ButtonType restartButton = new ButtonType("Restart");
    ButtonType exitButton = new ButtonType("Exit");
    alert.getButtonTypes().setAll(restartButton, exitButton);

    alert.setOnCloseRequest(
        event -> {
          if (alert.getResult() == restartButton) {
            restartGame(primaryStage);
          } else {
            primaryStage.close();
          }
        });

    alert.show();
  }

  private void restartGame(Stage primaryStage) {
    // Recreate the application to restart the game
    RPG newGame = new RPG();
    try {
      newGame.start(primaryStage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    launch(args);
  }
}
