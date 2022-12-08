package com.codecool.dungeoncrawl.model;


import java.util.ArrayList;

import java.util.List;

public class GameState extends BaseModel {

    //    private LocalDate savedAt;
    private String savedAt;
    private String currentMap;

    private final List<String> discoveredMapsInJSON = new ArrayList<>();
    private List<MonsterModel> monstersList; //todo consult Marcin, Mateusz
    private PlayerModel player;


    //    public GameState(String currentMap, LocalDate savedAt,PlayerModel player, List<MonsterModel> monstersList) {
    public GameState(String currentMap, String savedAt, PlayerModel player, List<MonsterModel> monstersList) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.monstersList = monstersList;
    }


//    public LocalDate getSavedAt() {
//        return savedAt;
//    }
//
//    public void setSavedAt(LocalDate savedAt) {
//        this.savedAt = savedAt;
//    }

    public String getCurrentMap() {
        return currentMap;
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
