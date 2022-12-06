package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.userCom.Popup;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.sql.SQLException;

public class Main extends Application {

    int[] cameraSize = new int[]{25, 35};
    int[] cameraCenterFactor = new int[]{5, 10};
    Cell centralCell;
    MultiMap multiMap = new MultiMap();
    GameMap map = MapLoader.loadMap(multiMap.getMapFromSet(0), false);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    int currentMapIndex = 0;
    Stage currentStage;
    Label healthLabel = new Label();
    Label inventoryListLabel = new Label();
    Label livesLabel = new Label();
    Button pickUpButton = new Button("Pick Up");
    Label name = new Label();
    Label message = new Label();
    GameDatabaseManager dbManager;
    private boolean teleported = false;

    public static void main(String[] args) {
        launch(args);
    }

    private void winPopUp() {
        System.out.println("GAME WON");
        Stage stage = new Stage();
        stage.setTitle("GAME OVER");
        stage.initStyle(StageStyle.UNDECORATED);
        VBox vBox = new VBox();
        Text label = new Text("\n\n\tHurray, you have found holy crown.\n" +
                "\tNow The Great Emperor can finish\n" +
                "\this bloody plan of lengthening half-lings\n\n");
        Button acceptButton = new Button();
        acceptButton.setText("Accept");
        acceptButton.setDefaultButton(true);
        acceptButton.setOnAction(event -> {
            stage.close();
            map = MapLoader.loadMap(multiMap.getMapFromSet(0), false, map.getPlayer());
            moveCamera(Direction.NONE);
            centerCamera();
            moveCamera(Direction.NONE);
        });
        vBox.getChildren().add(label);
        vBox.getChildren().add(acceptButton);
        Scene scene = new Scene(vBox, 250, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void setPlayerName() {
        System.out.println("SETTING NAME");
        Stage stage = new Stage();
        stage.setTitle("Player name");
        stage.initStyle(StageStyle.UNDECORATED);
        VBox vBox = new VBox();
        Label label = new Label("Enter player name: ");
        TextField playerName = new TextField();
        Button acceptButton = new Button();
        acceptButton.setText("Accept");
        acceptButton.setDefaultButton(true);
        acceptButton.setOnAction(event -> {
            if (!playerName.getText().isEmpty()) {
                map.getPlayer().setName(playerName.getText());
                name.setText("" + map.getPlayer().getName());
                stage.close();
            }
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
        currentStage = primaryStage;
        setupDbManager();

        GridPane ui = new GridPane();
        ui.setPrefWidth(250);
        ui.setPrefHeight(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Name: "), 0, 0);
        ui.add(name, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        ui.add(new Label("Extra Lives:"), 0, 2);
        ui.add(livesLabel, 1, 2);
        ui.add(new Label("Inventory:"), 0, 3);
        ui.add(inventoryListLabel, 0, 4);
        ui.add(pickUpButton, 0, 5);
        pickUpButton.setFocusTraversable(false);
        inventoryListLabel.setMinHeight(50);
        pickUpButton.setVisible(false);

        GridPane messages = new GridPane();
        messages.setPrefWidth(250);
        messages.setPrefHeight(200);
        messages.setPadding(new Insets(10));
        messages.add(message, 0, 0);

        VBox layout = new VBox();
        layout.setPrefWidth(250);
        layout.getChildren().addAll(ui, messages);
        layout.setPadding(new Insets(10));

        EventHandler<ActionEvent> event = e -> pickUpItemEvent();
        pickUpButton.setOnAction(event);
        BorderPane borderPane = new BorderPane();
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        canvas = new Canvas(
                size.getWidth() * 0.85,
                size.getHeight() * 0.9);

        context = canvas.getGraphicsContext2D();
        borderPane.setCenter(canvas);
        borderPane.setRight(layout);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        initMap();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("DUNGEON CRAWL by TeamONE");
        primaryStage.show();
        if (!teleported) {
            setPlayerName();
        }
    }

    private void worldMakeMove() {
        for (Actor monster : map.getMonstersList()) {
            if (monster.getHealth() > 0) {
                monster.monsterMove(map);
            }
        }
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void setMessage() {
        String text = "";
        if (map.getPlayer().isUnlockedDoor()) {
            text = "Key used to open the door!";
            map.getPlayer().setUnlockedDoor(false);
        }
        if (teleported) {
            text = "You teleported to another map!";
            teleported = false;
        }
        if (map.getPlayer().getOpponent() != null && !map.getPlayer().getOpponent().isAlive()) {
            text = map.getPlayer().getOpponent().getTileName() + " is dead!";
            map.getPlayer().setOpponent(null);
        }
        if (map.getPlayer().hasPickedUp()) {
            String itemName = map.getPlayer().getPickedUpItemName();
            switch (itemName) {
                case "sword":
                    text = "You have a sword! Strength strike +2";
                    break;
                case "bow":
                    text = "You have a bow! Strength strike +1";
                    break;
                case "bandage":
                    text = "You have a bandage!";
                    break;
                case "life":
                    text = "You have an extra life!";
                    break;
                case "medicine":
                    text = "You have a medicine!";
                    break;
                case "":
                    break;
            }
            map.getPlayer().setPick(false);
        }
        message.setText(text);
    }

    private void onKeyPressedAction(Direction direction) {
        map.getPlayer().move(direction.getValue().getX(), direction.getValue().getY());
        worldMakeMove();
        moveCamera(direction);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                onKeyPressedAction(Direction.UP);
                break;
            case DOWN:
                onKeyPressedAction(Direction.DOWN);
                break;
            case LEFT:
                onKeyPressedAction(Direction.LEFT);
                break;
            case RIGHT:
                onKeyPressedAction(Direction.RIGHT);
                break;
            case ENTER:
                pickUpItemEvent();
                break;
            default:
                break;
        }
        if (!map.getPlayer().isAlive) {
            teleported = false;
            restart();
            System.out.println(" RESTART =======================================================");
        }

        if (map.getPlayer().teleport) {
            System.out.println("TELEPORT " + map.getPlayer().getName());
            teleportation();
        }
    }

    public void teleportation() {
        teleported = true;
        map = MapLoader.loadMap(multiMap.getMapFromSet(++currentMapIndex), true, map.getPlayer());
        centerCamera();
        moveCamera(Direction.NONE);
    }

    public void restart() {
        Popup.display();
        map = MapLoader.loadMap(multiMap.getMapFromSet(0), false, map.getPlayer());
        moveCamera(Direction.NONE);
    }

    private void refresh() {
        setMessage();
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
        Item pickedUpItem = map.getPlayer().pickUp();
        if (pickedUpItem.getTileName().equals("life")) {
            map.getPlayer().increaseLifeCounter();
        }
        if (pickedUpItem.getTileName().equals("crown")) {
            winPopUp();
        }
        inventoryListLabel.setText(getInventoryDescription());
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpButton.setVisible(false);
        livesLabel.setText(Integer.toString(map.getPlayer().getLifeCounter()));
        setMessage();
    }

    private String getInventoryDescription() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < map.getPlayer().getInventory().getItems().size(); i++) {
            inventoryListLabel.setText(map.getPlayer().getInventory().getItems().get(i).getTileName());
            items.append(map.getPlayer().getInventory().getItems().get(i).getTileName()).append("\n");
        }
        return items.toString();
    }

    private void moveCamera(Direction direction) {
        refresh();
        for (int xFactor = 0; xFactor < cameraSize[0] + 18; xFactor++) {
            for (int yFactor = 0; yFactor < cameraSize[1]; yFactor++) {
                //todo extract
                int x = centralCell.getX() + xFactor - cameraSize[0] / 2 +
                        direction.getValue().getX() - cameraCenterFactor[1];
                int y = centralCell.getY() + yFactor - cameraSize[1] / 2 +
                        direction.getValue().getY() + cameraCenterFactor[0];
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

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private int getX(int centralCell, int direction, int cameraCenterFactor) {
        return centralCell +
                direction - cameraCenterFactor;
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
