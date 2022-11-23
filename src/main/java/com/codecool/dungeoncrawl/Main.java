package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    int[] cameraSize = new int[]{25, 35};
    Cell centralCell;
    GameMap map = MapLoader.loadMap();
    Canvas canvas;
    GraphicsContext context;
    Label healthLabel = new Label();
    Label inventoryListLabel = new Label();
    Button pickUpButton = new Button("Pick Up");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory: "), 0, 1);
        ui.add(inventoryListLabel, 0, 2);
        ui.add(pickUpButton, 0, 3);
        pickUpButton.setFocusTraversable(false);
        inventoryListLabel.setMinHeight(50);
        pickUpButton.setVisible(false);

        EventHandler<ActionEvent> event = e -> pickUpItemEvent();
        pickUpButton.setOnAction(event);
        BorderPane borderPane = new BorderPane();
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        canvas = new Canvas(
                size.getWidth() * 0.9,
                size.getHeight() * 0.9);

        context = canvas.getGraphicsContext2D();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        initMap();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Direction direction = null;
        if (map.getMonstersList() != null) {
            map.getMonstersList().forEach(Actor::monsterMove);
        }
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                direction = Direction.UP;
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                direction = Direction.DOWN;
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                direction = Direction.LEFT;
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                direction = Direction.RIGHT;
                break;
            case ENTER:
                pickUpItemEvent();
                refresh();
                break;
        }
        moveCamera(direction);
    }

    private void refresh() {
        context.setFill(Color.rgb(71, 45, 60));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpButton.setVisible(map.getPlayer().canPickUp());
    }

    private void initMap() {
        refresh();
        centerCamera();
        moveCamera(Direction.UP);
    }

    private void centerCamera() {
        centralCell = map.getPlayer().getCell();
    }

    private void pickUpItemEvent() {
        map.getPlayer().pickUp();
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < map.getPlayer().getItems().size(); i++) {
            inventoryListLabel.setText(map.getPlayer().getItems().get(i).getTileName());
            items.append(map.getPlayer().getItems().get(i).getTileName()).append("\n");
        }
        inventoryListLabel.setText(items.toString());
    }

    private void moveCamera(Direction direction) {
        for (int xFactor = 0; xFactor < cameraSize[0] + 18; xFactor++) {
            for (int yFactor = 0; yFactor < cameraSize[1]; yFactor++) {
                int x = centralCell.getX() + xFactor - cameraSize[0] / 2 + direction.getValue().getX() - 10;
                int y = centralCell.getY() + yFactor - cameraSize[1] / 2 + direction.getValue().getY() + 5;
                if (map.isInBounds(x, y)) {
                    Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), xFactor, yFactor);
                    } else {
                        Tiles.drawTile(context, cell, xFactor, yFactor);
                    }
                }
            }
        }
        centralCell = map.getPlayer().getCell();
    }
}
