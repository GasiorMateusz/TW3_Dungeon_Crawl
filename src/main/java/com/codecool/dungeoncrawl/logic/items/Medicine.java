package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medicine extends MedicalThing{
    public Medicine(Cell cell) {
        super(cell, 4); //set default health value
    }

    @Override
    public String getTileName() {
        return "medicine";
    }
}
