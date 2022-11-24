package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Weapon extends Item{


    private int weaponStrength;
    public Weapon(Cell cell) {
        super(cell);
    }
    public int getWeaponStrength() {
        return weaponStrength;
    }

    public void setWeaponStrength(int weaponStrength) {
        this.weaponStrength = weaponStrength;
    }
}
