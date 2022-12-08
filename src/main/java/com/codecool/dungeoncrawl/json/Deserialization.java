package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Deserialization {
    Player player;
    List<Actor> monsterList;

    String StringMap;
    List<String> mapList;
    GameState gameState;

    GameMap gameMap;

    public Deserialization(String fileName) {
        this.gameState = importFromJson(fileName);
        this.gameMap = MapLoader.loadMap(gameState.getCurrentMap(), false);
        this.monsterList = createMonsterList();


    }


    private GameState importFromJson(String filename) {
        FileLoader fileLoader = new FileLoader();
        String json = fileLoader.loadFromFile(filename);
        GameState gameStateDeserialized = new Gson().fromJson(json, GameState.class);
        return gameStateDeserialized;
    }


    public Player getPlayer() {
        PlayerModel playerModel = gameState.getPlayer();
        Cell cell = new Cell(gameMap, playerModel.getX(), playerModel.getY(), CellType.FLOOR);
        Player player = new Player(cell);
        return player;
    }


        private List<Actor> createMonsterList() {
        List<MonsterModel> monsterModelList = gameState.getMonstersList();
        List<Actor> monsterList = new ArrayList<>();

        for (MonsterModel monster : monsterModelList) {
            Cell cell = new Cell(gameMap, monster.getX(), monster.getY(), CellType.FLOOR);
            switch (monster.getMonsterType()) {
                case "skeleton":
                    monsterList.add(new Skeleton(cell));
                    break;
                case "ghosts":
                    monsterList.add(new Ghosts(cell));
                    break;
                case "octopus":
                    monsterList.add(new Octopus(cell));
                    break;
                case "crocodile":
                    monsterList.add(new Crocodile(cell));
                    break;
            }
        }
        return monsterList;
    }

    public List<Actor> getMonsterList() {
        return monsterList;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public String getStringMap() {
        return StringMap;
    }

    public List<String> getMapList() {
        return mapList;
    }
}
