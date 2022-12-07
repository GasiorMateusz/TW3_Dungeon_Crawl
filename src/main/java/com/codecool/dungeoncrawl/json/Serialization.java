package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.sql.Date;
import java.util.ArrayList;

import com.codecool.dungeoncrawl.json.FileWritter;

public class Serialization {






    public void exportToJson(String currentMap, Date date, PlayerModel player, ArrayList<Actor> monstersList) {//todo skonsultuj z Dominiką
        GameState gameState= new GameState( currentMap,  date,  player, monstersList);
        String json= new Gson().toJson(gameState);
        FileWritter fileWriter = new FileWritter();
        fileWriter.saveTofile(json);
    }

}
