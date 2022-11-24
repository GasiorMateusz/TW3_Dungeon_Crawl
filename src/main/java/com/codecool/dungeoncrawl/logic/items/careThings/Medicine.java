package com.codecool.dungeoncrawl.logic.items.careThings;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medicine extends MedicalThing{
    public Medicine(Cell cell) {
        super(cell);
        setIncreaseHealth(7); //set default value
    }

    @Override
    public String getTileName() {
        return "medicine";
    }
}
