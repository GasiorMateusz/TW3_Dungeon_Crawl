package com.codecool.dungeoncrawl.model;



import java.util.ArrayList;

import java.util.List;

public class GameState extends BaseModel {
    private String savedAt;  //TODO Dawid D date and time  LocalDate, check with Mateusz
    private String currentMap;

    private final List<String> discoveredMapsInJSON = new ArrayList<>(); //todo consult Marcin, Mateusz
    private List<MonsterModel> monstersList; //todo consult Marcin, Mateusz
    private PlayerModel player;

    public GameState(String currentMap, String savedAt, PlayerModel player, List<MonsterModel> monstersList) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.monstersList = monstersList;
    }
    public GameState(PlayerModel player) {
        this.player = player;
    }

    public String getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(String savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }



    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public List<MonsterModel> getMonstersList() {
        return monstersList;
    }
}
