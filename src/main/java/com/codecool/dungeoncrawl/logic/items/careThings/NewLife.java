package com.codecool.dungeoncrawl.logic.items.careThings;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class NewLife extends Item {
    public NewLife(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "life";
    }
}
