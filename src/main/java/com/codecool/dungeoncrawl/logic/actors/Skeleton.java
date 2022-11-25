package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(7);
        setStrikeStrength(2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void monsterMove(GameMap map) {
    }
}
