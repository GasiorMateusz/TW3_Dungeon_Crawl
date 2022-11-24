package com.codecool.dungeoncrawl.logic.items.careThings;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class MedicalThing extends Item {


    private int increaseHealth;
    public MedicalThing(Cell cell) {
        super(cell);
    }
    public int getIncreaseHealth() {
        return increaseHealth;
    }

    public void setIncreaseHealth(int increaseHealth) {
        this.increaseHealth = increaseHealth;
    }
}
