package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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