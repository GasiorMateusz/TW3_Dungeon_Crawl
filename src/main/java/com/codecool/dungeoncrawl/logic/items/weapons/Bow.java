package com.codecool.dungeoncrawl.logic.items.weapons;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.weapons.Weapon;

public class Bow extends Weapon {
    public Bow(Cell cell) {
        super(cell);
        setAdditionalWeaponStrength(1);
    }

    @Override
    public String getTileName() {
        return "bow";
    }
}
