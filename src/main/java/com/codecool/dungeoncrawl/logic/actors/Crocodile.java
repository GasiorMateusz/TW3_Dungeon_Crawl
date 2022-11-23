package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Crocodile extends Actor {
    public Crocodile(Cell cell) {
        super(cell);
        setStrikeStrength(2);
    }

    @Override
    public String getTileName() {
        return "crocodile";
    }
}