package com.codecool.dungeoncrawl.logic.items.weapons;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Weapon extends Item {


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
