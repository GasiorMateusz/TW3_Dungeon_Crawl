package com.codecool.dungeoncrawl.model;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class GameState extends BaseModel {

    //    private LocalDate savedAt;
    private final Date savedAt;
    private final String currentMap;

    private final List<String> discoveredMapsInJSON = new ArrayList<>();
    private final List<MonsterModel> monstersList;
    private PlayerModel player;


    //    public GameState(String currentMap, LocalDate savedAt,PlayerModel player, List<MonsterModel> monstersList) {
    public GameState(String currentMap, Date savedAt, PlayerModel player, List<MonsterModel> monstersList) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.monstersList = monstersList;
    }


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
