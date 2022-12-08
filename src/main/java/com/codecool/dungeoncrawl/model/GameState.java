package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class GameState extends BaseModel {

    private LocalDate savedAt;  //TODO DD convert Strings to LocalDateTime
    private String currentMap;

    private final List<String> discoveredMapsInJSON = new ArrayList<>(); //todo consult Marcin, Mateusz
    private List<MonsterModel> monstersList; //todo consult Marcin, Mateusz
    private PlayerModel player;


    public GameState(String currentMap, LocalDate savedAt,, PlayerModel player, List<MonsterModel> monstersList) {
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

    public List<MonsterModel> getMonstersList() {
        return monstersList;
    }
}
