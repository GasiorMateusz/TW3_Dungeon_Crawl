package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);

    void update(PlayerModel player);

    void set(PlayerModel playerModel);

    PlayerModel get(int id);

    List<PlayerModel> getAll();
}
