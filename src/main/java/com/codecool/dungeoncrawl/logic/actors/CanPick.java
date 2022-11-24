package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public interface CanPick {
    Item pickUp();

    boolean canPickUp();
}
