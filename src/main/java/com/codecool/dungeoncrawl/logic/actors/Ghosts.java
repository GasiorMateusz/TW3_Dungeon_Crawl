package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghosts extends Actor {
    public Ghosts(Cell cell) {
        super(cell);
        setStrikeStrength(2);
        setHealth(20);
    }

    @Override
    public String getTileName() {
        return "ghosts";
    }


}