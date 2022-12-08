package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;


public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int x;
    private int y;


    private InventoryModel inventory;


    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();


        this.hp = player.getHealth();
        this.inventory = new InventoryModel(player.getInventory());

    }

    public String getPlayerName() {
        return playerName;
    }


    public int getHp() {
        return hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
