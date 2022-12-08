package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;


public class MonsterModel extends BaseModel {
    private final String monsterType;
    private final int healthPoints;
    private int x;
    private int y;


    public MonsterModel(Actor actor) {
        this.monsterType = actor.getTileName();
        this.healthPoints = actor.getHealth();
        this.x = actor.getX();
        this.y = actor.getY();
    }


    public String getMonsterType() {
        return monsterType;
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
