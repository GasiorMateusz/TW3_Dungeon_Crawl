package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
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

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryListLabel= new Label();
    Button pickUpButton= new Button("Pick Up");
    Label name = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    private void setPlayerName(){
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
    public void start(Stage primaryStage) throws Exception {
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


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                pickUpItemEvent();
            }
        };
        pickUpButton.setOnAction(event);
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        setPlayerName();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        map.getMonstersList().stream().forEach(Actor::monsterMove);
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
            case ENTER:
                pickUpItemEvent();
                break;
        }

    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpButton.setVisible(map.getPlayer().canPickUp());
        inventoryListLabel.setText(getInventoryDescription());
    }
    private void pickUpItemEvent(){
        map.getPlayer().pickUp();

        inventoryListLabel.setText(getInventoryDescription());
    }
    private String getInventoryDescription(){
        String items="";
        for(int i=0; i<map.getPlayer().getItems().size(); i++) {
            inventoryListLabel.setText(map.getPlayer().getItems().get(i).getTileName());
            items=items+map.getPlayer().getItems().get(i).getTileName()+"\n";
        }
        return items;
    }
}
