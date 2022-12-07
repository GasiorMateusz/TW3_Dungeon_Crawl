package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Deserialization {
    Player player;
    ArrayList<Actor> monsterList;

    String StringMap;
    List<String> mapList;
    GameState gameState;

    GameMap gameMap;

    public Deserialization(String fileName) {
        this.gameState = importFromJson(fileName);
        this.gameMap = MapLoader.loadMap(gameState.getCurrentMap(), false);


    }


    private GameState importFromJson(String filename) {
        FileLoader fileLoader = new FileLoader();
        String json= fileLoader.loadFromFile(filename);
        GameState gameStateDeserialized = new Gson().fromJson(json, GameState.class);
        return gameStateDeserialized;
    }








    public Player getPlayer() {
        PlayerModel playerModel = gameState.getPlayer();
        Cell cell = new Cell(gameMap, playerModel.getX(),playerModel.getY(), CellType.FLOOR);
        Player player = new Player(cell);
        return player;
    }
    public ArrayList<Actor> getMonsterList() {
        this.monsterList = new ArrayList<>();
        return monsterList;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public String getStringMap(){
        return StringMap;
    }

    public List<String> getMapList() {
        return mapList;
    }
}
