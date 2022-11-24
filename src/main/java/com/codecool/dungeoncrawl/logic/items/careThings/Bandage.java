package com.codecool.dungeoncrawl.logic.items.careThings;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bandage extends MedicalThing{
    public Bandage(Cell cell) {
        super(cell,2 );
    }
    @Override
    public String getTileName() {
        return "bandage";
    }
}
