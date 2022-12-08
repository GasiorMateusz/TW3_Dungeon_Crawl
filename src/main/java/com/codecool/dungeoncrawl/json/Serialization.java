package com.codecool.dungeoncrawl.json;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Serialization {


    public void exportToJson(String currentMap, Date date, PlayerModel player, List<MonsterModel> monstersList, String fileName) {
        GameState gameState = new GameState(currentMap, date, player, monstersList);
        String json = new Gson().toJson(gameState);
        FileWritter fileWriter = new FileWritter();
        fileWriter.saveTofile(json, fileName);
    }

    public List<MonsterModel> getMonsterModelList(List<Actor> monsterList) {
        return monsterList.stream().map(MonsterModel::new).collect(Collectors.toList());
    }


}
