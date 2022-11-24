package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Weapon {
    public Sword(Cell cell) {
        super(cell);
        setWeaponStrength(7);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
