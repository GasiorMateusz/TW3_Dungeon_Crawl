package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.codecool.dungeoncrawl.json.FileWritter;



public class Serialization {

    public void exportToJson(String currentMap, LocalDate date, PlayerModel player, ArrayList<Actor> monstersList, String fileName) {//todo skonsultuj z Dominiką
        GameState gameState= new GameState( currentMap,  date,  player, monstersList);
        String json= new Gson().toJson(gameState);
        FileWritter fileWriter = new FileWritter();
        fileWriter.saveTofile(json, fileName);
    }

    public List<MonsterModel> getMonsterModelList(List<Actor> monsterList){
       return monsterList.stream().map(MonsterModel::new).collect(Collectors.toList());
    }


}
