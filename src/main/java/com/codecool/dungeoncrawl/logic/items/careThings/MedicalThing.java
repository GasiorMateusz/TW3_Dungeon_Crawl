package com.codecool.dungeoncrawl.logic.items.careThings;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

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
