package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Octopus extends Actor {
    public Octopus(Cell cell) {
        super(cell);
        setStrikeStrength(2);
    }

    @Override
    public String getTileName() {
        return "octopus";
    }










}