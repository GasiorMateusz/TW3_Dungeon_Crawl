package com.codecool.dungeoncrawl.logic.items.weapons;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Weapon extends Item {


    private int weaponStrength;

    public Weapon(Cell cell, int strength) {
        super(cell);
        this.weaponStrength = strength;
    }

    public int getWeaponStrength() {
        return weaponStrength;
    }
}
