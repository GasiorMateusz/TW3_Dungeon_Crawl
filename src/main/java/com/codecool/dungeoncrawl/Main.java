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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    Label name = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    private void setPlayerName() {
        Stage stage = new Stage();
        stage.setTitle("Player name");
        VBox vBox = new VBox();
        Label label = new Label("Enter player name: ");
        TextField playerName = new TextField();
        Button acceptButton = new Button();
        acceptButton.setText("Accept");
        acceptButton.setDefaultButton(true);
        acceptButton.setOnAction(event -> {
            map.getPlayer().setName(playerName.getText());
            name.setText("" + map.getPlayer().getName());
            stage.close();
        });
        vBox.getChildren().add(label);
        vBox.getChildren().add(playerName);
        vBox.getChildren().add(acceptButton);
        Scene scene = new Scene(vBox, 250, 150);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Name: "), 0, 0);
        ui.add(name, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventoryListLabel, 0, 3);
        ui.add(pickUpButton, 0, 4);
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
        setPlayerName();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Direction direction = null;
        for (Actor monster : map.getMonstersList()) {
            if (monster.getHealth() > 0) {
                monster.monsterMove(map);
                refresh();
            }
        }
//        map.getMonstersList().stream().forEach(monster -> monster.monsterMove(map)); //todo why it does not work?
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
                direction = Direction.NONE;
                break;
        }
        moveCamera(direction);
    }

    private void refresh() {
        context.setFill(Color.rgb(71, 45, 60));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpButton.setVisible(map.getPlayer().canPickUp());
        inventoryListLabel.setText(getInventoryDescription());
    }

    private void initMap() {
        refresh();
        centerCamera();
        moveCamera(Direction.NONE);
    }

    private void centerCamera() {
        centralCell = map.getPlayer().getCell();
    }

    private void pickUpItemEvent() {
        map.getPlayer().pickUp();

        inventoryListLabel.setText(getInventoryDescription());
    }

    private String getInventoryDescription() {
        String items = "";
        for (int i = 0; i < map.getPlayer().getItems().size(); i++) {
            inventoryListLabel.setText(map.getPlayer().getItems().get(i).getTileName());
            items = items + map.getPlayer().getItems().get(i).getTileName() + "\n";
        }
        return items;
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
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), xFactor, yFactor);
                    } else {
                        Tiles.drawTile(context, cell, xFactor, yFactor);
                    }
                }
            }
        }
        centralCell = map.getPlayer().getCell();
    }
}
