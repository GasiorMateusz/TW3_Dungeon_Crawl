package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private LocalDate savedAt;  //TODO Dawid D date and time  LocalDate, check with Mateusz
    private String currentMap;
    private final List<String> notDiscoveredMaps = new ArrayList<>(); //todo consult Marcin, Mateusz
    private final List<String> discoveredMapsInJSON = new ArrayList<>(); //todo consult Marcin, Mateusz
    private ArrayList<Actor> monstersList; //todo consult Marcin, Mateusz
    private PlayerModel player;

    public GameState(String currentMap, LocalDate savedAt, PlayerModel player, ArrayList<Actor> monstersList) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.monstersList = monstersList;
    }
    public GameState(PlayerModel player) {
        this.player = player;
    }

    public LocalDate getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDate savedAt) {
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
}
