package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class MedicalThing extends Item {


    private final int increaseHealth;
    public MedicalThing(Cell cell, int increaseHealth) {
        super(cell);
        this.increaseHealth=increaseHealth;
    }
    public int getIncreaseHealth() {
        return increaseHealth;
    }

}
