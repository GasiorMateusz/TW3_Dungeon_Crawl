package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.util.List;

public class Deserialization {
    Player player;
    List<Actor> monsterList;
    String map;
    List<String> mapList;
    String json;

    public Deserialization(String json){
        this.json=json;
    }



    private GameState importFromJson() {
        FileLoader fileLoder = new FileLoader();
        json=  fileLoder.loadFromFile("filename");
        GameState gameStateDeserilised = new Gson().fromJson(json, GameState.class);
        return gameStateDeserilised;
    }
    public Player getPlayer(){
        Player player= new Player(importFromJson().getPlayer().getPlayerName(), )
        return player;
    }
    public List<Actor> getMonsterList() {
        return monsterList;
    }

    public String getMap() {
        return map;
    }

    public List<String> getMapList() {
        return mapList;
    }
}
